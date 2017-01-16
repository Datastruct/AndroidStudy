package com.example.owner.myapplication.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.owner.myapplication.R;
import com.example.owner.myapplication.model.stnlistapi.LineList;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by owner on 2016-12-14.
 * StationListApdapter
 */
public class StationListAdapter extends ArrayAdapter<LineList> implements Filterable {
    ArrayList<LineList> originalList;
    ArrayList<LineList> stationList = new ArrayList<LineList>();    //filtered list
    Context mContext;
    private LayoutInflater mInflater;

    Filter mFilter;

    public StationListAdapter(Context context, ArrayList<LineList> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        stationList = objects;

        originalList = new ArrayList<LineList>();
        this.originalList.addAll(stationList);
    }

    @Override
    public LineList getItem(int position) {
        return stationList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // if not exist cashed view, make view and viewholder
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.search_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        // if exist cahsed view, use it
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvStnNm.setText(stationList.get(position).getStatnNm());
        viewHolder.tvLineNm.setText(stationList.get(position).getSubwayNm());
        viewHolder.tvCodeNm.setText(stationList.get(position).getStatnId());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.line_name)
        TextView tvLineNm;
        @BindView(R.id.station_name)
        TextView tvStnNm;
        @BindView(R.id.code_name)
        TextView tvCodeNm;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getCount() {
        return stationList.size();
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ListFilter();
        }

        return mFilter;
    }

    private class ListFilter extends Filter {

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub
            stationList = (ArrayList<LineList>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = stationList.size(); i < l; i++) {
                add(stationList.get(i));
                //Log.d("infoList.get(i)", infoList.get(i).str);
            }
            notifyDataSetInvalidated();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString();
            FilterResults result = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                ArrayList<LineList> filteredItems = new ArrayList<LineList>();
                for (int i = 0, l = originalList.size(); i < l; i++) {
                    LineList nameList = originalList.get(i);
                    if (nameList.getStatnNm().toString().contains(constraint))
                        filteredItems.add(nameList);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;

        }
    }

    public void sort() {
        Collections.sort(stationList, LineList.comparator);
        notifyDataSetChanged();
    }

}
