package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich    sandwichObject = null   ;

        try {
            JSONObject jsonSandwich = new JSONObject(json) ;
            sandwichObject = new Sandwich() ;
            /*
             Get the 'name' object from the input json object
             */
            JSONObject  nameObject = jsonSandwich.getJSONObject("name");
            /*
             set the main name of this meal
             */
            sandwichObject.setMainName(nameObject.getString("mainName"));
            /*
             * 'alsoKnownAs' is an array of strings
             */
            JSONArray jsonArrayOfStrings    = nameObject.getJSONArray("alsoKnownAs") ;
            /*
             * add other names to 'alsoKnownAs'
             */
            ArrayList<String> alsoKnownAs = new ArrayList<String>() ;
            for ( int i = 0 ; i < jsonArrayOfStrings.length() ; i++) {
                alsoKnownAs.add(jsonArrayOfStrings.getString(i)) ;
            }
            sandwichObject.setAlsoKnownAs(alsoKnownAs);
            sandwichObject.setPlaceOfOrigin(jsonSandwich.getString("placeOfOrigin")) ;
            sandwichObject.setDescription(jsonSandwich.getString("description")) ;
            sandwichObject.setImage(jsonSandwich.getString("image")) ;
            /*
                Get the 'ingredients' object
             */
            JSONArray   jsonArrayOfIngredients = jsonSandwich.getJSONArray("ingredients") ;
            /*
                Add any ingedients to 'ingredients'
             */
            ArrayList<String> ingredients = new ArrayList<String>() ;
            for (int i = 0 ; i < jsonArrayOfIngredients.length() ; i++) {
                ingredients.add(jsonArrayOfIngredients.getString(i)) ;
            }
            sandwichObject.setIngredients(ingredients);
        } catch (JSONException ex) {
            sandwichObject = null ;
        }
        return sandwichObject;
    }
}
