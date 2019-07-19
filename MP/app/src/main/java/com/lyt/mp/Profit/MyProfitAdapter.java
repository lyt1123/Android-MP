package com.lyt.mp.Profit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyt.mp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyProfitAdapter extends BaseAdapter {

    private Context mContent;
    private ArrayList<Map> listItems;

    public MyProfitAdapter(Context mContent,ArrayList<Map> data){
        this.mContent = mContent;
        listItems = data;
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
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContent).inflate(R.layout.profit_listitem_layout,null);

            viewHolder = new ViewHolder();
            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.title_tv);
            viewHolder.moneyTv = (TextView)convertView.findViewById(R.id.money_tv);
            viewHolder.iconImg = (ImageView)convertView.findViewById(R.id.listitem_image);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Map<String,String> listItem = listItems.get(position);

        viewHolder.titleTv.setText(listItem.get("title"));
        viewHolder.moneyTv.setText(listItem.get("money"));

        int resId = mContent.getResources().getIdentifier(listItem.get("icon"), "drawable", mContent.getPackageName());
        viewHolder.iconImg.setImageResource(resId);

        return convertView;
    }

    public class ViewHolder{
        TextView titleTv;
        TextView moneyTv;
        ImageView iconImg;
    }
}
