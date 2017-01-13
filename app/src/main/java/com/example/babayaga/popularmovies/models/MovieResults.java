package com.example.babayaga.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by mits on 7/9/16.
 */

public class MovieResults implements Parcelable {
        @JsonProperty("poster_path")
        private String posterPath;
        private Boolean adult;
        private String overview;
        @JsonProperty("release_date")
        private String releaseDate;
        @JsonIgnoreProperties
        @JsonProperty("genre_ids")
        private ArrayList<Integer> genreIds = null;
        private Integer id;
        @JsonProperty("original_title")
        private String originalTitle;
        @JsonProperty("original_language")
        private String originalLanguage;
        private String title;
        @JsonProperty("backdrop_path")
        private String backdropPath;
        private Double popularity;
        @JsonProperty("vote_count")
        private Integer voteCount;
        private Boolean video;
        @JsonProperty("vote_average")
        private Double voteAverage;

        public MovieResults()
        {

        }
        protected MovieResults(Parcel in) {
                posterPath = in.readString();
                overview = in.readString();
                releaseDate = in.readString();
                originalTitle = in.readString();
                originalLanguage = in.readString();
                title = in.readString();
                backdropPath = in.readString();
                voteAverage = in.readDouble();
                id = in.readInt();
        }

        public static final Creator<MovieResults> CREATOR = new Creator<MovieResults>() {
                @Override
                public MovieResults createFromParcel(Parcel in) {
                        return new MovieResults(in);
                }

                @Override
                public MovieResults[] newArray(int size) {
                        return new MovieResults[size];
                }
        };

        public String getPosterPath() {
                return posterPath;
        }

        public void setPosterPath(String posterPath) {
                this.posterPath = posterPath;
        }

        public Boolean getAdult() {
                return adult;
        }

        public void setAdult(Boolean adult) {
                this.adult = adult;
        }

        public String getOverview() {
                return overview;
        }

        public void setOverview(String overview) {
                this.overview = overview;
        }

        public String getReleaseDate() {
                return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
        }

        public ArrayList<Integer> getGenreIds() {
                return genreIds;
        }

        public void setGenreIds(ArrayList<Integer> genreIds) {
                this.genreIds = genreIds;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getOriginalTitle() {
                return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
                this.originalTitle = originalTitle;
        }

        public String getOriginalLanguage() {
                return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
                this.originalLanguage = originalLanguage;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getBackdropPath() {
                return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
                this.backdropPath = backdropPath;
        }

        public Double getPopularity() {
                return popularity;
        }

        public void setPopularity(Double popularity) {
                this.popularity = popularity;
        }

        public Integer getVoteCount() {
                return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
                this.voteCount = voteCount;
        }

        public Boolean getVideo() {
                return video;
        }

        public void setVideo(Boolean video) {
                this.video = video;
        }

        public Double getVoteAverage() {
                return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
                this.voteAverage = voteAverage;
        }


        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(posterPath);
                parcel.writeString(overview);
                parcel.writeString(releaseDate);
                parcel.writeString(originalTitle);
                parcel.writeString(originalLanguage);
                parcel.writeString(title);
                parcel.writeString(backdropPath);
                parcel.writeDouble(voteAverage);
                parcel.writeInt(id);

        }
}