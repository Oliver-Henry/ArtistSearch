package com.example.oliver.artistsearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ArtistInfoFragment extends Fragment {
    private TextView tVname, tVurl, tVbio;
    private ImageView iVimage;


    public ArtistInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tVname = view.findViewById(R.id.tVName);
        tVurl = view.findViewById(R.id.tVURL);
        tVbio = view.findViewById(R.id.tVSummary);
        iVimage = view.findViewById(R.id.iVImage);
        String Name = ArtistInfoFragment.this.getArguments().getString("name");
        String Img = ArtistInfoFragment.this.getArguments().getString("img");
        String URL = ArtistInfoFragment.this.getArguments().getString("url");
        String Bio = ArtistInfoFragment.this.getArguments().getString("bio");
        tVname.setText(Name);
        tVurl.setText(URL);
        tVbio.setText(Bio);
        Picasso.with(getActivity()).load(Img).resize(500, 500).centerCrop().into(iVimage);
    }
}
