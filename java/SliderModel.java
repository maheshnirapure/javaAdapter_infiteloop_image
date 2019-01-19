package com.potalika.potalika;

public class SliderModel {

    private int banner;
    private String  backroundColor;


    public SliderModel(int banner, String backroundColor) {
        this.banner = banner;
        this.backroundColor = backroundColor;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public String getBackroundColor() {
        return backroundColor;
    }

    public void setBackroundColor(String backroundColor) {
        this.backroundColor = backroundColor;
    }
}
