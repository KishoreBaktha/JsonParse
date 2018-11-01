package com.example.kishorebaktha.jsonparse;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button click;
    public static ListView data;
    public static customAdapter custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=(Button)findViewById(R.id.button);
        data=(ListView)findViewById(R.id.listview);
        custom=new customAdapter(this);
       // data.setAdapter(custom);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fetchData process=new fetchData();
                process.execute();
            }
        });
    }
}
  class singleRow
{
   String name;
    String password;
    String contact;
    String country;
    public singleRow(String name,String password, String contact,String country)
    {
        this.name=name;
        this.password=password;
        this.contact=contact;
        this.country=country;
    }
}
class customAdapter extends BaseAdapter
{
   ArrayList<singleRow> list;
    Context c;
    public customAdapter(Context context)
    {
        list=new ArrayList<singleRow>();
        c=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.singlerow,parent,false);
        TextView name=(TextView)row.findViewById(R.id.name);
        TextView password=(TextView)row.findViewById(R.id.password);
        TextView contact=(TextView)row.findViewById(R.id.contact);
        TextView country=(TextView)row.findViewById(R.id.country);
        singleRow tmp=list.get(position);
        name.setText(tmp.name);
        password.setText(tmp.password);
        contact.setText(tmp.contact);
        country.setText(tmp.country);
        return row;
    }
}