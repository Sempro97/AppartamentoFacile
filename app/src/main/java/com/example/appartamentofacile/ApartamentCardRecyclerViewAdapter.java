package com.example.appartamentofacile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appartamentofacile.network.ImageRequester;
import com.example.appartamentofacile.network.ProductEntry;

import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class ApartamentCardRecyclerViewAdapter extends RecyclerView.Adapter<ApartamentCardViewHolder> {

    private List<ProductEntry> productList;
    private ImageRequester imageRequester;

    ApartamentCardRecyclerViewAdapter(List<ProductEntry> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ApartamentCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.af_apartament_card, parent, false);
        return new ApartamentCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApartamentCardViewHolder holder, int position) {
        // TODO: Put ViewHolder binding code here in MDC-102
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
