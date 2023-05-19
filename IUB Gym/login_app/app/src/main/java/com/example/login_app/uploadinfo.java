package com.example.login_app;

public class uploadinfo {

    public String imageURL;
    public uploadinfo(){}

    public uploadinfo(String name, String url) {
        this.imageURL = url;
    }

    public String getImageURL() {
        return imageURL;
    }
}

