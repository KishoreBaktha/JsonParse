package com.example.kishorebaktha.jsonparse;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by KISHORE BAKTHA on 10/14/2017.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data="",dataParsed="",singleParsed="";
    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url=new URL("https://api.myjson.com/bins/bco5t");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null)
            {
                line=bufferedReader.readLine();
                data=data+line;
            }
            JSONArray jsonArray=new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jo= (JSONObject) jsonArray.get(i);
                String name=jo.get("name").toString();
                String password=jo.get("password").toString();
                String contact=jo.get("contact").toString();
                String country=jo.get("country").toString();
//                singleParsed="Name:"+jo.get("name")+"\n"+
//                              "Password:"+jo.get("password")+"\n"+
//                               "Contact:"+jo.get("contact")+"\n"+
//                               "Country:"+jo.get("country")+"\n";
                MainActivity.custom.list.add(new singleRow(name,password,contact,country));
                //dataParsed=dataParsed+singleParsed+"\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setAdapter(MainActivity.custom);
    }
}
