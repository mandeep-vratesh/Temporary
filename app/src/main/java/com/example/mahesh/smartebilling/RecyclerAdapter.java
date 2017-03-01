package com.example.mahesh.smartebilling;

/**
 * Created by MANDEEP on 2/25/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] products = Globals.getProducts();
    private int[] counts = Globals.getCounts();
    private float[] prices = Globals.getPrices();

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productname, producquantity, productcost;

        public ViewHolder(View itemView) {
            super(itemView);
            productname = (TextView) itemView.findViewById(R.id.productname);
            producquantity = (TextView) itemView.findViewById(R.id.productquantity);
            productcost = (TextView) itemView.findViewById(R.id.productcost);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.productname.setText(products[i]);
        viewHolder.producquantity.setText(counts[i]+" qty");
        viewHolder.productcost.setText("Rs. "+prices[i]);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }
}
