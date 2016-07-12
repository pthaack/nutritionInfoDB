package scs2682.philipyoung.finalproject.nutritioninfo.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by Philip Young on 2016-04-04.
 */
public class PickpageFoodAdapter extends Adapter<ViewHolder> {
    private final LayoutInflater layoutInflater;
    private final List<PickpageFoodModel> items;

    public PickpageFoodAdapter(@NonNull Context context, List<PickpageFoodModel> items) {
        layoutInflater = LayoutInflater.from(context);
        this.items = items != null ? items : Collections.<PickpageFoodModel>emptyList();

        // work great in case getItemId() below returns unique id
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(viewType, parent, false);
        ViewHolder viewHolder = new PickpageFoodElement(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if( holder instanceof PickpageFoodElement) {
            // only this type of holder needs to update
            ((PickpageFoodElement) holder).update(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).layoutId;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
