package com.example.hoangtru_foodbook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FoodBook foodBook;
    ArrayList<Food> foodList;
    FoodListAdapter foodListAdapter;
    ListView foodListView;
    TextView totalCostView;
    FirebaseFirestore db;

    // create activity result launcher if you want to get data back from subsequent activities
    // we do this because startActivityForResult() is deprecated.
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 1) {
                        Intent intent = result.getData();
                        if(intent != null) {
                            // extract data
                            foodBook = (FoodBook) intent.getSerializableExtra("foodBook");
                            foodList = foodBook.getFoodList();
                            // basically recreate all views
                            foodListAdapter = new FoodListAdapter(MainActivity.this, foodList);
                            foodListView.setAdapter(foodListAdapter);
                            SpannableString costString = new SpannableString(totalCostView.getText().subSequence(0, 11) + foodBook.getTotalCost().toString());
                            StyleSpan boldItalicSpan = new StyleSpan(Typeface.BOLD_ITALIC);
                            costString.setSpan(boldItalicSpan, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            totalCostView.setText(costString);
                            MainActivity.this.update();
                        }
                    }
                }
            }
    );

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
        // create view for total cost (actually set footer view for listview)
        totalCostView = (TextView) getLayoutInflater().inflate(R.layout.main_footer, foodListView, false);
        foodListView.addFooterView(totalCostView);
        // set Adapter for the view
        foodListView.setAdapter(foodListAdapter);

        db = FirebaseFirestore.getInstance();

        this.update();

        // Set what happens when we click an Item in ListView
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // bring us to InfoActivity
                Intent intent = new Intent(view.getContext(), InfoActivity.class);
                foodBook.setPosition(i);
                intent.putExtra("foodBook", foodBook);
                activityResultLauncher.launch(intent);
            }
        });

        // Make part of total cost bold and italic
        SpannableString costString = new SpannableString(totalCostView.getText().subSequence(0, 11) + foodBook.getTotalCost().toString());
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.add_button) {
            Intent intent = new Intent(this, AddEditActivity.class);
            foodBook.setPosition(-1);
            intent.putExtra("foodBook", foodBook);
            activityResultLauncher.launch(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void update() {
        for(int i = 0; i < this.foodList.size(); i++) {
            Food food = this.foodList.get(i);
            db.collection("FoodBook").document(food.toString()).set(food);
        }
    }
}