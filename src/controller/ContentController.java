package controller;
import javafx.scene.image.Image;
import model.Content;
import model.Movie;
import model.Show;
import model.Show;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class ContentController {
    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;
    private ArrayList<Content> content;
    private ArrayList<Content> contentSort;

    public ContentController() {
        content = new ArrayList<>();
        contentSort = new ArrayList<>();
    }

    public void initializeContent() throws IOException {

        //movie scanner
        Scanner mReader = new Scanner(new File("out/movies/#movies.txt"));
        mReader.useDelimiter(";");

        while (mReader.hasNext()) {
            String        title  = mReader.next().trim();
            int           year   = Integer.parseInt(mReader.next().trim());
            String        genre  = mReader.next().trim();
            String[] arrOfGenres = genre.split(Pattern.quote(",").trim());
            double        rating = Double.parseDouble(mReader.next().trim().replaceAll(",", "."));
            Image cover   = new Image(new FileInputStream("out/movies/" + title + ".jpg"));



            content.add(new Movie(title, arrOfGenres, rating, cover, year));
            mReader.nextLine();
        }
        mReader.close();

        //shows scanner
        Scanner sReader = new Scanner(new File("out/shows/#shows.txt"));
        sReader.useDelimiter(";");

        while (sReader.hasNext()) {
            String        title   = sReader.next().trim();
            String        runtime = sReader.next().trim();
            String        genre  = sReader.next().trim();
            String[] arrOfGenres = genre.split(Pattern.quote(",").trim());
            double        rating  = Double.parseDouble(sReader.next().trim().replaceAll(",", "."));
            String        seasons = sReader.next().trim();
            Image          cover   = new Image(new FileInputStream("out/shows/" + title + ".jpg"));


            content.add(new Show(title, arrOfGenres, rating, cover, runtime, seasons));
            sReader.nextLine();
        }
        sReader.close();
        //shuffles the arraylist so that it doesnt display movies and then series.
        Collections.shuffle(content);
    }

    public ArrayList<Content> getContent() {
        
    }

    public ArrayList<Content> getCustomContent(){

    }

    public void display() {
        int i = 0;
        for (Content c : content) {
            if (c instanceof Movie) {
                System.out.println("film " + i + " " + c.display());
            } else {
                System.out.println("Serie " + i + " " + c.display());
            }
            i++;
        }
    }

    public ArrayList searchByRating(double sort) {
        ArrayList<Content> sortArray = new ArrayList<>();
        for (Content c : content) {

            if (c.getRating() >= sort) {
                sortArray.add(c);
                if (c instanceof Movie) {
                    System.out.println("film " + c.display());
                } else {
                    System.out.println("Serie " + c.display());
                }
            }
        }
        return sortArray;
    }

    public ArrayList searchByTitle(String sTerm) {
        ArrayList<Content> sortArray = new ArrayList<>();
        for (Content c : content) {
            if (c.getTitle().toLowerCase().contains(sTerm.toLowerCase())) {
                sortArray.add(c);
                if (c instanceof Movie) {
                    System.out.println("film " + c.display());
                } else {
                    System.out.println("Serie " + c.display());
                }
            }

        }
        return sortArray;
    }

    public void searchByGenre(String sTerm){
        //capitalize string
        sTerm = sTerm.substring(0, 1).toUpperCase() + sTerm.substring(1);

        //searchAlgoritme
        for(Content c: content) {
            for(int i = 0; i <c.getGenre().length; i++){
                if(c.getGenre()[i].contains(sTerm)){
                    System.out.println(c.display());
                }
            }
        }
    }

    public ArrayList searchForMovies(){
        ArrayList<Movie> movieArray = new ArrayList<>();
        for(Content m: content){
            if(m instanceof Movie){
                movieArray.add((Movie) m);
            }
        }
        return movieArray;
    }

    public ArrayList searchForShows() {
        ArrayList<Show> ShowArray = new ArrayList<>();
        for (Content s : content) {
            if (s instanceof Show) {
                ShowArray.add((Show) s);
            }
        }
        return ShowArray;
    }


    public HashMap getSeasonAndEpisodesMap(Show show) {
        //creates hashmap to store key: season, value: episodes
        HashMap<Integer, Integer> hash_map = new HashMap<>();

        //creates an array of strings that contains "season-episodes"
        String[] tempString = show.getSeasons().replaceAll(" ", "").split(",");

        for (String s : tempString) {
            //first defines lastindex of the split charecter '-'
            int lastIndexOf = s.lastIndexOf( '-' );

            //parses the substring before and after the '-' which correspond to season and episodes
            int season = Integer.parseInt(s.substring(0, lastIndexOf));
            int episodes = Integer.parseInt(s.substring(lastIndexOf + 1));

            //adds the season and episodes to the hashmap
            hash_map.put(season, episodes);
        }
        return hash_map;
    }

    public void displaySeasonAndEpisodes(Show show){
        HashMap hash_map = getSeasonAndEpisodesMap(show);
        for(Object k: hash_map.keySet()){
            String key = k.toString();
            String value = hash_map.get(k).toString();
            System.out.println(key + " episodes " + value);
        }
    }



}
