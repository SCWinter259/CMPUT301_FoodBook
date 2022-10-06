package com.example.hoangtru_foodbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    FoodBook foodBook;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // get information from the intent
        foodBook = (FoodBook) getIntent().getSerializableExtra("foodBook");
        food = foodBook.getFood();

        // find the views
        TextView nameView = findViewById(R.id.info_name);
        TextView descriptionView = findViewById(R.id.info_description);
        TextView bestBeforeView = findViewById(R.id.info_bestBefore);
        TextView locationView = findViewById(R.id.info_location);
        TextView countView = findViewById(R.id.info_count);
        TextView costView = findViewById(R.id.info_cost);

        // format the view
        SpannableString name = new SpannableString(nameView.getText() + food.getName());
        SpannableString description = new SpannableString(descriptionView.getText() + food.getDescription());
        SpannableString bestBefore = new SpannableString(bestBeforeView.getText() + food.getBestBefore());
        SpannableString location = new SpannableString(locationView.getText() + food.getLocation());
        SpannableString count = new SpannableString(countView.getText() + food.getCount().toString());
        SpannableString cost = new SpannableString(costView.getText() + food.getCost().toString());

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

        name.setSpan(boldSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        description.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bestBefore.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        location.setSpan(boldSpan, 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        count.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cost.setSpan(boldSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        nameView.setText(name);
        descriptionView.setText(description);
        bestBeforeView.setText(bestBefore);
        locationView.setText(location);
        countView.setText(count);
        costView.setText(cost);
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.main_menu is a reference to an xml file named main_menu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a Android resource directory named "menu" inside res
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, AddEditActivity.class);
        intent.putExtra("foodBook", foodBook);
        item.setIntent(intent);
        this.startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void onDeleteClick(View view) {
        foodBook.deleteFood();
        Log.d("in foodBook: ", foodBook.toString());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("foodBook", foodBook);
        setResult(1, intent);
        // startActivity(intent);
        finish();
    }
}