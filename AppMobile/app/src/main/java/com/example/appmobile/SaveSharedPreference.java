package com.example.appmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    static final String PREF_USER_NAME= "username";
    static final String PREF_EMAIL="email";
    static final String PREF_FIRST_NAME="firstname";
    static final String PREF_LAST_NAME="lastname";
    static final String PREF_SCORE="score";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }


    //EMAIL
    public static void setEmail(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_EMAIL, userName);
        editor.commit();
    }

    public static String getEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_EMAIL, "");
    }

    public static void clearEmail(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

    //LASTNAME
    public static void setLastName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LAST_NAME, userName);
        editor.commit();
    }

    public static String getLastName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_LAST_NAME, "");
    }

    public static void clearLastName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

    //FIRSTNAME
    public static void setFirstName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_FIRST_NAME, userName);
        editor.commit();
    }

    public static String getFirstName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_FIRST_NAME, "");
    }

    public static void clearFirstName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

    //SCORE
    public static void setScore(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_SCORE, userName);
        editor.commit();
    }

    public static String getScore(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_SCORE, "");
    }

    public static void clearScore(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }


}
