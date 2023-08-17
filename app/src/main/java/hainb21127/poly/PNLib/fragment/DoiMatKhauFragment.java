package hainb21127.poly.PNLib.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
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


public class DoiMatKhauFragment extends Fragment {


    public DoiMatKhauFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DoiMatKhauFragment newInstance() {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();
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
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
    }
    EditText edPassOld,edPass,edRePass;
    Button btnSave, btnCancel;
    ThuThuDao dao;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edPassOld = view.findViewById(R.id.edPassOld);
        edPass = view.findViewById(R.id.edPassNew);
        edRePass = view.findViewById(R.id.edPassXn);
        btnCancel = view.findViewById(R.id.btnHuy_dmk);
        btnSave = view.findViewById(R.id.btnLuu_dmk);

        dao = new ThuThuDao(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Đọc user, pass trong
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if(validate() > 0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                    dao.updatePass(thuThu);
                    if(dao.updatePass(thuThu) > 0){
                        Toast.makeText(getActivity(),"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else{
                        Toast.makeText(getActivity(),"Đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public int validate(){
        int check = 1;
        if(edPassOld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0){
            Toast.makeText(getContext(),"Phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(),"Không trùng mật khẩu cũ",Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(),"Không trùng mật khẩu mới",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}