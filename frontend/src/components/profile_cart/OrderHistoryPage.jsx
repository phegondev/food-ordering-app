import { useState, useEffect } from 'react';
import ApiService from '../../services/ApiService';
import { useNavigate } from 'react-router-dom';
import { useError } from '../common/ErrorDisplay';


const OrderHistoryPage = () => {

    const [orders, setOrders] = useState(null);
    const navigate = useNavigate();
    const { ErrorDisplay, showError } = useError();


    useEffect(() => {
        // Define an asynchronous function to fetch orders
        const fetchOrders = async () => {
            try {
                // Call the API service to get the current user's orders
                const response = await ApiService.getMyOrders();

                // Check if the API call was successful (status code 200)
                if (response.statusCode === 200) {
                    const enhancedOrders = [];
                    // Iterate over each order to enhance its items
                    for (const order of response.data) {
                        const enhancedItems = [];
                        // Iterate over each item within the current order
                        for (const item of order.orderItems) {
                            // Fetch details for each order item by its ID
                            const itemResponse = await ApiService.getOrderItemById(item.id);

                            // If item details are successfully fetched
                            if (itemResponse.statusCode === 200) {
                                enhancedItems.push({
                                    ...item,
                                    // Determine if the item has a review associated with the current order
                                    hasReview: itemResponse.data.menu.reviews.some(
                                        review => review.orderId === order.id
                                    )
                                });
                            } else {
                                // Return the original item if fetching details failed
                                enhancedItems.push(item);
                            }
                        }
                        // Add the order with its newly enhanced items to the list
                        enhancedOrders.push({ ...order, orderItems: enhancedItems });
                    }
                    // Update the state with the enhanced orders
                    setOrders(enhancedOrders);
                }
            } catch (error) {
                // Catch and display any errors that occur during the fetch operation
                showError(error.response?.data?.message || error.message);
            }
        };

        fetchOrders();
    }, []);


    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const options = {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
        };
        return date.toLocaleDateString(undefined, options);
    };


    const handleLeaveReview = (orderId, menuId) => {
        navigate(`/leave-review?orderId=${orderId}&menuId=${menuId}`);
    };


    if (!orders || orders.length === 0) {
        return (
            <div className="order-history-container">
                <div className="no-orders-message">
                    <p>You have no previous orders.</p>
                </div>
            </div>
        );
    }


    return (
        <div className="order-history-container">
            {/* Render the ErrorDisplay component */}
            <ErrorDisplay />
            <h1 className="order-history-title">Your Order History</h1>
            <div className="order-list">
                {orders.map((order) => (
                    <div key={order.id} className="order-card">
                        <div className="order-header">
                            <span className="order-id">Order ID: {order.id}</span>
                            <span className="order-date">
                                Date: {formatDate(order.orderDate)}
                            </span>
                            <span className="order-status">
                                Status: <span className={`status-${order.orderStatus.toLowerCase()}`}>{order.orderStatus}</span>
                            </span>
                            <span className="order-total">
                                Total: ${order.totalAmount.toFixed(2)}
                            </span>
                        </div>
                        <div className="order-items">
                            <h2 className="order-items-title">Order Items:</h2>
                            {order.orderItems.map((item) => (
                                <div key={item.id} className="order-item">
                                    <div className="item-details">
                                        <span className="item-name">{item.menu.name}</span>
                                        <span className="item-quantity">Quantity: {item.quantity}</span>
                                        <span className="item-price">
                                            Price: ${item.pricePerUnit.toFixed(2)}
                                        </span>
                                        <span className="subtotal">
                                            Subtotal: ${item.subtotal.toFixed(2)}
                                        </span>
                                        {order.orderStatus.toLowerCase() === 'delivered' && !item.hasReview && (
                                            <button
                                                className="review-button"
                                                onClick={() => handleLeaveReview(order.id, item.menu.id)}
                                            >
                                                Leave Review
                                            </button>
                                        )}
                                    </div>
                                    <div className="item-image-container">
                                        <img src={item.menu.imageUrl} alt={item.menu.name} className="item-image" />
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );

}
export default OrderHistoryPage;