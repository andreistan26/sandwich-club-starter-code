package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mAKA_tv, mPlaceOfOrigin_tv, mIngredients_tv, mDescription_tv;

    private String strAka, strPlaceOfOrigin, strIngredient, strDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        mAKA_tv = findViewById(R.id.also_known_tv);
        mPlaceOfOrigin_tv = findViewById(R.id.origin_tv);
        mIngredients_tv = findViewById(R.id.ingredients_tv);
        mDescription_tv = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable

            closeOnError();
            return;
        }
        strAka = sandwich.getAlsoKnownAs().toString();
        strDescription = sandwich.getDescription();
        strIngredient = sandwich.getIngredients().toString();
        strPlaceOfOrigin = sandwich.getPlaceOfOrigin();

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        populateUI();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        mAKA_tv.setText(strAka.subSequence(1,strAka.length()-1));
        mIngredients_tv.setText(strIngredient.subSequence(1,strIngredient.length()-1));
        mPlaceOfOrigin_tv.setText(strPlaceOfOrigin);
        mDescription_tv.setText(strDescription);


    }
}