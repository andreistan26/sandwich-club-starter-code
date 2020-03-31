package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();
        String mainName = "";
        String description = "";
        String placeOfOrigin = "";
        String image = "";
        List<String>lsAlsoKnownAs = new ArrayList<>();
        List<String>lsIngredients = new ArrayList<>();


        try{
            JSONObject jobj = new JSONObject(json);

            JSONObject objName = jobj.getJSONObject("name");
            mainName = objName.getString("mainName");

            JSONArray alsoKnownAs = objName.getJSONArray("alsoKnownAs");
            for(int i=0 ; i<alsoKnownAs.length() ; i++){
                lsAlsoKnownAs.add(alsoKnownAs.getString(i));
            }

            JSONArray ingredients = jobj.getJSONArray("ingredients");

            for(int i=0 ; i<ingredients.length() ; i++){
                lsIngredients.add(ingredients.getString(i));
            }

            description = jobj.getString("description");
            image = jobj.getString("image");
            placeOfOrigin = jobj.getString("placeOfOrigin");


        }catch (JSONException e){
            e.printStackTrace();
        }

        sandwich.setAlsoKnownAs(lsAlsoKnownAs);
        sandwich.setImage(image);
        sandwich.setMainName(mainName);
        sandwich.setIngredients(lsIngredients);
        sandwich.setPlaceOfOrigin(placeOfOrigin);
        sandwich.setDescription(description);

        return sandwich;
    }

}
