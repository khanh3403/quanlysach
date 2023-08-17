package hainb21127.poly.PNLib.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.adapter.LoaiSachAdapter;
import hainb21127.poly.PNLib.dao.LoaiSachDao;
import hainb21127.poly.PNLib.obj.LoaiSach;


public class LoaiSachFragment extends Fragment {
    ListView listView;
    ArrayList<LoaiSach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaloai, edTenloai;
    Button btnSave, btnCancel;
    static LoaiSachDao dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    public LoaiSachFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoaiSachFragment newInstance() {
        LoaiSachFragment fragment = new LoaiSachFragment();
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
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        listView = v.findViewById(R.id.lv_LoaiSach);
        fab = v.findViewById(R.id.Ls_FAButton);

        dao = new LoaiSachDao(getActivity());
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
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaloai = dialog.findViewById(R.id.edMaLoai_ls);
        edTenloai = dialog.findViewById(R.id.edTenLoai_ls);
        btnCancel = dialog.findViewById(R.id.btnCancel_ls);
        btnSave = dialog.findViewById(R.id.btnSave_ls);

        edMaloai.setEnabled(false);
        if(type != 0){
            edMaloai.setText(String.valueOf(item.maLoai));
            edTenloai.setText(item.tenLoai);
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
                item = new LoaiSach();
                item.tenLoai = edTenloai.getText().toString();
                if(validate() > 0){
                    if(type == 0){
                        //type = 0 (insert)
                        if(dao.insert(item) > 0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        //type = 1 (update)
                        item.maLoai = Integer.parseInt(edMaloai.getText().toString());
                        if(dao.update(item) > 0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
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
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        if(edTenloai.getText().length() == 0){
            Toast.makeText(getContext(),"Phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}