package com.example.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String MenuItemsData[], MenuItemsDescriptionData[], MenuItemsPricesData[], itemsTimeStr[];
    private int images[], itemsCnt[];
    private Context context;
    private TextView total;

    public MyAdapter(Context context, String MenuItemsData[], String MenuItemsDescriptionData[], String MenuItemsPricesData[], String itemsTimeStr[],
                     int images[], TextView total) {
        this.context = context;
        this.MenuItemsData = MenuItemsData;
        this.MenuItemsDescriptionData = MenuItemsDescriptionData;
        this.MenuItemsPricesData = MenuItemsPricesData;
        this.itemsTimeStr = itemsTimeStr;
        this.images = images;
        this.total = total;
        itemsCnt = new int[images.length];
        for (int i = 0; i < itemsCnt.length; i++)
            itemsCnt[i] = 0;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.itemTitleNameTxt.setText(MenuItemsData[position]);
        holder.itemDescriptionTxt.setText(MenuItemsDescriptionData[position]);
        holder.itemPriceTxt.setText(MenuItemsPricesData[position]);
        holder.myImage.setImageResource(images[position]);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemsCnt[position]++;
                holder.itemCntrTxt.setText(String.valueOf(Integer.parseInt(holder.itemCntrTxt.getText().toString()) + 1));
                total.setText(String.valueOf(Integer.parseInt(holder.itemPriceTxt.getText().toString()) + Integer.parseInt(total.getText().toString())));
            }
        });
        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemsCnt[position] > 0)
                    itemsCnt[position]--;
                int totalPrice = Integer.parseInt(total.getText().toString());
                int priceOfItem = Integer.parseInt(holder.itemPriceTxt.getText().toString());
                int cntrOfItem = Integer.parseInt(holder.itemCntrTxt.getText().toString());
                if (cntrOfItem == 0) priceOfItem = 0;
                total.setText(String.valueOf(Math.max(totalPrice - priceOfItem, 0)));
                holder.itemCntrTxt.setText(String.valueOf(Math.max(Integer.parseInt(holder.itemCntrTxt.getText().toString()) - 1, 0)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return MenuItemsData.length;
    }

    public int getMaxTime() {
        int mx = -1;
        for (int i = 0; i < itemsCnt.length; i++) {
            if (itemsCnt[i] > 0)
                mx = Math.max(mx, Integer.parseInt(itemsTimeStr[i]));
        }
        return mx;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitleNameTxt, itemDescriptionTxt, itemPriceTxt, itemCntrTxt;
        ImageView myImage;
        Button addBtn;
        Button minusBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitleNameTxt = itemView.findViewById(R.id.TitleName_txt);
            itemDescriptionTxt = itemView.findViewById(R.id.DescriptionText);
            itemPriceTxt = itemView.findViewById(R.id.priceText);
            myImage = itemView.findViewById(R.id.image_img);
            itemCntrTxt = itemView.findViewById(R.id.cntrText);
            addBtn = itemView.findViewById(R.id.addBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn);
        }
    }
}