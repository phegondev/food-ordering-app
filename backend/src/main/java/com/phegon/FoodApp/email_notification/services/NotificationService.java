package com.phegon.FoodApp.email_notification.services;

import com.phegon.FoodApp.email_notification.dtos.NotificationDTO;

public interface NotificationService {
    void sendEmail(NotificationDTO notificationDTO);
}
