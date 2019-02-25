package com.example.oliver.artistsearch.search_artist;

import com.example.oliver.artistsearch.ui.base.MvpPresenter;

public interface ISearchArtistMvpPresenter<V extends ISearchArtistMvpView> extends MvpPresenter<V> {
    void loadArtistList(String i);
}
