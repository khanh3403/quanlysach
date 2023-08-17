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
import hainb21127.poly.PNLib.adapter.ThanhVienAdapter;
import hainb21127.poly.PNLib.dao.ThanhVienDao;
import hainb21127.poly.PNLib.obj.ThanhVien;


public class ThanhVienFragment extends Fragment {
    ListView listView;
    ArrayList<ThanhVien> arrayList;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    static ThanhVienDao dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    public ThanhVienFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ThanhVienFragment newInstance() {
        ThanhVienFragment fragment = new ThanhVienFragment();
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
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        listView = v.findViewById(R.id.lv_ThanhVien);
        fab = v.findViewById(R.id.Tv_FAButton);
        dao = new ThanhVienDao(getActivity());
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
                item = arrayList.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnSave = dialog.findViewById(R.id.btnSave_tv);
        btnCancel = dialog.findViewById(R.id.btnCancel_tv);

        //kiểm tra type insert 0 hay update 1
        edMaTV.setEnabled(false);
        if(type != 0){
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
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
                item = new ThanhVien();
                item.hoTen = edTenTV.getText().toString();
                item.namSinh = edNamSinh.getText().toString();
                if(validate() > 0){
                    if(type == 0){
                        //type = 0 (insert)
                        if(dao.insert(item) > 0){
                            Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        //type = 1 (update)
                        item.maTV = Integer.parseInt(edMaTV.getText().toString());
                        if(dao.update(item) > 0){
                            Toast.makeText(getContext(),"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"Sửa thất bại",Toast.LENGTH_SHORT).show();
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
        //Sử dụng Alert
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
        arrayList = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, arrayList);
        listView.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if(edTenTV.getText().length() == 0 || edNamSinh.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}