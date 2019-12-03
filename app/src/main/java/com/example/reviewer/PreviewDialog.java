package com.example.reviewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Locale;

public class PreviewDialog extends AppCompatDialogFragment {

    private TextView gameTitle,
                     reviewBody,
                     gameRating;
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
                .setTitle("Preview")
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int     game = 1,        // TBD where from
                                rating = Integer.parseInt(gameRating.getText().toString());

                        String  review = reviewBody.getText().toString();

                        listener.applyTexts(game, rating, review);
                    }
                });

        gameTitle = view.findViewById(R.id.game_title);
        reviewBody = view.findViewById(R.id.review_text);
        gameRating = view.findViewById(R.id.game_rating);

        gameTitle.setText(getArguments().getString("gameTitle"));
        reviewBody.setText(getArguments().getString("reviewBody"));
        gameRating.setText(String.format(Locale.ENGLISH, "%d", getArguments().getInt("gameRating")) + " Stars");

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
