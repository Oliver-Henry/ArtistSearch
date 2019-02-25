package com.example.oliver.artistsearch.data.network;

import com.example.oliver.artistsearch.data.network.model.ArtistsModel;

import io.reactivex.Observable;

public interface IApiHelper {
    Observable<ArtistsModel> getArtistsList(String i);
}
