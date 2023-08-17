package hainb21127.poly.PNLib.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.fragment.LoaiSachFragment;
import hainb21127.poly.PNLib.obj.LoaiSach;
import hainb21127.poly.PNLib.obj.ThanhVien;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tvMaloai, tvTenloai;
    ImageView imgDel;
    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);
        }
        final LoaiSach item = lists.get(position);
        if(item != null){
            tvMaloai = v.findViewById(R.id.tvMaLoai_ls);
            tvTenloai = v.findViewById(R.id.tvTenLoai_ls);
            imgDel = v.findViewById(R.id.imgDel_ls);

            tvMaloai.setText("Mã loại: "+item.getMaLoai());
            tvTenloai.setText("Tên loại: "+item.getTenLoai());
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }
}
