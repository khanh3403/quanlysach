package hainb21127.poly.PNLib.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import hainb21127.poly.PNLib.database.DbHelper;
import hainb21127.poly.PNLib.obj.Top;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;

    public ThongKeDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay1, String denNgay1){
        String tuNgay = fmDate(tuNgay1);
        String denNgay = fmDate(denNgay1);
        String sqlDoanhThu = "SELECT SUM(tienThue) as danhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(0)));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

    private String fmDate(String date){
        String kqua = "";
        String[] tach = date.split("/");
        for(int i = tach.length - 1; i >= 0; i--){
            kqua += tach[i];
            if(i != 0){
                kqua += "-";
            }
        }
        return kqua;
    }
}
