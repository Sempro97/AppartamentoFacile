package com.example.appartamentofacile.RecyclerView;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.R;
import com.example.appartamentofacile.User;
import com.example.appartamentofacile.UserWithCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter used to show a simple grid of products.
 */
public class ApartmentCardRecyclerViewAdapter extends RecyclerView.Adapter<ApartmentCardViewHolder> {

    private static final String LOG = "CardAdapter";
    //list that contains ALL the element added by the user
    private List <CardItem> cardOfUser = new ArrayList<>();
    //i need the activity to get the drawable for the image
    private Activity activity;
    //nedd to get user specific card
    private User user;

    public ApartmentCardRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ApartmentCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.af_apartament_card,
                parent, false);
        return new ApartmentCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentCardViewHolder holder, int position) {
            final CardItem currentCardItem = cardOfUser.get(position);
            holder.startDateTextView.setText(currentCardItem.getStartDate());
            holder.endDateTextView.setText(currentCardItem.getEndDate());
            holder.title.setText(currentCardItem.getTitle());
            holder.descriptionTextView.setText(currentCardItem.getDescription());
    }

    public void updateList() {
        Log.d(LOG, "updateList()");
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cardOfUser.size();
    }

    public void setData(List<CardItem> newData) {
        this.cardOfUser.clear();
        this.cardOfUser.addAll(newData);
        notifyDataSetChanged();
    }
}
