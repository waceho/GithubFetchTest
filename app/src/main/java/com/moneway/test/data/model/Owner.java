package com.moneway.test.data.model;

import io.realm.RealmObject;

/**
 * repos owner Object
 **/
public class Owner extends RealmObject {

    public String getLogin() {
        return login;
    }

    private String login;

    public Owner() {
    }

}
