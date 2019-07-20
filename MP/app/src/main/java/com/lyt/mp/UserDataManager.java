package com.lyt.mp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

public class UserDataManager {

    //静态内部类
    //优雅，实现了懒加载和线程安全，线程安全利用了虚拟机的机制
    private static class Holder {
        private static UserDataManager INSTANCE = new UserDataManager();
    }

    // 构造方法
    private UserDataManager() {
    }

    public static UserDataManager getInstance() {
        return Holder.INSTANCE;
    }

    public String username;
    public String token;
    public String agentNo;

    private SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());

    @SuppressWarnings("null")
    void SetUserData(Map map) {
        username = map.get("username").toString();
        token = map.get("token").toString();
        agentNo = map.get("agentNo").toString();
    }

    public String getAgentNo() {
        if (agentNo != null) {
            return agentNo;
        }
        return preferences.getString("agentNo", "");
    }

    public String getUsername() {
        if (username != null) {
            return username;
        }
        return preferences.getString("username", "");
    }

    public String getToken() {
        if (token != null) {
            return token;
        }
        return preferences.getString("token", "");
    }

    public void setUsername(String username) {
        this.username = username;
        preferences.edit().putString("username", username).apply();
    }

    public void setToken(String token) {
        this.token = token;
        preferences.edit().putString("token", token).apply();
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
        preferences.edit().putString("agentNo", agentNo).apply();
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }

}
