package edu.miracostacollege.cs134.wheretonext.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads College data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (College) with data.
 */
public class JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<College> loadJSONFromAsset(Context context) throws IOException {
        List<College> allCollegesList = new ArrayList<>();
        String json = null;
        InputStream is = context.getAssets().open("colleges.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray allCollegesJSON = jsonRootObject.getJSONArray("Colleges");

            // DONE: Loop through all the colleges in the JSON data, create a College object for each
            JSONObject collegeJSON;
            int count = allCollegesJSON.length();
            String imageName, name;
            int population;
            float tuition, rating;

            for (int i = 0; i < count; i++)
            {
                collegeJSON = allCollegesJSON.getJSONObject(i);
                imageName = collegeJSON.getString("FileName");
                name = collegeJSON.getString("Name");
                population = Integer.parseInt(collegeJSON.getString("Population"));
                tuition = Float.parseFloat(collegeJSON.getString("Tuition"));
                rating = Float.parseFloat(collegeJSON.getString("Rating"));

                // DONE: Add each college object to the list
                allCollegesList.add(new College(name, population, tuition, rating, imageName));
            }


        } catch (JSONException e) {
            Log.e("Flag Quiz", e.getMessage());
        }

        return allCollegesList;
    }
}
