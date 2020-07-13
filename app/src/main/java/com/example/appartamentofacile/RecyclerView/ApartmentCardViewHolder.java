package com.example.appartamentofacile.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appartamentofacile.R;

public class ApartmentCardViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView descriptionTextView;
    TextView startDateTextView;
    TextView endDateTextView;


    public ApartmentCardViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title_card_description);
        startDateTextView = itemView.findViewById(R.id.startDateTextView);
        endDateTextView = itemView.findViewById(R.id.endDateTextView);
        descriptionTextView = itemView.findViewById(R.id.description_card);

    }
}
