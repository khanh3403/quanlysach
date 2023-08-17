package hainb21127.poly.PNLib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "PNLIB";
    static final int dbVersion = 1;

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng Thủ Thư
        String createTableThuThu=
                "create table ThuThu(" +
                        "maTT TEXT PRIMARY KEY," +
                        "hoTen TEXT NOT NULL," +
                        "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

        //Tạo bảng Thành Viên
        String createTableThanhVien=
                "create table ThanhVien(" +
                        "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "hoTen TEXT NOT NULL," +
                        "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        //Tạo bảng Loại Sách
        String createTableLoaiSach=
                "create table LoaiSach(" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        //Tạo bảng Sách
        String createTableSach=
                "create table Sach(" +
                        "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tenSach TEXT NOT NULL," +
                        "giaThue INTEGER NOT NULL," +
                        "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        //Tạo bảng Phiếu Mượn
        String createTablePhieuMuon=
                "create table PhieuMuon(" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "maTT TEXT REFERENCES ThuThu(maTT)," +
                        "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                        "maSach INTEGER REFERENCES Sach(maSach)," +
                        "tienThue INTEGER NOT NULL," +
                        "ngay DATE NOT NULL," +
                        "traSach INTEGER NOT NULL)";
        db.execSQL(createTablePhieuMuon);


        //Thêm cột giờ vào bảng PhieuMuon
        String alterTablePhieuMuon=
                "alter table PhieuMuon add gioMuon TIME";
        db.execSQL(alterTablePhieuMuon);





        //insert dữ liệu
        db.execSQL(Data_SQL.INSERT_THU_THU);
        db.execSQL(Data_SQL.INSERT_THANH_VIEN);
//        db.execSQL(Data_SQL.INSERT_LOAI_SACH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableLoaiThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
