package ru.anstag.app.example3;

import java.util.ArrayList;
import java.util.List;

public class SeriesList {

    List<String> getGenre(String spinner){

        List<String> genre = new ArrayList<>();

        if (spinner.equals("Comedy")){
            genre.add("Friends");
            genre.add("Big Bang");
        } if (spinner.equals("Fantasy")){
            genre.add("Geme of Thrones");
        } if (spinner.equals("Horror")){
            genre.add("The Walking Dead");
        } if (spinner.equals("Action")){
            genre.add("Futurama");
            genre.add("Mentalist");
        } if (spinner.equals("Crime")){
            genre.add("Breaking Bad");
        }

        return genre;

    }

}
