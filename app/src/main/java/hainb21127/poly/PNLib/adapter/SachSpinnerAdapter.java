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
import hainb21127.poly.PNLib.obj.Sach;
import hainb21127.poly.PNLib.obj.ThanhVien;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    Context context;
    ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> lists) {
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
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final Sach item = lists.get(position);
        if(item != null){
            tvMaSach = v.findViewById(R.id.tvMaSachSpn);
            tvTenSach = v.findViewById(R.id.tvTenSachSpn);

            tvMaSach.setText(item.getMaSach() + ". ");
            tvTenSach.setText(item.getTenSach());
        }
        return v;
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final Sach item = lists.get(position);
        if(item != null){
            tvMaSach = v.findViewById(R.id.tvMaSachSpn);
            tvTenSach = v.findViewById(R.id.tvTenSachSpn);

            tvMaSach.setText(item.getMaSach() + ". ");
            tvTenSach.setText(item.getTenSach());
        }
        return v;
    }
}
