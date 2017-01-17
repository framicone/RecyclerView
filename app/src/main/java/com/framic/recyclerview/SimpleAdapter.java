package com.framic.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SJ on 2017/1/16.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
    protected List<String> datas;
    private LayoutInflater layoutInflater;
    private Context context;

    public interface OnItemClicklistener {
        void OnItemClick(View view, int pos);

        void OnItemLongClick(View view, int pos);
    }

    private OnItemClicklistener mOnItemClicklistener;

    public void setOnItemClickListener(OnItemClicklistener listener) {
        this.mOnItemClicklistener = listener;
    }

    public SimpleAdapter(Context context, List datas) {
        this.datas = datas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.items, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(datas.get(position));
        if (mOnItemClicklistener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClicklistener.OnItemClick(holder.itemView, layoutPosition);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClicklistener.OnItemLongClick(holder.itemView, layoutPosition);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(int pos) {
        datas.add("Insert One");
        notifyItemInserted(pos);
    }

    public void delData(int pos) {
        datas.remove(pos);
        notifyItemRemoved(pos);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}


