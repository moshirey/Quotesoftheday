package com.example.quotesoftheday;

public class Quote {
    String favourate;
    int id;

    public Quote(String favourate) {
        this.favourate = favourate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quote() {
    }

    public String getFavourate() {
        return favourate;
    }

    public void setFavourate(String favourate) {
        this.favourate = favourate;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "favourate='" + favourate + '\'' +
                '}';
    }
}
