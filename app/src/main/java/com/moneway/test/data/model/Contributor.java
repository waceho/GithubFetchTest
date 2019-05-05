package com.moneway.test.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributor {

    @SerializedName("login")
    @Expose
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public Contributor(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
