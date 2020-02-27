package com.example.adapter;

import java.util.ArrayList;





import com.example.test1.R;
import com.example.ygcx.Ygxx2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter2 extends BaseAdapter{    
    private Context context;    
    private ArrayList<String> text;  
    public Adapter2(Context context,ArrayList<String> text){    
        this.context = context;    
        this.text=text;    
    }    
    @Override    
    public int getCount() {    
        // TODO Auto-generated method stub    
        return text.size();    
    }    
    @Override    
    public Object getItem(int position) {    
        // TODO Auto-generated method stub    
        return text.get(position);    
    }    
    @Override    
    public long getItemId(int position) {    
        // TODO Auto-generated method stub    
        return position;    
    }    
    @Override    
    public View getView(int position, View convertView, ViewGroup parent) {    
        // TODO Auto-generated method stub     
        View view=convertView;    
        if(view==null){    
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
            view=inflater.inflate(R.layout.row_simple_list_item_2, null);    
        }    
        final TextView textView=(TextView)view.findViewById(R.id.simple_item_1);    
        textView.setText(text.get(position));    
        final ImageView imageView=(ImageView)view.findViewById(R.id.simple_item_2);    
        imageView.setBackgroundResource(android.R.drawable.ic_dialog_info);    
        imageView.setTag(position);    
        imageView.setOnClickListener(new OnClickListener() {    
                
            @Override    
            public void onClick(View v) {    
                // TODO Auto-generated method stub  
            	Intent intent = new Intent(context, Ygxx2.class);
            	intent.putExtra("name", textView.getText().toString());
		        context.startActivity(intent);
            }    
        });    
        return view;    
    }    
} 