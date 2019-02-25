package com.example.oliver.artistsearch.data.network;

import com.example.oliver.artistsearch.data.network.model.ArtistsModel;
import com.example.oliver.artistsearch.data.network.service.IRequestInterface;
import com.example.oliver.artistsearch.data.network.service.ServiceConnection;

import io.reactivex.Observable;

public class AppApiHelper implements IApiHelper {

    private IRequestInterface iRequestInterface;

    @Override
    public Observable<ArtistsModel> getArtistsList(String i) {
        return iRequestInterface.getArtists(i);
    }

    public AppApiHelper() {iRequestInterface = ServiceConnection.getConnection();}
}
