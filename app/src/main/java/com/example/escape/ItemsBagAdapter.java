package com.example.escape;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ItemsBagAdapter extends ArrayAdapter<Item> {
    private int resourceId;
    public ItemsBagAdapter(Context context,
                           int textViewResourceId,
                           List<Item> object){
        super(context, textViewResourceId, object);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item itemBag = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView itemsBagImage = (ImageView) view.findViewById(R.id.image_items_bag);
        TextView itemBagIntroduction = (TextView) view.findViewById(R.id.textView_items_bag);
        itemsBagImage.setImageResource(itemBag.getImageID());
        itemBagIntroduction.setText(itemBag.getName()+"\n"+itemBag.getIntroduction());
        return view;
    }
}
