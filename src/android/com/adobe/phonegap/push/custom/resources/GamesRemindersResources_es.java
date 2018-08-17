package com.adobe.phonegap.push.custom.resources;

import java.util.ResourceBundle;
import java.util.Enumeration;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class GamesRemindersResources_es extends ResourceBundle {

    public Object handleGetObject(String key) {
        switch (key) {

            case "gameReminderTextTpl":
                return "Tú juego en %s iniciará en %d minutos";

            default:
                return null;
        }
    }

    public Enumeration<String> getKeys() {
        return Collections.enumeration(keySet());
    }

    // Overrides handleKeySet() so that the getKeys() implementation
    // can rely on the keySet() value.
    protected Set<String> handleKeySet() {
        return new HashSet<String>(Arrays.asList("gameReminderTextTpl"));
    }
}