package hainb21127.poly.PNLib;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import hainb21127.poly.PNLib.dao.ThuThuDao;

public class LoginActivity extends AppCompatActivity {
    private EditText edUserName,edPassword;
    private CheckBox chkRememberPass;
    private Button btnLogin,btnCancel;
    ThuThuDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPass_lg);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        dao = new ThuThuDao(this);

        //Đọc user, pass trong Share
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }
    private void checkLogin(){
        String strUser,strPass;
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if(strPass.isEmpty() || strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Không được bỏ trống thông tin",Toast.LENGTH_SHORT).show();
        }else{
            if(dao.checkLogin(strUser,strPass) > 0){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            //Xóa tình trạng lưu trữ trước đó
            edit.clear();
        }else{
            //Lưu dữ liệu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //Lưu lại toàn bộ
        edit.commit();
    }
}