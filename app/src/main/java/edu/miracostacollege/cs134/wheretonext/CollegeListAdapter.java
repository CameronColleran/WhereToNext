package edu.miracostacollege.cs134.wheretonext;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.cs134.wheretonext.model.College;

/**
 * Helper class to provide custom adapter for the <code>College</code> list.
 */
public class CollegeListAdapter extends ArrayAdapter<College>
{

    private Context mContext;
    private List<College> mCollegesList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>CollegeListAdapter</code> given a mContext, resource id and list of colleges.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param colleges The list of colleges to display
     */
    public CollegeListAdapter(Context c, int rId, List<College> colleges) {
        super(c, rId, colleges);
        mContext = c;
        mResourceId = rId;
        mCollegesList = colleges;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the College selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);


        // TODO:  Write the code to correctly inflate the view (college_list_item) with
        // TODO:  all widgets filled with the appropriate College information.

        College selectedCollege = mCollegesList.get(pos);

        // Wire up linear layout
        LinearLayout collegeListLinearLayout = view.findViewById(R.id.collegeListLinearLayout);

        // Inflate data with selectedCollege data
        TextView collegeListNameTextView = view.findViewById(R.id.collegeListNameTextView);
        RatingBar collegeListRatingBar = view.findViewById(R.id.collegeListRatingBar);

        collegeListNameTextView.setText(selectedCollege.getName());
        collegeListRatingBar.setRating((float) selectedCollege.getRating());

        // Set up tag for linear layout to be the selected event
        collegeListLinearLayout.setTag(selectedCollege);

        // Wire up image view
        ImageView collegeListImageView = view.findViewById(R.id.collegeListImageView);

        AssetManager am = mContext.getAssets();

        try
        {
            InputStream stream = am.open(selectedCollege.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedCollege.getImageName());
            collegeListImageView.setImageDrawable(image);
        }
        catch (IOException e)
        {
            Log.e("Where To Next", "Error inflating image for " + selectedCollege.getName(), e);
        }

        return view;
    }
}
