package com.example.qunlkhchhng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<KhachHang> khachHangList;

    public KhachHangAdapter(Context context, int layout, List<KhachHang> khachHangList) {
        this.context = context;
        Layout = layout;
        this.khachHangList = khachHangList;
    }

    @Override
    public int getCount() {
        return khachHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return khachHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        KhachHang khachHang = (KhachHang) khachHangList.get(position);
        return khachHang.getId();
    }
    public class Viewholder{
        TextView txtId;
        TextView txtTen;
        TextView txtDienThoai;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView == null){
            holder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(Layout,null);
            holder.txtId = convertView.findViewById(R.id.txtViewID);
            holder.txtTen = convertView.findViewById(R.id.txtViewTenID);
            holder.txtDienThoai = convertView.findViewById(R.id.txtViewSoDienThoaiID);
            convertView.setTag(holder);
        } else{
            holder = (Viewholder) convertView.getTag();
        }
        KhachHang khachHang = (KhachHang) khachHangList.get(position);
        holder.txtId.setText(khachHang.toString());
        holder.txtTen.setText(khachHang.getTen());
        holder.txtDienThoai.setText(String.valueOf(khachHang.getSoDienThoai()));
        return convertView;
    }
}
