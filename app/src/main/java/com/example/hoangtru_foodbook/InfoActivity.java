package com.example.hoangtru_foodbook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.widget.Toolbar;

public class InfoActivity extends AppCompatActivity {
    FoodBook foodBook;
    Food food;

    // create activity result launcher if you want to get data back from subsequent activities
    // we do this because startActivityForResult() is deprecated.
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 1) {
                        Log.d("in onActivityResult 1", "result code correct");
                        Intent intent = result.getData();
                        if(intent != null) {
                            // extract data
                            Log.d("in onActivityResult 2", "intent not null");
                            foodBook = (FoodBook) intent.getSerializableExtra("foodBook");
                            food = foodBook.getFood();

                            // find the views
                            TextView nameView = findViewById(R.id.info_name);
                            TextView descriptionView = findViewById(R.id.info_description);
                            TextView bestBeforeView = findViewById(R.id.info_bestBefore);
                            TextView locationView = findViewById(R.id.info_location);
                            TextView countView = findViewById(R.id.info_count);
                            TextView costView = findViewById(R.id.info_cost);

                            // format the view
                            SpannableString name = new SpannableString(nameView.getText().subSequence(0, 6) + food.getName());
                            SpannableString description = new SpannableString(descriptionView.getText().subSequence(0, 13) + food.getDescription());
                            SpannableString bestBefore = new SpannableString(bestBeforeView.getText().subSequence(0, 13) + food.getBestBefore());
                            SpannableString location = new SpannableString(locationView.getText().subSequence(0, 10) + food.getLocation());
                            SpannableString count = new SpannableString(countView.getText().subSequence(0, 7) + food.getCount().toString());
                            SpannableString cost = new SpannableString(costView.getText().subSequence(0, 11) + food.getCost().toString());

                            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

                            name.setSpan(boldSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            description.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            bestBefore.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            location.setSpan(boldSpan, 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            count.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            cost.setSpan(boldSpan, 0, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                            nameView.setText(name);
                            descriptionView.setText(description);
                            bestBeforeView.setText(bestBefore);
                            locationView.setText(location);
                            countView.setText(count);
                            costView.setText(cost);
                        }
                    }
                }
            }
    );

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
        SpannableString name = new SpannableString(nameView.getText().subSequence(0, 6) + food.getName());
        SpannableString description = new SpannableString(descriptionView.getText().subSequence(0, 13) + food.getDescription());
        SpannableString bestBefore = new SpannableString(bestBeforeView.getText().subSequence(0, 13) + food.getBestBefore());
        SpannableString location = new SpannableString(locationView.getText().subSequence(0, 10) + food.getLocation());
        SpannableString count = new SpannableString(countView.getText().subSequence(0, 7) + food.getCount().toString());
        SpannableString cost = new SpannableString(costView.getText().subSequence(0, 11) + food.getCost().toString());

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

        name.setSpan(boldSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        description.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bestBefore.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        location.setSpan(boldSpan, 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        count.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cost.setSpan(boldSpan, 0, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

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
        int id = item.getItemId();

        if(id == R.id.edit_button) {
            Intent intent = new Intent(this, AddEditActivity.class);
            intent.putExtra("foodBook", foodBook);
            activityResultLauncher.launch(intent);
        }
        else if (id == android.R.id.home) {
            Log.d("back button", foodBook.toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("foodBook", foodBook);
            setResult(1, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onDeleteClick(View view) {
        foodBook.deleteFood();
        Log.d("in foodBook: ", foodBook.toString());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("foodBook", foodBook);
        setResult(1, intent);
        finish();
    }
}