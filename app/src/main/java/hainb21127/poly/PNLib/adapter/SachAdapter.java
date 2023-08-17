package hainb21127.poly.PNLib.adapter;

import android.content.Context;
import android.util.Log;
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
import hainb21127.poly.PNLib.dao.LoaiSachDao;
import hainb21127.poly.PNLib.fragment.LoaiSachFragment;
import hainb21127.poly.PNLib.fragment.SachFragment;
import hainb21127.poly.PNLib.obj.LoaiSach;
import hainb21127.poly.PNLib.obj.Sach;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    SachFragment fragment;
    ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;
    public SachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> lists) {
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
            v = inflater.inflate(R.layout.sach_item, null);
        }
        final Sach item = lists.get(position);
        if(item != null){
            LoaiSachDao loaiSachDao = new LoaiSachDao(context);
            LoaiSach loaiSach = loaiSachDao.getID(String.valueOf(item.getMaLoai()));

            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvLoai = v.findViewById(R.id.tvLoaiSach);
            imgDel = v.findViewById(R.id.imgDel_s);

            tvMaSach.setText("Mã sách: "+item.getMaSach());
            tvTenSach.setText("Tên sách: "+item.getTenSach());
            tvGiaThue.setText("Giá thuê: "+item.getGiaThue());
            tvLoai.setText("Loại sách: "+loaiSach.getTenLoai());
////            Log.d("zzzzzz", item.toString());
            
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(item.getMaSach()));
                }
            });
        }
        return v;
    }
}
