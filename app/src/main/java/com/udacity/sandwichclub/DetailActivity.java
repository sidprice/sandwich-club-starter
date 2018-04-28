package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

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
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            sandwich = null ;
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /*
        Method to build a comma separated string from an input List<string>
     */

    private String listToString(List<String> input) {
        String stringResult = "" ;
        boolean isFirst = true ;
        if (input.size() != 0) {
            for ( int i = 0 ; i < input.size() ; i++) {
                if (isFirst == false) {
                    stringResult += ", " ;
                }
                isFirst = false ;
                stringResult += input.get(i) ;
            }
            /*
                terminate the new string
             */
            stringResult += "." ;
        }
        return stringResult ;
    }
    private void populateUI(Sandwich sandwich) {
        /*
            Also known as ... Mark the label and text as "gone"
            if there are no other names
         */
        TextView tv_temp = findViewById(R.id.also_known_tv);
        if ( sandwich.getAlsoKnownAs().size() == 0) {
            tv_temp.setVisibility(View.GONE);
            tv_temp = findViewById(R.id.tv_detail_also_known_as_label) ;
            tv_temp.setVisibility(View.GONE);
        } else {
            tv_temp.setText(listToString(sandwich.getAlsoKnownAs()));
            tv_temp = findViewById(R.id.tv_detail_also_known_as_label) ;
            tv_temp.setVisibility(View.VISIBLE);
        }
        /*
            Description
         */
        tv_temp = findViewById(R.id.description_tv) ;
        tv_temp.setText(sandwich.getDescription());
        /*
            Ingredients
         */
        tv_temp = findViewById(R.id.ingredients_tv) ;
        tv_temp.setText(listToString(sandwich.getIngredients()));
        /*
            Place of origin
         */
        tv_temp = findViewById(R.id.place_of_origin_tv) ;
        tv_temp.setText(sandwich.getPlaceOfOrigin());
    }
}
