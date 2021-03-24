package com.servicenow.sample.repository.preferences;

import android.content.SharedPreferences;

/**
 * Created by Ashutosh Srivastava on 09-01-2018 02:20.
 * Project Name : MA-Dista
 */

public interface ISharedPref {

    String KEY_FIRST_NAME = "KEY_FIRST_NAME";
    String KEY_LAST_NAME = "KEY_LAST_NAME";

    SharedPreferences getSharedPreferneces();

    default void clear() {
        getSharedPreferneces().edit().clear().apply();
    }

    default void putString(String KEY, String VALUE) {
        getSharedPreferneces().edit().remove(KEY).putString(KEY, VALUE).apply();
    }

    default String getString(String KEY) {
        return getSharedPreferneces().getString(KEY, null);
    }

    default void putInteger(String KEY, int VALUE) {
        getSharedPreferneces().edit().remove(KEY).putInt(KEY, VALUE).apply();
    }

    default int getInteger(String KEY) {
        return getSharedPreferneces().getInt(KEY, 0);
    }

    default void putBoolean(String KEY, boolean VALUE) {
        getSharedPreferneces().edit().remove(KEY).putBoolean(KEY, VALUE).apply();
    }

    default boolean getBoolean(String KEY) {
        return getSharedPreferneces().getBoolean(KEY, false);
    }

    default void putFloat(String KEY, float VALUE) {
        getSharedPreferneces().edit().remove(KEY).putFloat(KEY, VALUE).apply();
    }

    default float getFloat(String KEY) {
        return getSharedPreferneces().getFloat(KEY, 0F);
    }

    default void putLong(String KEY, long VALUE) {
        getSharedPreferneces().edit().remove(KEY).putLong(KEY, VALUE).apply();
    }

    default long getLong(String KEY) {
        return getSharedPreferneces().getLong(KEY, 0L);
    }

}
