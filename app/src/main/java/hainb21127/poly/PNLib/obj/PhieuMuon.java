package hainb21127.poly.PNLib.obj;

import java.sql.Time;
import java.util.Date;

public class PhieuMuon {
    public int maPM;
    public String maTT;
    public int maTV;
    public int maSach;
    public Date ngay;
    public int tienThue;
    public int traSach;
    public String gio;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, Date ngay, int tienThue, int traSach, String gio) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.gio = gio;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }
}
