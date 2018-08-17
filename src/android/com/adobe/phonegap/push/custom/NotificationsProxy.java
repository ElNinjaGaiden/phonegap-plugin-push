package com.adobe.phonegap.push.custom;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Locale;

public class NotificationsProxy {

  // Log tag
  private static String LOG_TAG = "CustomNotificationsProxy"; 

  // Date format to parse dates comming from the server
  private static final SimpleDateFormat ISODateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

  public Bundle checkValidNotification(Bundle extras) {
    String notificationCategory = extras.getString("category");
    
    if("GameReminder".equals(notificationCategory)) {
      return resolveGameReminderBundle(extras);
    }
    return extras;
  }
    
  private Bundle resolveGameReminderBundle(Bundle extras) {
    try {
      String date = extras.getString("date");
      Date gameDate = ISODateFormat.parse(date);
      Date now = new Date();
      String customDataContent = extras.getString("customData");
      JSONObject customData = new JSONObject(customDataContent);
      Integer timeInterval = customData.getInt("TimeInterval");
      Integer marginOffset = customData.getInt("MarginOffset");
      Integer maxLimit = timeInterval + marginOffset;
      Integer minLimit = timeInterval - marginOffset;
      long diffInMillisec = gameDate.getTime() - now.getTime();
      long diffInSecs = diffInMillisec / 1000;
      long diffInMin = (diffInSecs / 60) + 1;

      if(diffInMin >= minLimit && diffInMin <= maxLimit) {
        String sportsCenterName = customData.getString("SportsCenterName");
        String localeId = customData.getString("LanguageId").split("-")[0];
        Locale locale = new Locale(localeId);
        ResourceBundle resources = ResourceBundle.getBundle("com.adobe.phonegap.push.custom.resources.GamesRemindersResources", locale);
        String message = String.format(resources.getString("gameReminderTextTpl"), sportsCenterName, diffInMin);
        extras.putString("message", message);
        return extras;
      }
      else {
        //It is too "late" to show the reminder
        Log.d(LOG_TAG, "Too early or too late to show a game reminder");
        return null;
      }
    }
    catch (JSONException ex) {
      Log.d(LOG_TAG, "Error parsing JSON: " + ex.getMessage());
      return null;
    }
    catch(ParseException ex) {
      Log.d(LOG_TAG, "Error parsing date: " + ex.getMessage());
      return null;
    }
  }
}