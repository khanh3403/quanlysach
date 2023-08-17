package hainb21127.poly.PNLib.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.adapter.PhieuMuonAdapter;
import hainb21127.poly.PNLib.adapter.SachSpinnerAdapter;
import hainb21127.poly.PNLib.adapter.ThanhVienSpinnerAdapter;
import hainb21127.poly.PNLib.dao.PhieuMuonDao;
import hainb21127.poly.PNLib.dao.SachDao;
import hainb21127.poly.PNLib.dao.ThanhVienDao;
import hainb21127.poly.PNLib.obj.PhieuMuon;
import hainb21127.poly.PNLib.obj.Sach;
import hainb21127.poly.PNLib.obj.ThanhVien;

public class PhieuMuonFragment extends Fragment {
    ListView listView;
    ArrayList<PhieuMuon> list;
    ArrayList<ThanhVien> listThanhVien;
    ArrayList<Sach> listSach;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spnTV,spnSach;
    TextView tvNgay,tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    static PhieuMuonDao dao;
    ThanhVienDao thanhVienDao;
    SachDao sachDao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVien thanhVien;
    Sach sach;
    SachSpinnerAdapter sachSpinnerAdapter;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    int maThanhVien;
    int maSach, tienThue;
    int positionTV, posittionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    //
    EditText edGio;


    public PhieuMuonFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PhieuMuonFragment newInstance() {
        PhieuMuonFragment fragment = new PhieuMuonFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        listView = v.findViewById(R.id.lv_PhieuMuon);
        fab = v.findViewById(R.id.Pm_FAButton);
        dao = new PhieuMuonDao(getActivity());
        capNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spnTV = dialog.findViewById(R.id.spnMaTV);
        spnSach = dialog.findViewById(R.id.spnMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancel_pm);
        btnSave = dialog.findViewById(R.id.btnSave_pm);
        //
        edGio = dialog.findViewById(R.id.edGio);

        thanhVienDao = new ThanhVienDao(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDao.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spnTV.setAdapter(thanhVienSpinnerAdapter);

        tvNgay.setText("Ngày thuê: "+sdf.format(new Date()));
        //
//        edGio.setText(item.getGio());

        spnTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).maTV;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDao = new SachDao(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDao.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spnSach.setAdapter(sachSpinnerAdapter);

        spnSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).maSach;
                tienThue = listSach.get(position).giaThue;
                tvTienThue.setText("Tiền thuê: " + tienThue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMaPM.setEnabled(false);
        if(type != 0){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listThanhVien.size(); i++){
                if(item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            }
            spnTV.setSelection(positionTV);

            for (int i = 0; i < listSach.size(); i++){
                if(item.getMaSach() == (listSach.get(i).getMaSach())){
                    posittionSach = i;
                }
            }
            spnSach.setSelection(posittionSach);

            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());

            if(item.getTraSach() == 1){
                chkTraSach.setChecked(true);
            }else{
                chkTraSach.setChecked(false);
            }
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                //
                String hour = edGio.getText().toString();
                item.setGio(hour);

                if(tienThue > 50000){
                    item.setTienThue(1);
                }else{
                    item.setTienThue(0);
                }

                if(chkTraSach.isChecked()){
                    item.traSach = 1;
                }else{
                    item.traSach = 0;
                }
                if(validate() > 0){
                    if(type == 0){
                        if(dao.insert(item) > 0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maPM = Integer.parseInt(edMaPM.getText().toString());
                        if(dao.update(item) > 0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Chắc chắc xóa?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        if(edGio.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn chưa nhập giờ",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}