package unhas.informatics.moviedbfinalproject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

class MovieItems implements Parcelable {
    public static final Parcelable.Creator<MovieItems> CREATOR = new Parcelable.Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel source) {
            return new MovieItems(source);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };
    private int id;
    private String title;
    private double rating;
    private String releaseDate;
    private String description;
    private String poster;
    private String backdrop;

    public MovieItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            double rating = object.getDouble("vote_average");
            String release = object.getString("release_date");
            String description = object.getString("overview");
            String poster = object.getString("poster_path");
            String backdrop_path = object.getString("backdrop_path");
            this.id = id;
            this.title = title;
            this.rating = rating;
            this.releaseDate = release;
            this.description = description;
            this.poster = poster;
            this.backdrop = backdrop_path;

        } catch (JSONException e) {
            Log.d("Exception", e.getMessage());
        }
    }

    protected MovieItems(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.rating = in.readDouble();
        this.releaseDate = in.readString();
        this.description = in.readString();
        this.poster = in.readString();
        this.backdrop = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeDouble(this.rating);
        dest.writeString(this.releaseDate);
        dest.writeString(this.description);
        dest.writeString(this.poster);
        dest.writeString(this.backdrop);
    }
}
