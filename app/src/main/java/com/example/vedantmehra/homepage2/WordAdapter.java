package com.example.vedantmehra.homepage2;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Word} objects.
 */
public class WordAdapter extends ArrayAdapter<Word>  {

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;

    /**
     * Create a new {@link WordAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param words is the list of {@link Word}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // EventPage if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        if(currentWord.getRequestStatus() == 0) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText(currentWord.getHeader());

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText(currentWord.getSubHeader());

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(currentWord.getImageResourceId());


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        }
        else
        if(currentWord.getRequestStatus() == 1) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText("You have accepted " + currentWord.getSubHeader() + "'s request");

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText("");

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.accept);


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        }

        else
        if(currentWord.getRequestStatus() == 2) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText("You have rejected " + currentWord.getSubHeader() + "'s request");

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText("");

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.reject);


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        }
        else
        if(currentWord.getRequestStatus() == 4) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText(currentWord.getHeader());

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText(currentWord.getSubHeader());

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(currentWord.getImageResourceId());


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        }
        else
        if(currentWord.getRequestStatus() == 5) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText("You have accepted " + currentWord.getSubHeader() + "'s amount " + currentWord.amount);

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText("");

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.accept);


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        }
        else
        if(currentWord.getRequestStatus() == 6) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText("You have rejected " + currentWord.getSubHeader() + "'s amount " + currentWord.amount);

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText("");

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.reject);


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        }
        else
        if(currentWord.getRequestStatus() == 7) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText("friend request to " + currentWord.getSubHeader() + " has been accepted ");

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText("");

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.accept);


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        }
        else
        if(currentWord.getRequestStatus() == 8) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText("money request to " + currentWord.getSubHeader() + " has been accepted ");

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText("");

            // Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.accept);


            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        } else if (currentWord.getRequestStatus() == 12) {
            TextView head = (TextView) listItemView.findViewById(R.id.head);
            head.setText("");

            TextView sub = (TextView) listItemView.findViewById(R.id.subhead);
            sub.setText("");

            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            imageView.setVisibility(View.GONE);

            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);


            // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
            // the ListView.
            return listItemView;
        }


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
