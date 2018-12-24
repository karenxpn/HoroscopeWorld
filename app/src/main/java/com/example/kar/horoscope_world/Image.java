package com.example.kar.horoscope_world;

public class Image {
    public String download_url;
    public String name;

    public Image ( String name, String download_url ) {
        this.name = name;
        this.download_url = download_url;
    }

    public Image() {this ( null, null );}
}
