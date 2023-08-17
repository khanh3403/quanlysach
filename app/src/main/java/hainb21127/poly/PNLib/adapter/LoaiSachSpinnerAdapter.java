package hainb21127.poly.PNLib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.obj.LoaiSach;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context,0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item_spinner, null);
        }
        final LoaiSach item = lists.get(position);
        if(item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSpn);
            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSpn);

            tvMaLoaiSach.setText(item.maLoai + ". ");
            tvTenLoaiSach.setText(item.tenLoai);
        }
        return v;
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item_spinner, null);
        }
        final LoaiSach item = lists.get(position);
        if(item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSpn);
            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSpn);

            tvMaLoaiSach.setText(item.maLoai + ". ");
            tvTenLoaiSach.setText(item.tenLoai);
        }
        return v;
    }
}
