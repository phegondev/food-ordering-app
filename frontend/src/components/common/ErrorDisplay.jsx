import { useState, useEffect } from 'react';

/**
 * A component that displays error messages with auto-dismiss functionality
 * @param {string} message - The error message to display
 * @param {function} onDismiss - Callback to dismiss the error
 */
const ErrorDisplay = ({ message, onDismiss }) => {
    // Auto-dismiss the error after 5 seconds
    useEffect(() => {
        const timer = setTimeout(() => {
            onDismiss();
        }, 5000);

        // Cleanup: Clear the timer if component unmounts or message changes
        return () => clearTimeout(timer);
    }, [message, onDismiss]);

    // Don't render if there's no error message
    if (!message) return null;

    return (
        <div className="error-display">
            <div className="error-content">
                {/* Display the error message */}
                <span className="error-message">{message}</span>
                {/* Visual progress indicator (for auto-dismiss) */}
                <div className="error-progress"></div>
            </div>
        </div>
    );
};

/**
 * Custom hook for error handling
 * @returns {object} - Contains ErrorDisplay component and control methods
 */

export const useError = () => {
    // State to hold the current error message
    const [errorMessage, setErrorMessage] = useState(null);

    /**
     * Shows an error message
     * @param {string} message - The error message to display
     */
    const showError = (message) => {
        setErrorMessage(message);
    };

    /** Dismisses the current error message */
    const dismissError = () => {
        setErrorMessage(null);
    };

    return {
        // Component that renders the error display
        ErrorDisplay: () => (
            <ErrorDisplay
                message={errorMessage}
                onDismiss={dismissError}
            />
        ),
        // Methods to control the error display
        showError,
        dismissError
    };
};

export default ErrorDisplay;