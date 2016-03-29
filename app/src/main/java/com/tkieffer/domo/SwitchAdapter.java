package com.tkieffer.domo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class SwitchAdapter extends ArrayAdapter<Switch> {

    MainActivity context;
    List<Switch> data;

    SwitchAdapter(MainActivity ctx, List<Switch> data){
        super(ctx,-1,data);
        this.context = ctx;
        this.data = data;
    }

    CompoundButton.OnCheckedChangeListener ccl = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            System.out.println("Changed status of switch id : "+buttonView.getId());
            context.switchInter(buttonView.getId());
        }
    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View card = inflater.inflate(R.layout.switchcard, parent, false);

        TextView tv = (TextView) card.findViewById(R.id.sw_name);
        android.widget.Switch sw = (android.widget.Switch) card.findViewById(R.id.sw);

        Switch current = data.get(position);
        tv.setText(current.getName());
        sw.setChecked(current.isChecked());
        sw.setId(current.getId());
        sw.setOnCheckedChangeListener(ccl);

        return card;
    }
}
