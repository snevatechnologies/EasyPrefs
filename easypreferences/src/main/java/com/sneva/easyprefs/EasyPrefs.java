package com.sneva.easyprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Developer : Harsh Kumar Singh
 * Programmer : Harsh Kumar Singh
 * Founder : Harsh Kumar Singh
 * Company : Sneva Technologies
 * UI/UX : Harsh Kumar Singh
 * Date : 15-10-2022
 * */

public class EasyPrefs {

    private static EasyPrefs instance;
    private static SharedPreferences sharedPreferences;

    public EasyPrefs() {

    }

    // by using this method our EasyPrefs library will be initialized in our Application.
    public static void initialize(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // this method is used to check and validate is EasyPrefs library is Initialized or not.
    public static EasyPrefs use() {
        if (instance == null) {
            validate();
            synchronized (EasyPrefs.class) {
                if (instance == null) {
                    instance = new EasyPrefs();
                }
            }
        }
        return instance;
    }

    // this method is used to get the value of Introduction Activity or Onboarding Activity
    public boolean getIntroduction() {
        return sharedPreferences.getBoolean("firstTimeOpen", true);
    }

    // this method will put first run value to prefs.
    public void setIntroduction(boolean isFirstRun) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstTimeOpen", isFirstRun);
        editor.apply();
    }

    // this method will check is user is signed in or not
    public boolean getSignedIn() {
        return sharedPreferences.getBoolean("isSignedIn", true);
    }

    // it will set user is signed in or not
    public void setSignedIn(boolean isSignedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isSignedIn", isSignedIn);
        editor.apply();
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Developer : Harsh Kumar Singh
     * Programmer : Harsh Kumar Singh
     * Founder : Harsh Kumar Singh
     * Company : Sneva Technologies
     * UI/UX : Harsh Kumar Singh
     * Date : 15-10-2022
     * */

    public int getInt(String key, int defaultValue) {
        if (isKeyExist(key)) {
            return sharedPreferences.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (isKeyExist(key)) {
            return sharedPreferences.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * Developer : Harsh Kumar Singh
     * Programmer : Harsh Kumar Singh
     * Founder : Harsh Kumar Singh
     * Company : Sneva Technologies
     * UI/UX : Harsh Kumar Singh
     * Date : 15-10-2022
     * */

    public void setFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key, float defaultValue) {
        if (isKeyExist(key)) {
            return sharedPreferences.getFloat(key, defaultValue);
        }
        return defaultValue;
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultValue) {
        if (isKeyExist(key)) {
            return sharedPreferences.getLong(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * Developer : Harsh Kumar Singh
     * Programmer : Harsh Kumar Singh
     * Founder : Harsh Kumar Singh
     * Company : Sneva Technologies
     * UI/UX : Harsh Kumar Singh
     * Date : 15-10-2022
     * */

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        if (isKeyExist(key)) {
            return sharedPreferences.getString(key, defaultValue);
        }
        return defaultValue;
    }

    public <T> void setObject(String key, T object) {
        String objectString = new Gson().toJson(object);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, objectString);
        editor.apply();
    }

    public <T> T getObject(String key, Class<T> classType) {
        if (isKeyExist(key)) {
            String objectString = sharedPreferences.getString(key, null);
            if (objectString != null) {
                return new Gson().fromJson(objectString, classType);
            }
        }
        return null;
    }

    /**
     * Developer : Harsh Kumar Singh
     * Programmer : Harsh Kumar Singh
     * Founder : Harsh Kumar Singh
     * Company : Sneva Technologies
     * UI/UX : Harsh Kumar Singh
     * Date : 15-10-2022
     * */

    public <T> void setObjectsList(String key, List<T> objectList) {
        String objectString = new Gson().toJson(objectList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, objectString);
        editor.apply();
    }

    public <T> List<T> getObjectsList(String key, Class<T> classType) {
        if (isKeyExist(key)) {
            String objectString = sharedPreferences.getString(key, null);
            if (objectString != null) {

                ArrayList<T> t = new Gson().fromJson(objectString, new TypeToken<List<T>>() {
                }.getType());

                List<T> finalList = new ArrayList<>();

                for (int i = 0; i < t.size(); i++) {
                    String s = String.valueOf(t.get(i));
                    finalList.add(new Gson().fromJson(s, classType));
                }

                return finalList;
            }
        }

        return null;
    }

    /**
     * Developer : Harsh Kumar Singh
     * Programmer : Harsh Kumar Singh
     * Founder : Harsh Kumar Singh
     * Company : Sneva Technologies
     * UI/UX : Harsh Kumar Singh
     * Date : 15-10-2022
     * */

    public void clearPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean deleteValue(String key) {
        if (isKeyExist(key)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
            return true;
        }

        return false;
    }

    public String searchKey(String key) {
        Map<String, ?> map = sharedPreferences.getAll();
        if (map.containsKey(key)) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    /**
     * Developer : Harsh Kumar Singh
     * Programmer : Harsh Kumar Singh
     * Founder : Harsh Kumar Singh
     * Company : Sneva Technologies
     * UI/UX : Harsh Kumar Singh
     * Date : 15-10-2022
     * */

    public boolean isKeyExist(String key) {
        Map<String, ?> map = sharedPreferences.getAll();
        if (map.containsKey(key)) {
            return true;
        } else {
            Log.e("EasyPrefs", "Not found any key which belongs to string you have provided " + key);
            return false;
        }
    }

    private static void validate() {
        if (sharedPreferences == null)
            throw new EasyPrefsException("EasyPrefs Library must be initialized inside your application class by 'EasyPrefs.initialize(getApplicationContext());'");
    }
}
