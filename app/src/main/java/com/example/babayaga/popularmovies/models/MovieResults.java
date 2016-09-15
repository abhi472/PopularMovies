package com.example.babayaga.popularmovies.models;

/**
 * Created by mits on 7/9/16.
 */

public class MovieResults {
        private String id;
        private String title;
        private String overview;
        private String vote_average;
        private String release_date;
        private String poster_path;
        private String back_path;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getOverview() {
                return overview;
        }

        public void setOverview(String overview) {
                this.overview = overview;
        }

        public String getVote_average() {
                return vote_average;
        }

        public void setVote_average(String vote_average) {
                this.vote_average = vote_average;
        }

        public String getRelease_date() {
                return release_date;
        }

        public void setRelease_date(String release_date) {
                this.release_date = release_date;
        }

        public String getPoster_path() {
                return poster_path;
        }

        public void setPoster_path(String poster_path) {
                this.poster_path = poster_path;
        }

        public String getBack_path() {
                return back_path;
        }

        public void setBack_path(String back_path) {
                this.back_path = back_path;
        }
}
