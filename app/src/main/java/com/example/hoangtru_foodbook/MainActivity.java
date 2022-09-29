package com.example.hoangtru_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FoodBook foodBook;
    ArrayList<Food> foodList;
    FoodListAdapter foodListAdapter;
    ListView foodListView;
    TextView totalCostView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This food book object will contain every info we need, so the activity won't look messy
        foodBook = new FoodBook();
        // foodList to pass around
        foodList = foodBook.getFoodList();
        // create Adapter
        foodListAdapter = new FoodListAdapter(this, foodList);
        // create view (main view of this activity)
        foodListView = findViewById(R.id.food_list);
        // set Adapter for the view
        foodListView.setAdapter(foodListAdapter);
        // create view for total cost
        totalCostView = findViewById(R.id.total_cost);

        // Set what happens when we click an Item in ListView
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // bring us to InfoActivity
                Intent intent = new Intent(view.getContext(), InfoActivity.class);
                intent.putExtra("food", foodList.get(i));
                startActivity(intent);
            }
        });


        // Make part of total cost bold and italic
        SpannableString costString = new SpannableString(totalCostView.getText() + foodBook.getTotalCost().toString());
        StyleSpan boldItalicSpan = new StyleSpan(Typeface.BOLD_ITALIC);
        costString.setSpan(boldItalicSpan, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        totalCostView.setText(costString);
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.main_menu is a reference to an xml file named main_menu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a Android resource directory named "menu" inside res
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    // handle button activities
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.add_button) {
//            // get user to the AddEditActivity
//        }
//        return super.onOptionsItemSelected(item);
//    }

    // This method is announced by onClick attribute in the XML file (main_menu.xml)
    public void onAddClick(MenuItem item) {
        // Bring us to AddEditActivity
        Intent intent = new Intent(this, AddEditActivity.class);
        item.setIntent(intent);
        this.startActivity(intent);
    }
}