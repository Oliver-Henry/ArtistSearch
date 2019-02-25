package com.example.oliver.artistsearch.search_artist;

import com.example.oliver.artistsearch.data.network.model.ArtistsModel;
import com.example.oliver.artistsearch.ui.base.MvpView;

public interface ISearchArtistMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(ArtistsModel artistsModel);
    void onFetchDataError(String error);
}
