package com.alexandre.transferenciaagendamento.classes;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {

    private static MessageSource messageSource;

    public MessageFactory(MessageSource messageSource) {
        MessageFactory.messageSource = messageSource;
    }

    public static String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
