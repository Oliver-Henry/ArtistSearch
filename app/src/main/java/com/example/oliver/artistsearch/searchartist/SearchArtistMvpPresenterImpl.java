package com.example.oliver.artistsearch.searchartist;

import com.example.oliver.artistsearch.data.network.IDataManager;
import com.example.oliver.artistsearch.data.network.model.ArtistsModel;
import com.example.oliver.artistsearch.ui.base.BasePresenter;
import com.example.oliver.artistsearch.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SearchArtistMvpPresenterImpl<V extends ISearchArtistMvpView> extends BasePresenter<V> implements ISearchArtistMvpPresenter<V> {

    public SearchArtistMvpPresenterImpl(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadArtistList(String i) {
        getMvpView().onFetchDataProgress();
        getCompositeDisposable().add(getDataManager().getArtistsList(i)
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<ArtistsModel>() {
                       @Override
                       public void accept(ArtistsModel artistsModel) throws Exception {
                           getMvpView().onFetchDataSuccess(artistsModel);
                       }
                   },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onFetchDataError(throwable.getMessage());
                    }
                }));
    }
}
