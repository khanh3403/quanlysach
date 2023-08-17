package hainb21127.poly.PNLib.adapter;

import android.content.Context;
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
import hainb21127.poly.PNLib.fragment.ThanhVienFragment;
import hainb21127.poly.PNLib.obj.ThanhVien;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView imgDel;
    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
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
            v = inflater.inflate(R.layout.thanh_vien_item, null);
        }
        final ThanhVien item = lists.get(position);
        if(item != null){
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            imgDel = v.findViewById(R.id.imgDel_tv);

            tvMaTV.setText("Mã thành viên: "+item.getMaTV());
            tvTenTV.setText("Tên thành viên: "+item.getHoTen());
            tvNamSinh.setText("Năm sinh: "+item.getNamSinh());
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maTV));
            }
        });
        return v;
    }
}
