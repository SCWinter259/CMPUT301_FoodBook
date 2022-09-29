package com.example.hoangtru_foodbook;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoodListAdapter extends ArrayAdapter {
    private ArrayList<Food> foodList;
    private Context context;

    public FoodListAdapter(Context context, ArrayList<Food> foodList) {
        super(context, 0, foodList);
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        }

        Food food = foodList.get(position);

        TextView nameView = view.findViewById(R.id.content_name);
        TextView countView = view.findViewById(R.id.content_count);
        TextView costView = view.findViewById(R.id.content_cost);

        // Assemble the strings and make part of them bold
        SpannableString name = new SpannableString(nameView.getText() + food.getName());
        SpannableString count = new SpannableString(countView.getText() + food.getCount().toString());
        SpannableString cost = new SpannableString(costView.getText() + food.getCost().toString());

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

        // set part of strings to bold
        name.setSpan(boldSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        count.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cost.setSpan(boldSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // set text into textView
        nameView.setText(name);
        countView.setText(count);
        costView.setText(cost);

        return view;
    }
}
