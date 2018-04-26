package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich    sandwichObject = null   ;

        try {
            JSONObject jsonSandwich = new JSONObject(json) ;
            sandwichObject = new Sandwich(jsonSandwich) ;
        } catch (JSONException ex) {
            sandwichObject = null ;
        }
        return sandwichObject;
    }
}
