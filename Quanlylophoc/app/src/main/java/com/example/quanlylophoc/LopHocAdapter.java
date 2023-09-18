package com.example.quanlylophoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LopHocAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<LopHoc> lopHocList;

    public LopHocAdapter(Context context, int layout, List<LopHoc> lopHocList) {
        this.context = context;
        Layout = layout;
        this.lopHocList = lopHocList;
    }

    @Override
    public int getCount() {
        return lopHocList.size();
    }

    @Override
    public Object getItem(int position) {
        return lopHocList.get(position);
    }

    @Override
    public long getItemId(int position) {
        LopHoc lopHoc = (LopHoc) lopHocList.get(position);
        return lopHoc.getId();
    }
    public class Viewholder{
        TextView txtId;
        TextView txtTen;
        TextView txtSV;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView==null){
            holder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(Layout,null);
            holder.txtId = convertView.findViewById(R.id.txtViewID);
            holder.txtTen = convertView.findViewById(R.id.txtViewTenID);
            holder.txtSV = convertView.findViewById(R.id.txtViewSoSVID);
            convertView.setTag(holder);
        }else{
            holder = (Viewholder) convertView.getTag();
        }
        LopHoc lopHoc = (LopHoc) lopHocList.get(position);
        holder.txtId.setText(lopHoc.toString());
        holder.txtTen.setText(lopHoc.getTen());
        holder.txtSV.setText(String.valueOf(lopHoc.getSoSV()));
        return convertView;
    }
}
