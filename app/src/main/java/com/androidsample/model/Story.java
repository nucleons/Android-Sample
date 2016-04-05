package com.androidsample.model;

/**
 * Created by Aditya on 04-Apr-16.
 */
public class Story {

    public String type;
    public String title;
    public String content;
    public String image_url;
    public String location_url;
    public String more_images;

    public String getImageUrl(){
        if(image_url!=null)
            return image_url;
        return "";
    }

    public int getItemType(){
        if(type!=null){
            if(type.equals("simple_card"))
                return 0;
            else if(type.equals("checkin_card"))
                return 1;
            else if(type.equals("photo"))
                return 2;
        }
        return 0;
    }
}
