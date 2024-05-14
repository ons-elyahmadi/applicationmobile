package com.example.test.models;

public class Categorie  { private String imageURL, caption;
        public Categorie (){
        }
        public String getImageURL() {
            return imageURL;
        }
        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
        public String getCaption() {
            return caption;
        }
        public void setCaption(String caption) {
            this.caption = caption;
        }
        public Categorie(String imageURL, String caption) {
            this.imageURL = imageURL;
            this.caption = caption;
        }
}