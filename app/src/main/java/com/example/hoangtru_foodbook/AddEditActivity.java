package com.example.hoangtru_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddEditActivity extends AppCompatActivity {
    Food food;
    EditText editName;
    EditText editDescription;
    EditText editBestBefore;
    Spinner editLocation;
    EditText editCount;
    EditText editCost;
    Button cancel;
    Fridge fridge = new Fridge();
    Freezer freezer = new Freezer();
    Pantry pantry = new Pantry();
    ArrayList<Location> locations = new ArrayList<>();
    ArrayAdapter<CharSequence> locationsAdapter;
    private boolean edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        // create the spinner
        locations.add(fridge);
        locations.add(freezer);
        locations.add(pantry);

        locationsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locations);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editLocation.setAdapter(locationsAdapter);

        // create views
        editName = findViewById(R.id.edit_name);
        editDescription = findViewById(R.id.edit_description);
        editBestBefore = findViewById(R.id.edit_bestBefore);
        editLocation = findViewById(R.id.edit_location);
        editCount = findViewById(R.id.edit_count);
        editCost = findViewById(R.id.edit_cost);
        cancel = findViewById(R.id.edit_cancel);


        Intent intent = getIntent();

        if(intent.hasExtra("food")) {
            this.edit = true;
            // set the already chosen values
            food = (Food) intent.getSerializableExtra("food");
            editName.setText(food.getName());
            editDescription.setText(food.getDescription());
            editBestBefore.setText(food.getBestBefore().toString());
            Location location = food.getLocation();
            editLocation.setSelection(locationsAdapter.getPosition(location.toString()));
            editCount.setText(food.getCount());
            editCost.setText(food.getCost());
        }
        else {
            this.edit = false;
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onOKClick(MenuItem item) {
        if(this.edit) {
            String name = editName.getText().toString();
            String description = editDescription.getText().toString();
            String bestBefore = editBestBefore.getText().toString();
            Location location = locations.get(editLocation.getCount());
            Integer count = Integer.parseInt(editCount.getText().toString());
            Integer cost = Integer.parseInt(editCost.getText().toString());

            food.setName(name);
            food.setDescription(description);
            food.setBestBefore(bestBefore);
            food.setLocation(location);
            food.setCount(count);
            food.setCost(cost);
        }
        else {

        }
    }
}