package myown.searchmovies.network.models;

import java.util.List;

/**
 * Created by Netaq on 9/14/2017.
 */

public class MoviesResponse {

    public int page;
    public int total_results;
    public int total_pages;
    public List<Result> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result
    {
        public int vote_count;
        public int id;
        public boolean video;
        public double vote_average;
        public String title;
        public double popularity;
        public String poster_path;
        public String original_language;
        public String original_title;
        public List<Integer> genre_ids;
        public String backdrop_path;
        public boolean adult;
        public String overview;
        public String release_date;
    }

}
