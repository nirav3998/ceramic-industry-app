package com.example.valenzaceramic;

public class Tile {


    private String Applications;
    private String Dimension;
    private String Finish;
    private String Image;
    private String Price;

    public Tile() {
    }

    public Tile(String applications, String dimension, String finish, String image, String price) {
        Applications = applications;
        Dimension = dimension;
        Finish = finish;
        Image = image;
        Price = price;
    }

    public String getApplications() {
        return Applications;
    }

    public void setApplications(String applications) {
        Applications = applications;
    }

    public String getDimension() {
        return Dimension;
    }

    public void setDimension(String dimension) {
        Dimension = dimension;
    }

    public String getFinish() {
        return Finish;
    }

    public void setFinish(String finish) {
        Finish = finish;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
