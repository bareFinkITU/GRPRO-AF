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
    private static ContentController instance;

    private ContentController() throws IOException {
        content = new ArrayList<Content>();
        contentSort = new ArrayList<>();
        initializeContent();
    }

    public static ContentController getInstanceOf() throws IOException {
        if(instance == null) {
            instance = new ContentController();
        }
        return instance;
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
        //adds all movies and shows to the searchable array
        contentSort = content;
    }

    public ArrayList<Content> getContent() {
        return content;
    }

    public ArrayList<Content> customContent(){
        return contentSort;
    }

    public void display() {
        int i = 1;
        for (Content c : contentSort) {
            if (c instanceof Movie) {
                System.out.println(i + " film "+ c.display());
            } else {
                System.out.println(i + " Serie " + c.display());
            }
            i++;
        }
    }

    public void searchByRating(double sTerm) {
        // Removes the 'current' item
        contentSort.removeIf(c -> c.getRating() < sTerm);
    }

    public void searchByGenre(String sTerm){
        //capitalize string
        sTerm = sTerm.substring(0, 1).toUpperCase() + sTerm.substring(1);

        //searchAlgoritme
        Iterator<Content> c = contentSort.iterator();
        while(c.hasNext()) {
            Content con = c.next();
            for(int i = 0; i < con.getGenre().length; i++){
                if(!con.getGenre()[i].contains(sTerm)) {
                    c.remove();
                }
            }
        }
    }

    public ArrayList searchByTitle(String sTerm) {
        ArrayList<Content> sortArray = new ArrayList<>();
        for (Content c : content) {
            if (c.getTitle().toLowerCase().contains(sTerm.toLowerCase())) {
                sortArray.add(c);
                contentSort.add(c);
                if (c instanceof Movie) {
                    System.out.println("film " + c.display());
                } else {
                    System.out.println("Serie " + c.display());
                }
            }

        }
        return sortArray;
    }

    public ArrayList searchForMovies(){
        ArrayList<Movie> movieArray = new ArrayList<>();
        for(Content m: content){
            if(m instanceof Movie){
                Movie h = (Movie) m;
                contentSort.add(h);
                movieArray.add(h);
            }
        }
        return movieArray;
    }

    public ArrayList searchForShows() {
        ArrayList<Show> ShowArray = new ArrayList<>();
        for (Content s : content) {
            contentSort.add(s);
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
