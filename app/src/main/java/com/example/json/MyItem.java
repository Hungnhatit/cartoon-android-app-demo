package com.example.json;

public class MyItem {
    String image, name, year;

    public MyItem(String image, String name, String year) {
        this.image = image;
        this.name = name;
        this.year = year;
    }

    public MyItem() {};

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
