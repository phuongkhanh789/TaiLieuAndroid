package com.example.testthu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DanhBaAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<DanhBa> danhBaList;

    public  DanhBaAdapter(Context context, int layout, List danhBaList){
        this.context = context;
        Layout = layout;
        this.danhBaList = danhBaList;
    }

    @Override
    public int getCount() {
        return danhBaList.size();
    }

    @Override
    public Object getItem(int position) {
        return danhBaList.get(position);

    }

    @Override
    public long getItemId(int position) {
        DanhBa danhBa = (DanhBa) danhBaList.get(position);
        return danhBa.getId();
    }

    private class Viewholder {
        TextView txtId;
        TextView txtTen;
        TextView txtDienThoai;
    }
    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        Viewholder holder;
        if(convertView == null){
            holder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(Layout,null);
            holder.txtId = convertView.findViewById(R.id.txtViewID);
            holder.txtTen = convertView.findViewById(R.id.txtViewTenID);
            holder.txtDienThoai = convertView.findViewById((R.id.txtViewSoDienThoaiID));
            convertView.setTag(holder);
        }else{
            holder =(Viewholder) convertView.getTag();
        }

        DanhBa danhBa = (DanhBa) danhBaList.get(position);
        holder.txtId .setText(danhBa.toString());
        holder.txtTen.setText(danhBa.getTen());
        holder.txtDienThoai.setText(String.valueOf(danhBa.getSoDienThoai()));

        return convertView;
    }
}
