package com.twe.android.master_detail;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Version> {
  private Context context;
  private ArrayList<Version> versions;

  public CustomAdapter(Context context, ArrayList<Version> versions) {
    super(context, 0, versions);
    this.context = context;
    this.versions = versions;
  }

  public int getCount() {
    return versions.size();
  }

  public Version getItem(int position) {
    return versions.get(position);
  }

  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
      convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.list_item, parent, false);
      holder = new ViewHolder();
      holder.code = (TextView) convertView.findViewById(R.id.code);
      holder.level = (TextView) convertView.findViewById(R.id.level);
      holder.icon = (ImageView) convertView.findViewById(R.id.icon);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    Version version = versions.get(position);
    if (version != null) {
      holder.code.setText(version.getCode());
      holder.level.setText(version.getLevel());
      holder.icon.setImageBitmap(version.getIcon());
    }
    return convertView;
  }

  @Override
  public Filter getFilter() {
    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        versions = DataCenter.get(getContext()).getList();
        if (charSequence == null || charSequence.length() == 0) {
          results.values = versions;
          results.count = versions.size();
        } else {
          ArrayList<Version> filterResultsData = new ArrayList<>();
          String keyword = charSequence.toString().toLowerCase();
          for (Version v : versions) {
            if (v.getCode().toLowerCase().contains(keyword)) {
              filterResultsData.add(v);
            }
          }
          results.values = filterResultsData;
          results.count = filterResultsData.size();
        }
        return results;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        if (filterResults.count != 0) {
          versions = (ArrayList<Version>) filterResults.values;
          notifyDataSetChanged();
        } else {
          notifyDataSetInvalidated();
        }
      }
    };
  }

  static class ViewHolder {
    TextView code;
    TextView level;
    ImageView icon;
  }
}