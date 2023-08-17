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
import hainb21127.poly.PNLib.obj.ThanhVien;

public class ThanhVienDao {
    private SQLiteDatabase db;

    public ThanhVienDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        return db.insert("ThanhVien",null,values);
    }

    public int update(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(obj.getNamSinh())});
    }

    public int delete(String id){
        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }

    //Get tất cả data
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        if(list != null && list.size() != 0){
            return list.get(0);
        }
        return null;
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while(c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.namSinh = c.getString(c.getColumnIndex("namSinh"));
//            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
//            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
//            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
//            Log.i("//=======", obj.toString());
            list.add(obj);
        }
        return list;
    }
}
