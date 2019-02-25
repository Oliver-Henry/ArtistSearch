package com.example.oliver.artistsearch.data.network;

import com.example.oliver.artistsearch.data.network.model.ArtistsModel;

import io.reactivex.Observable;

public class AppDataManager implements IDataManager {
    private IApiHelper iApiHelper;

    public AppDataManager(){iApiHelper = new AppApiHelper();}


    @Override
    public Observable<ArtistsModel> getArtistsList(String i) {
        return iApiHelper.getArtistsList(i);
    }
}
