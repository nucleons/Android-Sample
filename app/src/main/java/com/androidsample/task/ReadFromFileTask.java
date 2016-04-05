package com.androidsample.task;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.androidsample.startup.GlobalAppContext;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aditya on 04-Apr-16.
 */
public class ReadFromFileTask extends AsyncTask<String,Void,Object>{

    public interface FileReadListener {

        void onFileReadSuccess(Object response);

        void onFileReadFailure();
    }

    private FileReadListener mListener;
    private Class type;

    public ReadFromFileTask(FileReadListener listener,Class classType){
        mListener = listener;
        type = classType;
    }

    @Override
    protected Object doInBackground(String... params) {
        byte[] data;
        String jsonData = "";
        try {
            AssetManager manager = GlobalAppContext.getContext().getAssets();
            InputStream file = manager.open(params[0]);

            data = new byte[file.available()];
            file.read(data);
            file.close();
            jsonData = new String(data);
        }catch (Exception e){

        }
        Gson gson = new Gson();
        Object object = gson.fromJson(jsonData, type);
        Log.i("hello", jsonData);
        return object;
    }


    protected void onPostExecute(Object result) {
        mListener.onFileReadSuccess(result);
    }
}
