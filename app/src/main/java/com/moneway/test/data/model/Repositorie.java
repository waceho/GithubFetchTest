package com.moneway.test.data.model;

import com.google.gson.annotations.SerializedName;

public class Repositorie {

    private final long id;
    private final String name;
    private final String description;
    private final Owner owner;
    @SerializedName("stargazers_count")
    private final long stars;
    @SerializedName("forks_count")
    private final long forks;

    public Repositorie(long id, String name, String description, Owner owner, long stars, long forks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.stars = stars;
        this.forks = forks;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Owner getOwner() {
        return owner;
    }

    public long getStars() {
        return stars;
    }

    public long getForks() {
        return forks;
    }
}
