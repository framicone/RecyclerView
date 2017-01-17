package com.framic.recyclerview;

import android.content.Context;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by SJ on 2017/1/16.
 */

public class StaggeredAdapter extends SimpleAdapter{
    private List<Integer> heights;

    public StaggeredAdapter(Context context, List datas) {
        super(context,datas);

        heights = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            int rand = new Random().nextInt(300);
            heights.add(rand + 100);
        }

    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params=holder.itemView.getLayoutParams();
        params.height = heights.get(position);
        holder.itemView.setLayoutParams(params);
        holder.tv.setText(datas.get(position));
    }

}

