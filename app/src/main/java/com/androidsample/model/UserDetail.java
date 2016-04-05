package com.androidsample.model;

import java.util.List;

/**
 * Created by Aditya on 04-Apr-16.
 */
public class UserDetail {

    public String name;
    public String photo;
    public String phone;
    public String email;
    public String contact_url;
    public List<Story> our_story;

    public Story getItem(int position){
        if(our_story!=null && our_story.size()>0)
            return our_story.get(position);
        return null;
    }

    public int getCount(){
        if(our_story!=null && our_story.size()>0)
            return our_story.size();
        return 0;
    }
}
