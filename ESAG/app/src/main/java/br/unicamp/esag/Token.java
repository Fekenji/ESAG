package br.unicamp.esag;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Token {

    private SharedPreferences sharedPreferences;

    public Token(Context context)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setToken(String token)
    {
        sharedPreferences.edit().putString("token", token).commit();
    }

    public String getToken() { return sharedPreferences.getString("token", null); }


}
