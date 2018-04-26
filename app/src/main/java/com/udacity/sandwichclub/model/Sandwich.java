package com.udacity.sandwichclub.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }
    /*
     * Constructor taking a JSON object
     */
    public Sandwich(JSONObject jsonObject) throws JSONException {
        /*
         Get the 'name' object from ther input json object
         */
        JSONObject  nameObject = jsonObject.getJSONObject("name");
        /*
         get the main name of this meal
         */
        mainName = nameObject.getString("mainName") ;
        /*
         * 'alsoKnownAs' is an array of strings
         */
        JSONArray jsonArrayOfStrings    = nameObject.getJSONArray("alsoKnownAs") ;
        /*
         * add other names to 'alsoKnownAs'
         */
        alsoKnownAs = new ArrayList<String>() ;
        for ( int i = 0 ; i < jsonArrayOfStrings.length() ; i++) {
            alsoKnownAs.add(jsonArrayOfStrings.getString(i)) ;
        }
        placeOfOrigin = jsonObject.getString("placeOfOrigin") ;
        description = jsonObject.getString("description") ;
        image = jsonObject.getString("image") ;
        /*
            Get the 'ingredients' object
         */
        JSONArray   jsonArrayOfIngredients = jsonObject.getJSONArray("ingredients") ;
        /*
            Add any ingedients to 'ingredients'
         */
        ingredients = new ArrayList<String>() ;
        for (int i = 0 ; i < jsonArrayOfIngredients.length() ; i++) {
            ingredients.add(jsonArrayOfIngredients.getString(i)) ;
        }
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
