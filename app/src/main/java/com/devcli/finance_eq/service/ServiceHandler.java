package com.devcli.finance_eq.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.devcli.finance_eq.vo.Calculator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashantkoshta on 12/8/16.
 */

public class ServiceHandler extends IntentService {

    private static final String TAG = ServiceHandler.class.getName();

    public ServiceHandler() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<Calculator> list = this.execute(intent.getStringExtra("url"));
        Intent sendIntent = new Intent();
        sendIntent.setAction("GET_LIST");
        sendIntent.putExtra("result",(ArrayList<Calculator>)list);
        sendBroadcast(sendIntent);
        this.stopSelf();
    }

    private List<Calculator> execute(String sUrl){
        Log.i("###","############");
        HttpURLConnection urlConnection = null;
        URL url=null;
        String temp, response = "";
        BufferedReader bfReader = null;
        InputStream inStream = null;
        JSONArray jsonData =null;
        Gson gson = new Gson();
        List<Calculator> list = null;
        Type collectionType =null;

        try {
            url = new URL(sUrl);
            urlConnection  = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(false);
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inStream = urlConnection.getInputStream();

            bfReader= new BufferedReader(new InputStreamReader(inStream));
            while((temp = bfReader.readLine())!= null){
                response +=temp;
            }

            jsonData = (JSONArray) new JSONTokener(response).nextValue();
            collectionType = new TypeToken<List<Calculator>>() {}.getType();
            list = gson.fromJson(jsonData.toString(),collectionType);

            //Log.i("###", list.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if(bfReader!=null)
                try {
                    bfReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(inStream!=null)
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        return list;
    }

}
