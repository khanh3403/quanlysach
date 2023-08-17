package hainb21127.poly.PNLib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hainb21127.poly.PNLib.database.DbHelper;
import hainb21127.poly.PNLib.obj.ThuThu;

public class ThuThuDao {
    private SQLiteDatabase db;

    public ThuThuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("ThuThu", null, values);
    }

    public int updatePass(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("ThuThu", values, "maTT=?", new String[]{obj.getMaTT()});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    //get tất cả data
    public List<ThuThu> getAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String... selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu obj = new ThuThu();
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            Log.i("//=======", obj.toString());
            list.add(obj);
        }
        return list;
    }

    //check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, password);
        if (list.size() == 0)
            return -1;//không tìm thấy
        return 1;//tìm thấy
    }
}
