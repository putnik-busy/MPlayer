package com.just_app.mplayer;


import java.util.ArrayList;

public class Model_Melodies {
    public ArrayList<Melodies> mMelodies;

    public ArrayList<Melodies> getMelodies() {
        return mMelodies;
    }

    public void setMelodies(ArrayList<Melodies> melodies) {
        mMelodies = melodies;
    }

    static class Melodies{
        private String picUrl;
        private String title;
        private String artist;
        private String demoUrl;

        public Melodies(String picUrl, String title, String artist, String demoUrl) {
            this.picUrl = picUrl;
            this.title = title;
            this.artist = artist;
            this.demoUrl = demoUrl;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getDemoUrl() {
            return demoUrl;
        }

        public void setDemoUrl(String demoUrl) {
            this.demoUrl = demoUrl;
        }
    }
}
