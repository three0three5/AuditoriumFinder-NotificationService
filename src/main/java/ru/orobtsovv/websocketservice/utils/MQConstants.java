package ru.orobtsovv.websocketservice.utils;

public class MQConstants {
    public static final String PROFILE_DELETE_NOTIFICATIONS = "profile-delete-notifications-queue"; // user->notif
    public static final String PROFILE_UPDATE = "profile-update-notifications-queue"; // user->notif
    public static final String FRIEND_LINK_DELETED = "friend-link-delete-queue"; // user->notif
    public static final String AUDITORIUM_UPDATE = "auditorium-update-queue"; // aud->notif
    public static final String FRIEND_REQUEST_ACCEPTED = "friend-request-accepted-queue"; //user->notif
    public static final String FRIEND_REQUEST_RECEIVED = "friend-request-received-queue"; //user->notif
}
