package com.alexandracastrillonvalencia.ejemplo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 07/01/2016.
 */
public class List_Adapter extends ArrayAdapter {
    public List_Adapter (Context context, List objects){
        super (context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content_list,null);
        }
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView name = (TextView) convertView.findViewById(R.id.select);

        list_menu item = (list_menu) getItem(position);
        icon.setImageResource(item.getImageid());
        name.setText(item.getName());

        return convertView;
    }
}
