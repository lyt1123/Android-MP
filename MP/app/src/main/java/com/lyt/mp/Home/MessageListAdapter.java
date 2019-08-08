package com.lyt.mp.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lyt.mp.R;

import java.util.ArrayList;
import java.util.Map;

public class MessageListAdapter extends BaseAdapter {

    private Context mContent;
    private ArrayList<Map> listItems;

    public MessageListAdapter(Context context, ArrayList<Map> listItems) {
        this.mContent = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContent).inflate(R.layout.message_listitem_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.timeTV = convertView.findViewById(R.id.message_time);
            viewHolder.titleTV = convertView.findViewById(R.id.message_title);
            viewHolder.contentTV = convertView.findViewById(R.id.message_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Map<String,String> map = listItems.get(position);
        viewHolder.timeTV.setText(map.get("time"));
        viewHolder.titleTV.setText(map.get("title"));
        viewHolder.contentTV.setText(map.get("content"));
        return convertView;
    }

    public class ViewHolder {
        TextView timeTV;
        TextView titleTV;
        TextView contentTV;
    }
}
