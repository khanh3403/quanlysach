package hainb21127.poly.PNLib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import hainb21127.poly.PNLib.dao.ThuThuDao;
import hainb21127.poly.PNLib.fragment.DoanhThuFragment;
import hainb21127.poly.PNLib.fragment.DoiMatKhauFragment;
import hainb21127.poly.PNLib.fragment.LoaiSachFragment;
import hainb21127.poly.PNLib.fragment.PhieuMuonFragment;
import hainb21127.poly.PNLib.fragment.SachFragment;
import hainb21127.poly.PNLib.fragment.TaoTaiKhoanFragment;
import hainb21127.poly.PNLib.fragment.ThanhVienFragment;
import hainb21127.poly.PNLib.fragment.TopFragment;
import hainb21127.poly.PNLib.obj.ThuThu;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    View mHeaderView;
    TextView edUser;
    ThuThuDao thuThuDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.id_Drawer);
        navigationView = findViewById(R.id.id_NavView);
        toolbar = findViewById(R.id.toolbar1);
        frameLayout = findViewById(R.id.Fl_Content);

        //set toolbar thay thế cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //Dùng PhieuMuonFragment làm màn hình home
        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction().replace(R.id.Fl_Content,phieuMuonFragment).commit();

        //Hiển thị username lên header
        mHeaderView = navigationView.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDao = new ThuThuDao(this);
        ThuThu thuThu = thuThuDao.getID(user);
        String username = thuThu.hoTen;
        edUser.setText("Welcome "+username+"!");

        //admin có quyền tạo tài khoản
        if(!user.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.nav_AddUser).setVisible(false);
        }
        
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,phieuMuonFragment).commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,loaiSachFragment).commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,sachFragment).commit();
                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,thanhVienFragment).commit();
                        break;
                    case R.id.nav_Top10:
                        setTitle("Top 10 sách mượn nhiều nhất");
                        TopFragment top10Fragment = new TopFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,top10Fragment).commit();
                        break;
                    case R.id.nav_DoanhThu:
                        setTitle("Quản lý Doanh thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,doanhThuFragment).commit();
                        break;
                    case R.id.nav_AddUser:
                        setTitle("Tạo tài khoản");
                        TaoTaiKhoanFragment taoTaiKhoanFragment = new TaoTaiKhoanFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,taoTaiKhoanFragment).commit();
                        break;
                    case R.id.nav_DoiMk:
                        setTitle("Đổi mật khẩu");
                        DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                        manager.beginTransaction().replace(R.id.Fl_Content,doiMatKhauFragment).commit();
                        break;
                    case R.id.nav_Logout:
                        setTitle("Đăng xuất");
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}