package com.twe.android.master_detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailFragment extends Fragment {
    private TextView txtCode;
    private TextView txtLevel;
    private ImageView imgIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        txtCode = (TextView) view.findViewById(R.id.txtCode);
        txtLevel = (TextView) view.findViewById(R.id.txtLevel);
        imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
        return view;
    }


    public void updateView(Version version) {
        txtCode.setText(version.getCode());
        txtLevel.setText(version.getLevel());
        imgIcon.setImageBitmap(version.getIcon());
    }
}
