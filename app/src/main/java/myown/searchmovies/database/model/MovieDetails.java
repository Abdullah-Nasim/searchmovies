package myown.searchmovies.database.model;

import java.util.List;

/**
 * Created by Netaq on 9/14/2017.
 */


public class MovieDetails
{
    public Integer id;
    public String title;
    public String tagline;
    public String status;
    public Integer budget;
    public String genres;
    public String homepage;
    public String overview;
    public String popularity;
    public String poster_path;


    public MovieDetails(Integer id, String title, String tagline, String status, Integer budget, String genres, String homepage, String overview, String popularity, String poster_path) {
        this.id = id;
        this.title = title;
        this.tagline = tagline;
        this.status = status;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

