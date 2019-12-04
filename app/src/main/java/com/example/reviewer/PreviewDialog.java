package com.example.reviewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;


public class PreviewDialog extends AppCompatDialogFragment {

    private TextView gameTitle,
                     reviewBody;
    private RatingBar rating;
    private PreviewDialogListener listener;

    static PreviewDialog newInstance(String gameTitle, String reviewBody, int rating) {
        PreviewDialog preview = new PreviewDialog();

        // Supply input as an arguments to display.
        Bundle args = new Bundle();
        args.putString("gameTitle", gameTitle);
        args.putString("reviewBody", reviewBody);
        args.putInt("gameRating", rating);
        preview.setArguments(args);

        return preview;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_preview, null);


        builder.setView(view)
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int     game = 1,        // TBD where from
                                rating = getArguments().getInt("gameRating");

                        String  review = reviewBody.getText().toString();

                        listener.applyTexts(game, rating, review);
                    }
                });

        gameTitle = view.findViewById(R.id.game_title);
        reviewBody = view.findViewById(R.id.review_text);
        rating = view.findViewById(R.id.rating_bar);

        gameTitle.setText(getArguments().getString("gameTitle"));
        reviewBody.setText(getArguments().getString("reviewBody"));
        rating.setRating((float)getArguments().getInt("gameRating"));
        //Drawable drawable = rating.getProgressDrawable();
        //drawable.setColorFilter(Color.parseColor("#FFC107"), PorterDuff.Mode.SRC_ATOP);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (PreviewDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement PreviewDialogListener");
        }
    }

    public interface PreviewDialogListener {
        void applyTexts(int gameID, int rating, String review);
    }

}
