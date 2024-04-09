package ru.orobtsovv.websocketservice.utils;

public class Constants {
    public static final String USER_ID_HEADER_NAME = "userid";
    public static final String USER_DESTINATION_PREFIX = "/user";
    public static final String USER_DESTINATION = "/updates";
    public static final String TELEGRAM_DESTINATION = "/tg/updates";
    public static final String NOT_FOUND_EXCEPTION = "Указанный ресурс не найден";
    public static final String IS_TG_ATTRIBUTE = "tgservice";
    public static final String NOT_FRIENDS_EXCEPTION = "Подписка невозможна, пользователя нет в списке друзей";
    public static final String SUB_SUCCESS = "Подписка успешна";
    public static final String UNSUB_SUCCESS = "Подписка удалена";
}
