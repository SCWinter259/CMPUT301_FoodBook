package com.example.hoangtru_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddEditActivity extends AppCompatActivity {
    FoodBook foodBook;
    Food food;
    EditText editName;
    EditText editDescription;
    EditText editBestBefore;
    Spinner editLocation;
    EditText editCount;
    EditText editCost;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        // get the intent
        foodBook = (FoodBook) getIntent().getSerializableExtra("foodBook");
        food = foodBook.getFood();

        // find the views
        editName = findViewById(R.id.edit_name);
        editDescription = findViewById(R.id.edit_description);
        editBestBefore = findViewById(R.id.edit_bestBefore);
        editLocation = findViewById(R.id.edit_location);
        editCount = findViewById(R.id.edit_count);
        editCost = findViewById(R.id.edit_cost);
        cancelButton = findViewById(R.id.edit_cancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // get the locations for spinner
        String[] locations = foodBook.getLocations();

        // create adapter for the spinner
        ArrayAdapter locationsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locations);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editLocation.setAdapter(locationsAdapter);

        // set info into fields if a food is selected
        if(food != null) {
            // set info of selected food
            editName.setText(food.getName());
            editDescription.setText(food.getDescription());
            editBestBefore.setText(food.getBestBefore());

            // determine the selected location
            int pos = 0;
            for(int i = 0; i < locations.length; i++) {
                if(food.getLocation().equals(locations[i])) {
                    pos = i;
                    break;
                }
            }
            editLocation.setSelection(pos);

            editCount.setText(food.getCount());
            editCost.setText(food.getCost());
        }
        else {
            food = new Food();
        }

        // getting input and set them to food object
        food.setName(editName.getText().toString());
        food.setDescription(editDescription.getText().toString());
        food.setBestBefore(editBestBefore.getText().toString());
        // control spinner action - set location
        editLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                food.setLocation(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                food.setLocation(adapterView.getItemAtPosition(0).toString());
            }
        });

        // parseInt require try-catch
        try{
            Integer count = Integer.parseInt(editCount.getText().toString());
            Integer cost = Integer.parseInt(editCost.getText().toString());
            food.setCount(count);
            food.setCost(cost);
        } catch(NumberFormatException ex){ // handle your exception
            ex.printStackTrace();
        }
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.main_menu is a reference to an xml file named main_menu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a Android resource directory named "menu" inside res
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle the OK button on the activity bar
    public void onOKClick(MenuItem item) {
        if(foodBook.getFood() == null) {
            foodBook.addFood(food);
        }
        else {
            foodBook.replaceFood(food);
        }
        finish();
    }
}