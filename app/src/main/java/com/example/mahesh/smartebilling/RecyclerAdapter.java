package com.example.mahesh.smartebilling;

/**
 * Created by MANDEEP on 2/25/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> products = Globals.getProducts();
    private ArrayList<Integer> counts = Globals.getCounts();
    private ArrayList<Float> prices = Globals.getPrices();
    private  ArrayList<Float> weights = Globals.getWeights();
    TextView total;

    public RecyclerAdapter(TextView editText){
        this.total = editText;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productname, producquantity, productcost, productweight;
        Button remove;

        public ViewHolder(View itemView) {
            super(itemView);
            productname = (TextView) itemView.findViewById(R.id.productname);
            producquantity = (TextView) itemView.findViewById(R.id.productquantity);
            productcost = (TextView) itemView.findViewById(R.id.productcost);
            productweight = (TextView) itemView.findViewById(R.id.productweight);
            remove = (Button) itemView.findViewById(R.id.remove);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeAt(getAdapterPosition());
                    total.setText(Integer.toString(Globals.getTotalPriceOnCart()));
                }
            });
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
        viewHolder.productname.setText(products.get(i));
        viewHolder.producquantity.setText(counts.get(i)+" qty");
        viewHolder.productcost.setText("Rs. "+prices.get(i));
        viewHolder.productweight.setText(weights.get(i)+" gm");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void removeAt(int position) {
        products.remove(position);
        prices.remove(position);
        counts.remove(position);
        weights.remove(position);
        Globals.cart.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, products.size());
    }
}
