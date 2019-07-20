package com.lyt.mp.Mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Map;

public class MineListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Map> listItems;

    public MineListAdapter(Context mContent, ArrayList<Map> listItems){
        this.context = mContent;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
