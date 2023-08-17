package hainb21127.poly.PNLib.fragment;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.dao.ThongKeDao;


public class DoanhThuFragment extends Fragment {
    Button btnTuNgay, btnDenNgay, btnDoanhThu;
    EditText edTuNgay, edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    int mYear, mMonth, mDay;

    public DoanhThuFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DoanhThuFragment newInstance() {
        DoanhThuFragment fragment = new DoanhThuFragment();
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
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edTuNgay = v.findViewById(R.id.edTuNgay);
        edDenNgay = v.findViewById(R.id.edDenNgay);
        tvDoanhThu = v.findViewById(R.id.tvDoanhThu);
        btnTuNgay = v.findViewById(R.id.btnTuNgay);
        btnDenNgay = v.findViewById(R.id.btnDenNgay);
        btnDoanhThu = v.findViewById(R.id.btnDoanhThu);

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mMonth = c.get(Calendar.MONTH);
                mYear = c.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateTuNgay, mDay, mMonth, mYear);
                d.show();
            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mMonth = c.get(Calendar.MONTH);
                mYear = c.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateDenNgay, mDay, mMonth, mYear);
                d.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                ThongKeDao thongKeDao = new ThongKeDao(getActivity());
                tvDoanhThu.setText("Doanh thu: " +thongKeDao.getDoanhThu(tuNgay, denNgay)+ " VNĐ");
            }
        });
        return v;
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mDay = dayOfMonth;
            mMonth = month;
            mYear = year;
            GregorianCalendar c = new GregorianCalendar(mDay, mMonth, mYear);
            edTuNgay.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mDay = dayOfMonth;
            mMonth = month;
            mYear = year;
            GregorianCalendar c = new GregorianCalendar(mDay, mMonth, mYear);
            edDenNgay.setText(sdf.format(c.getTime()));
        }
    };

}
