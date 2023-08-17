package hainb21127.poly.PNLib.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.dao.SachDao;
import hainb21127.poly.PNLib.dao.ThanhVienDao;
import hainb21127.poly.PNLib.fragment.PhieuMuonFragment;
import hainb21127.poly.PNLib.obj.LoaiSach;
import hainb21127.poly.PNLib.obj.PhieuMuon;
import hainb21127.poly.PNLib.obj.Sach;
import hainb21127.poly.PNLib.obj.ThanhVien;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    Context context;
    PhieuMuonFragment fragment;
    ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach, tvGio;
    ImageView imgDel;
    SachDao sachDao;
    ThanhVienDao thanhVienDao;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
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
            v = inflater.inflate(R.layout.phieu_muon_item, null);
        }
        final PhieuMuon item = lists.get(position);
        if(item != null){
            sachDao = new SachDao(context);
            thanhVienDao = new ThanhVienDao(context);
            Sach sach = sachDao.getID(String.valueOf(item.getMaSach()));
            ThanhVien thanhVien = thanhVienDao.getID(String.valueOf(item.getMaTV()));

            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvTenTV = v.findViewById(R.id.tvTenTV_pm);
            tvTenSach = v.findViewById(R.id.tvTenSach_pm);
            tvTienThue = v.findViewById(R.id.tvTienThue_pm);
            tvTraSach = v.findViewById(R.id.tvTraSach);
            tvNgay = v.findViewById(R.id.tvNgay_pm);
            imgDel = v.findViewById(R.id.imgDel_pm);

            //
            tvGio = v.findViewById(R.id.tvGio_pm);
            tvGio.setText("Giờ: "+item.getGio());

            tvMaPM.setText("Mã phiếu: "+item.getMaPM());
            tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());
            tvTenSach.setText("Tên sách: "+sach.getTenSach());
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));

            //tích vào checkbox
            if(item.traSach == 1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else{
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }


        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maPM));
            }
        });
        return v;
    }
}
