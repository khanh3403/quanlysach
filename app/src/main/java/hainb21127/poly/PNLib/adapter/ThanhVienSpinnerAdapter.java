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
import hainb21127.poly.PNLib.obj.ThanhVien;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> lists;
    TextView tvMaTv, tvTenTV;
    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> lists) {
        super(context,0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item_spinner, null);
        }
        final ThanhVien item = lists.get(position);
        if(item != null){
            tvMaTv = v.findViewById(R.id.tvMaTVSpn);
            tvTenTV = v.findViewById(R.id.tvTenTVSpn);

            tvMaTv.setText(item.maTV + ". ");
            tvTenTV.setText(item.hoTen);
        }
        return v;
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item_spinner, null);
        }
        final ThanhVien item = lists.get(position);
        if(item != null){
            tvMaTv = v.findViewById(R.id.tvMaTVSpn);
            tvTenTV = v.findViewById(R.id.tvTenTVSpn);

            tvMaTv.setText(item.maTV + ". ");
            tvTenTV.setText(item.hoTen);
        }
        return v;
    }
}
