package com.example.oliver.artistsearch.data.network.service;

import com.example.oliver.artistsearch.data.network.model.ArtistsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRequestInterface {

    @GET(ApiList.ARTIST_SEARCH)
    Observable<ArtistsModel> getArtists(@Query("artist") String i);
}
