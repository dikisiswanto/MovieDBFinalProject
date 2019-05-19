package unhas.informatics.moviemodelfinalproject;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "722af6d6186fe5b159504e8123359a35";
    private static final String API_ENDPOINT = "https://api.themoviedb.org/3/";

    private MutableLiveData<ArrayList<MovieItems>> listMovie = new MutableLiveData<>();

    public static String getApiKey() {
        return API_KEY;
    }

    public MutableLiveData<ArrayList<MovieItems>> getListMovie() {
        return listMovie;
    }

    void setMovies(String movie){
        //Request via Web API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieItems> listItems = new ArrayList<>();
        String url = API_ENDPOINT +
                "discover/movie?sort_by=popularity.desc&page=1&api_key=" + API_KEY;
        if (movie != null){
            url = API_ENDPOINT + "search/movie?query=" + movie + "&api_key=" + API_KEY;
        }


        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        listItems.add(movieItems);
                    }
                    listMovie.postValue(listItems);

                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
}
