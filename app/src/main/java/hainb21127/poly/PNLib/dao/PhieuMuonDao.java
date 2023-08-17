package hainb21127.poly.PNLib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

import hainb21127.poly.PNLib.database.DbHelper;
import hainb21127.poly.PNLib.obj.PhieuMuon;
import hainb21127.poly.PNLib.obj.Sach;
import hainb21127.poly.PNLib.obj.Top;

public class PhieuMuonDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public PhieuMuonDao(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        //
        values.put("gioMuon", obj.getGio());
        return db.insert("PhieuMuon", null, values);
    }

    public int update (PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        //
        values.put("gioMuon", obj.getGio());
        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.getMaPM())});
    }

    public int delete (String id){
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    //get tất cả data
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            }catch (ParseException e){
                e.printStackTrace();
            }
            obj.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            //
            obj.setGio(c.getString(c.getColumnIndex("gioMuon")));
            list.add(obj);
        }
        return list;
    }

    //thống kê top 10
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sqlTop = "SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<Top>();
        SachDao sachDao = new SachDao(context);
        Cursor c = db.rawQuery(sqlTop, null);
        while (c.moveToNext()){
            Top top = new Top();
            Sach sach = sachDao.getID(c.getString(c.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
}
