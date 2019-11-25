package com.baw.day1120_ymn_demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baw.day1120_ymn_demo1.bean.StudentBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Copyright (C)
 * <p>
 * FileName: MyBaseAdapter
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 14:18
 * <p>
 * Description:
 */
public class MyBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<StudentBean.ResultBean> result;
    private final int One_Type = 1;
    private final int Two_Type = 2;

    public MyBaseAdapter(Context context, List<StudentBean.ResultBean> result) {

        this.context = context;
        this.result = result;
    }

    //用于找布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
        switch (viewType) {
            case One_Type:
                view = LayoutInflater.from(context).inflate(R.layout.one_item, parent, false);
                holder = new OneHolder(view);
                break;
            case Two_Type:
                view = LayoutInflater.from(context).inflate(R.layout.two_item, parent, false);
                holder = new TwoHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OneHolder) {
            Glide.with(context).load(result.get(position).getMasterPic()).into(((OneHolder) holder).iv);
            ((OneHolder) holder).name.setText(result.get(position).getCommodityName());
            ((OneHolder) holder).price.setText(result.get(position).getPrice() + "");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cilck != null) {
                        cilck.onSetCilck(holder.itemView, position);
                    }
                }
            });
        } else if (holder instanceof TwoHolder) {
            Glide.with(context).load(result.get(position).getMasterPic()).into(((TwoHolder) holder).iv);
            ((TwoHolder) holder).name.setText(result.get(position).getCommodityName());
            ((TwoHolder) holder).price.setText(result.get(position).getPrice() + "");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cilck != null) {
                        cilck.onSetCilck(holder.itemView, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class OneHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private ImageView iv;

        public OneHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    class TwoHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private ImageView iv;

        public TwoHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //int saleNum = result.get(position).getSaleNum();
        int commodityId = result.get(position).getCommodityId();
        if (commodityId % 2 == 0) {
            return One_Type;
        } else {
            return Two_Type;
        }
    }

    //首先定义一个接口
    public interface Cilck {
        void onSetCilck(View v, int p);
    }

    private Cilck cilck;

    public void setItemCilck(Cilck cilck) {
        this.cilck = cilck;
    }
}
