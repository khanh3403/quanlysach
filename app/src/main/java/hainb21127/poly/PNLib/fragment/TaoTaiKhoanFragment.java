package hainb21127.poly.PNLib.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.dao.ThuThuDao;
import hainb21127.poly.PNLib.obj.ThuThu;


public class TaoTaiKhoanFragment extends Fragment {


    public TaoTaiKhoanFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TaoTaiKhoanFragment newInstance() {
        TaoTaiKhoanFragment fragment = new TaoTaiKhoanFragment();
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
        return inflater.inflate(R.layout.fragment_tao_tai_khoan, container, false);
    }
    private EditText edUser, edHoTen, edPass, edRePass;
    private Button btnAdd, btnCancel;
    ThuThuDao dao;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edUser = view.findViewById(R.id.edUser);
        edHoTen = view.findViewById(R.id.edHoTen);
        edPass = view.findViewById(R.id.edPass);
        edRePass = view.findViewById(R.id.edRePass);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnCancel = view.findViewById(R.id.btnCancel_dk);

        dao = new ThuThuDao(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUser.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.maTT = edUser.getText().toString();
                thuThu.hoTen = edHoTen.getText().toString();
                thuThu.matKhau = edPass.getText().toString();
                if(validate() > 0){
                    if(dao.insert(thuThu) > 0){
                        Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edHoTen.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else{
                        Toast.makeText(getActivity(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int validate(){
        int check = 1;
        if(edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}