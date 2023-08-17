package hainb21127.poly.PNLib.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.adapter.LoaiSachSpinnerAdapter;
import hainb21127.poly.PNLib.adapter.SachAdapter;
import hainb21127.poly.PNLib.dao.LoaiSachDao;
import hainb21127.poly.PNLib.dao.SachDao;
import hainb21127.poly.PNLib.obj.LoaiSach;
import hainb21127.poly.PNLib.obj.Sach;


public class SachFragment extends Fragment {
    ListView listView;
    ArrayList<Sach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;
    static SachDao dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDao loaiSachDao;
    LoaiSach loaiSach;
    int maLoaiSach, position;

    public SachFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SachFragment newInstance() {
        SachFragment fragment = new SachFragment();
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
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        listView = v.findViewById(R.id.lv_Sach);
        fab = v.findViewById(R.id.S_FAButton);
        dao = new SachDao(getActivity());
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
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spnLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancel_s);
        btnSave = dialog.findViewById(R.id.btnSave_s);

        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDao = new LoaiSachDao(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDao.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).maLoai;
//                Toast.makeText(context,"Chọn "+listLoaiSach.get(position).tenLoai,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMaSach.setEnabled(false);
        if(type != 0){
            edMaSach.setText(String.valueOf(item.maSach));
            edTenSach.setText(item.tenSach);
            edGiaThue.setText(String.valueOf(item.giaThue));
            for(int i = 0; i < listLoaiSach.size(); i++){
                if(item.maLoai == (listLoaiSach.get(i).maLoai)){
                    position = i;
                }
            }
            Log.i("demo","posSach"+position);
            spinner.setSelection(position);
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
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setMaLoai(maLoaiSach);
                try{
                    item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                }catch (Exception e){
                }

                if(validate() > 0){
                    if(type == 0){
                        if(dao.insert(item) > 0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maSach = Integer.parseInt(edMaSach.getText().toString());
                        if(dao.update(item) > 0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Chắc chắn xóa?");
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
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        String ten = edTenSach.getText().toString();
        String tt = edGiaThue.getText().toString();
        if(ten.length() == 0 || tt.length() == 0){
            Toast.makeText(getContext(), "Phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
//        else if(!tt.matches("^\\\\d+$")){
//            Toast.makeText(getContext(), "Phải nhập số", Toast.LENGTH_SHORT).show();
//            check = -1;
//        }
        return check;
    }
}