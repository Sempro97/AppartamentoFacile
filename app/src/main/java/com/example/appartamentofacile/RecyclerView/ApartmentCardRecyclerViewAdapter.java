package com.example.appartamentofacile.RecyclerView;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.R;
import com.example.appartamentofacile.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class ApartmentCardRecyclerViewAdapter extends RecyclerView.Adapter<ApartmentCardViewHolder> implements Filterable {

    private static final String LOG = "CardAdapter";
    //list that can be filtered
    private List <CardItem> cardItemList = new ArrayList<>();
    //list that contains ALL the element added by the user
    private List <CardItem> cardOfUser = new ArrayList<>();
    //i need the activity to get the drawable for the image
    private Activity activity;
    //nedd to get user specific card
    private User user;

    private Filter cardFilter = new Filter() {
        /**
         * Called to filter the data according to the constraint
         * @param constraint constraint used to filtered the data
         * @return the result of the filtering
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CardItem> filteredList = new ArrayList<>();

            //if you have no constraint --> return the full list
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(cardOfUser);
            } else {
                //else apply the filter and return a filtered list
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CardItem item : cardOfUser) {
                    if (item.getDescription().toLowerCase().contains(filterPattern) ||
                            item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        /**
         * Called to publish the filtering results in the user interface
         * @param constraint constraint used to filter the data
         * @param results the result of the filtering
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cardItemList.clear();
            //cardItemList.addAll((List) results.values);
            List<?> result = (List<?>) results.values;
            for (Object object : result) {
                if (object instanceof CardItem) {
                    cardItemList.add((CardItem) object);
                }
            }
            //warn the adapter that the dare are changed after the filtering
            notifyDataSetChanged();
        }
    };

    public ApartmentCardRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ApartmentCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.af_apartment_card,
                parent, false);
        return new ApartmentCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentCardViewHolder holder, int position) {
            final CardItem currentCardItem = cardItemList.get(position);
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
    public Filter getFilter() {
        return cardFilter;
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public void setData(List<CardItem> newData) {
        this.cardItemList.clear();
        this.cardItemList.addAll(newData);
        this.cardOfUser.clear();
        this.cardOfUser.addAll(newData);
        notifyDataSetChanged();
    }
}
