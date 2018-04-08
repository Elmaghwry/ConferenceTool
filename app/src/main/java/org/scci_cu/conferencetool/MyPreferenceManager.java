package org.scci_cu.conferencetool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.IntentCompat;
import android.util.Log;

import org.scci_cu.conferencetool.Models.UserModel;


public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Conference Tool";

    // All Shared Preferences Keys
    private static final String KEY_USER_PASS = "user_pass";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_TYPE= "type";


    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public MyPreferenceManager() {

    }


    public void storeUser(UserModel user) {
        editor.clear();
        editor.commit();

        editor.putString(KEY_USER_NAME, user.getUserName());
        editor.putString(KEY_USER_PASS , user.getPassword());
        editor.putString(KEY_USER_TYPE , user.getType());
        editor.commit();


        Log.e(TAG, "User is stored in shared preferences. " + user.getUserName() + " ," + user.getType() );
    }
    public UserModel getUser() {
        if (pref.getString(KEY_USER_NAME, null) != null) {
            String  username, password,type;
            username = pref.getString(KEY_USER_NAME, null);
            password = pref.getString(KEY_USER_TYPE, null);
            type = pref.getString(KEY_USER_TYPE, null);

            UserModel user = new UserModel(username,password,type);
            return user;
        }
        return null;
    }
    public void clear() {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(_context, LoginActivity.class);
        ComponentName cn = intent.getComponent();
        Intent mainIntent = IntentCompat.makeRestartActivityTask(cn);
        _context.startActivity(mainIntent);
    }
}
