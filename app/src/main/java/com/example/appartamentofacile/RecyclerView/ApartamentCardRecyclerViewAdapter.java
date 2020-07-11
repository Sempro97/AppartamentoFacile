package com.example.appartamentofacile.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appartamentofacile.Network.ImageRequester;
import com.example.appartamentofacile.Network.ProductEntry;
import com.example.appartamentofacile.R;

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
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
