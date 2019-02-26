package com.example.oliver.artistsearch.ui;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oliver.artistsearch.R;
import com.example.oliver.artistsearch.searchartist.SearchArtistFragment;

public class MainActivity extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new SearchArtistFragment())
                    .commit();
        }
    }

    public static void showArtistDetails(String name, String img, String url, String bio){
        ArtistInfoFragment artistInfoFragment = new ArtistInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("img", img);
        bundle.putString("url", url);
        bundle.putString("bio", bio);
        artistInfoFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, artistInfoFragment)
                .addToBackStack(null)
                .commit();
    }
}
