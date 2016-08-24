package com.goit.gojavaonline.model;

/**
 * Created by tamila on 8/24/16.
 */
public class Ingredient {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
