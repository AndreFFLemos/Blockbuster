package Blockbuster.Model;

import java.util.List;

public class Movie {

    private int id;
    private String title;
    private int nMovieCopies; // the number of copies in stock that each title has
    private double moviePrice;
    private boolean isItInStock=true;

    public Movie(int id,String title, int nMovieCopies, double moviePrice) {
        this.id=id;
        this.title = title;
        this.nMovieCopies = nMovieCopies;
        this.moviePrice = moviePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie (String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getnMovieCopies() {
        return nMovieCopies;
    }

    public void setnMovieCopies(int nMovieCopies) {
        this.nMovieCopies = nMovieCopies;
    }

    public double getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(double moviePrice) {
        this.moviePrice = moviePrice;
    }

    public boolean isItInStock() {
        return isItInStock;
    }

    public void setItInStock(boolean itInStock) {
        isItInStock = itInStock;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", nMovieCopies=" + nMovieCopies +
                ", moviePrice=" + moviePrice +
                ", isItInStock=" + isItInStock +
                '}';
    }
}