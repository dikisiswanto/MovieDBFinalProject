package unhas.informatics.moviemodelfinalproject;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "bb237087";
    private MutableLiveData<ArrayList<MovieItems>> listMovie = new MutableLiveData<>();

    public static String getApiKey() {
        return API_KEY;
    }

    public MutableLiveData<ArrayList<MovieItems>> getListMovie() {
        return listMovie;
    }

    void setMovie(){};

}
