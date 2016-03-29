package com.tkieffer.domo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ViewManager {

    Activity mainAct;
    RelativeLayout root;

    CompoundButton.OnCheckedChangeListener ccl = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    };

    public ViewManager(Activity act, ListView lv) {
        // initialize root layout;

    }

    public void renderCard(int id, String name, boolean status){
        System.out.println("rendring id: " + id);
        // create one card
        //CardView card = new CardView(mainAct);


        // Set cards parameters (text + listener)

        // Attach card to main layout
        //root.addView(v);
    }
}
