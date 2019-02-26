package com.example.oliver.artistsearch.searchartist;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oliver.artistsearch.ui.MainActivity;
import com.example.oliver.artistsearch.R;
import com.example.oliver.artistsearch.data.network.AppDataManager;
import com.example.oliver.artistsearch.data.network.model.ArtistsModel;
import com.example.oliver.artistsearch.ui.base.BaseFragment;
import com.example.oliver.artistsearch.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class SearchArtistFragment extends BaseFragment implements ISearchArtistMvpView {

    private SearchView searchView;
    private TextView textView;
    private ImageView imageView;
    private Boolean imageClickable = false;
    private SearchArtistMvpPresenterImpl<SearchArtistFragment> searchArtistFragmentSearchArtistMvpPresenter;

    public SearchArtistFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_artist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.tV);
        imageView = view.findViewById(R.id.iV);
        searchView = view.findViewById(R.id.SV);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s != null){searchArtistCallService(s);}
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchArtistFragmentSearchArtistMvpPresenter = new SearchArtistMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        searchArtistFragmentSearchArtistMvpPresenter.onAttach(this);

    }

    public void searchArtistCallService(String i){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean connected) throws Exception {
                        if(connected){searchArtistFragmentSearchArtistMvpPresenter.loadArtistList(i);}
                        else{
                            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override
    public void onFetchDataProgress() {

    }

    @Override
    public void onFetchDataSuccess(ArtistsModel artistsModel) {

        if(artistsModel.getArtist().getImage().size() > 0){
            textView.setText(artistsModel.getArtist().getName());
            String url = artistsModel.getArtist().getImage().get(3).getText();
            Picasso.with(getActivity()).load(url).resize(500, 500).centerCrop().into(imageView);
            imageClickable = true;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.showArtistDetails(
                            artistsModel.getArtist().getName(),
                            artistsModel.getArtist().getImage().get(4).getText(),
                            artistsModel.getArtist().getUrl(),
                            artistsModel.getArtist().getBio().getContent());
                }
            });
        }
        else{
            Toast.makeText(getActivity(), "No Artist Found", Toast.LENGTH_SHORT).show();
            imageClickable = false;
        }
    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        Toast.makeText(getActivity(), "No Artist Found", Toast.LENGTH_SHORT).show();
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
