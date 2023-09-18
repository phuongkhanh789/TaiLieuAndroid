package com.example.quanlycongviec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(Context context, int layout, List<CongViec> congViecList) {
        this.context = context;
        Layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int position) {
        return congViecList.get(position);
    }

    @Override
    public long getItemId(int position) {
        CongViec congViec = (CongViec) congViecList.get(position);
        return congViec.getId();
    }
    public class Viewholder{
        TextView txtId;
        TextView txtTen;
        TextView txtCongViec;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if (convertView==null){
            holder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(Layout,null);
            holder.txtId = convertView.findViewById(R.id.txtViewID);
            holder.txtTen = convertView.findViewById(R.id.txtViewTenID);
            holder.txtCongViec = convertView.findViewById(R.id.txtViewTGThucHienID);
            convertView.setTag(holder);
        } else {
            holder =(Viewholder) convertView.getTag();
        }
        CongViec congViec = (CongViec) congViecList.get(position);
        holder.txtId.setText(congViec.toString());
        holder.txtTen.setText(congViec.getTenCV());
        holder.txtCongViec.setText(String.valueOf(congViec.getThoiGianThucHien()));

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteCongViec(congViec);
                return true;
            }
        });

        return convertView;
    }
    private void deleteCongViec(CongViec congViec) {

        congViecList.remove(congViec);
        notifyDataSetChanged();
    }
}
