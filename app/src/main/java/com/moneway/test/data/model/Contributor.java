package com.moneway.test.data.model;

public class Contributor {

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

    private String avatarUrl;

    public Contributor(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
