package edu.miracostacollege.cs134.wheretonext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.io.IOException;
import java.util.List;

import edu.miracostacollege.cs134.wheretonext.model.College;
import edu.miracostacollege.cs134.wheretonext.model.JSONLoader;

public class MainActivity extends AppCompatActivity {

    //private DBHelper db;
    private List<College> collegesList;
    private CollegeListAdapter collegesListAdapter;
    private ListView collegesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.deleteDatabase(DBHelper.DATABASE_NAME);
        //db = new DBHelper(this);

        // TODO: Comment this section out once the colleges below have been added to the database,
        // TODO: so they are not added multiple times (prevent duplicate entries)
        /*
        db.addCollege(new College("UC Berkeley", 42082, 14068, 4.7, "ucb.png"));
        db.addCollege(new College("UC Irvine", 31551, 15026.47, 4.3, "uci.png"));
        db.addCollege(new College("UC Los Angeles", 43301, 25308, 4.5, "ucla.png"));
        db.addCollege(new College("UC San Diego", 33735, 20212, 4.4, "ucsd.png"));
        db.addCollege(new College("CSU Fullerton", 38948, 6437, 4.5, "csuf.png"));
        db.addCollege(new College("CSU Long Beach", 37430, 6452, 4.4, "csulb.png"));

         */


        // TODO:  Fill the collegesList with all Colleges from the database
        try
        {
            collegesList = JSONLoader.loadJSONFromAsset(this);
        }
        catch (IOException e)
        {
            Log.e("WhereToNext", "Error loading from JSON.", e);
        }

        // TODO:  Connect the list adapter with the list
        collegesListView = findViewById(R.id.collegeListView);

        collegesListAdapter = new CollegeListAdapter(this,R.layout.college_list_item, collegesList);

        // TODO:  Set the list view to use the list adapter
        collegesListView.setAdapter(collegesListAdapter);



    }

    public void viewCollegeDetails(View view)
    {
        // Extract the tag from the view
        College clickedCollege = (College) view.getTag();
        System.out.println("TUITION " + clickedCollege.getTuition());
        System.out.println("RATING " + clickedCollege.getRating());

        // Set up the intent
        Intent intent = new Intent(this, CollegeDetailsActivity.class);

        // Fill intent with details from the clickedCollege
        intent.putExtra("Name", clickedCollege.getName());
        intent.putExtra("Population", clickedCollege.getPopulation());
        intent.putExtra("Tuition", clickedCollege.getTuition());
        intent.putExtra("Rating", clickedCollege.getRating());
        intent.putExtra("ImageName", clickedCollege.getImageName());

        startActivity(intent);

        // TODO: Implement the view college details using an Intent
    }

    public void addCollege(View view) {

        // TODO: Implement the code for when the user clicks on the addCollegeButton
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText populationgEditText = findViewById(R.id.populationEditText);
        EditText tuitionEditText = findViewById(R.id.tuitionEditText);
        RatingBar collegeRatingBar = findViewById(R.id.collegeRatingBar);

        String name = nameEditText.getText().toString();
        int population = Integer.parseInt(populationgEditText.getText().toString());
        float tuition = Float.parseFloat(tuitionEditText.getText().toString());
        float rating = collegeRatingBar.getRating();

        collegesList.add(new College(name, population, tuition, rating));
    }

}
