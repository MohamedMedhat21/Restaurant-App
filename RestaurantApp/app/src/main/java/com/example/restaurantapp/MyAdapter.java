package com.example.restaurantapp;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    String data[], data2[], data3[];
    int images[];
    Context context;
    TextView total;

    public MyAdapter(Context context,String s1[], String desc[], String prices[],int arrImg[], TextView total){
        data = s1;
        data2 = desc;
        data3 = prices;
        images = arrImg;
        this.context = context;
        this.total = total;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.txt1.setText(data[position]);
        holder.txt2.setText(data2[position]);
        holder.txt3.setText(data3[position]);
        holder.myImage.setImageResource(images[position]);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cntr.setText(String.valueOf(Integer.parseInt(holder.cntr.getText().toString()) + 1));
                total.setText(String.valueOf(Integer.parseInt(holder.txt3.getText().toString()) + Integer.parseInt(total.getText().toString())));
            }
        });
        holder.minusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int totalPrice = Integer.parseInt(total.getText().toString());
                int PriceOfItem = Integer.parseInt(holder.txt3.getText().toString());
                int cntrOfItem = Integer.parseInt(holder.cntr.getText().toString());
                if(cntrOfItem == 0) PriceOfItem = 0;
                total.setText(String.valueOf(Math.max(totalPrice - PriceOfItem, 0)));
                holder.cntr.setText(String.valueOf(Math.max(Integer.parseInt(holder.cntr.getText().toString()) - 1 , 0)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt1, txt2, txt3, cntr;
        ImageView myImage;
        Button addBtn;
        Button minusBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.TitleName_txt);
            txt2 = itemView.findViewById(R.id.DescriptionText);
            txt3 = itemView.findViewById(R.id.priceText);
            myImage = itemView.findViewById(R.id.image_img);
            cntr = itemView.findViewById(R.id.cntrText);
            addBtn = itemView.findViewById(R.id.addBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn);

        }

    }
}