package hainb21127.poly.PNLib.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hainb21127.poly.PNLib.R;
import hainb21127.poly.PNLib.adapter.TopAdapter;
import hainb21127.poly.PNLib.dao.PhieuMuonDao;
import hainb21127.poly.PNLib.obj.Top;


public class TopFragment extends Fragment {
    ListView listView;
    ArrayList<Top> list;
    TopAdapter adapter;

    public TopFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TopFragment newInstance() {
        TopFragment fragment = new TopFragment();
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
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        listView = v.findViewById(R.id.Top_ListView);
        PhieuMuonDao phieuMuonDao = new PhieuMuonDao(getActivity());
        list = (ArrayList<Top>) phieuMuonDao.getTop();
        adapter = new TopAdapter(getActivity(), this, list);
        listView.setAdapter(adapter);
        return v;
    }


}