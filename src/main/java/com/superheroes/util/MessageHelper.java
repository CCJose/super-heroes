package com.superheroes.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHelper {
    private ResourceBundleMessageSource messageSource;

    public MessageHelper() {
        messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
    }

    public String translate(String label) {return messageSource.getMessage(label, null, LocaleContextHolder.getLocale());}
}
