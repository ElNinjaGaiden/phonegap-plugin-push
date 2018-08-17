package com.adobe.phonegap.push.custom.resources;

import java.util.ResourceBundle;
import java.util.Enumeration;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class GamesRemindersResources_en extends GamesRemindersResources_es {

    public Object handleGetObject(String key) {
        switch (key) {

            case "gameReminderTextTpl":
                return "Your game at %s will start on %d minutes";

            default:
                return null;
        }
    }
}