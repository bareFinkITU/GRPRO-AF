package controller;

import UserMVC.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Content;
import model.Movie;
import model.Show;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ContentController {
    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;
    private ArrayList<Content> content;
    private ArrayList<Content> contentSort;
    private static ContentController instance;
    private Users brugere = Users.getInstanceOf();
    private Content selectedContent;
    @FXML
    private BorderPane contentSceneBP;


    private ContentController() {
        content = new ArrayList<>();
        contentSort = new ArrayList<>();
        movies = new ArrayList<>();
        shows = new ArrayList<>();
        initializeContent();
        sortByTitle(content);
    }

    public ArrayList<String> getGenres(){
        ArrayList<String> genreMagi = new ArrayList<>();
        for (Content c: content){
            for (int i = 0; i < c.getGenre().length; i++) {
                if(!genreMagi.contains(c.getGenre()[i]) ){
                    genreMagi.add(c.getGenre()[i]);
                }
            }
        }
        return genreMagi;
    }


    public static ContentController getInstanceOf() {
        if (instance == null) {
            instance = new ContentController();
        }
        return instance;
    }

    public void initializeContent() {

        try {
            //movie scanner
            Scanner mReader = new Scanner(new File("out/movies/#movies.txt"));
            mReader.useDelimiter(";");

            while (mReader.hasNext()) {
                String title = mReader.next().trim();
                int year = Integer.parseInt(mReader.next().trim());
                String genre = mReader.next().trim();
                String[] arrOfGenres = genre.split(Pattern.quote(", ").trim());
                double rating = Double.parseDouble(mReader.next().trim().replaceAll(",", "."));
                Image cover = new Image(new FileInputStream("out/movies/" + title + ".jpg"));

                content.add(new Movie(title, arrOfGenres, rating, cover, year));
                movies.add(new Movie(title, arrOfGenres, rating, cover, year));
                mReader.nextLine();
            }
            mReader.close();
        } catch (IOException e) {
            System.out.println("There was a problem reading from the #movies.txt file");
        }

        try {
            //shows scanner
            Scanner sReader = new Scanner(new File("out/shows/#shows.txt"));
            sReader.useDelimiter(";");
            //splitter strengen op hver gang den møder semi-kolon
            while (sReader.hasNext()) {
                String title = sReader.next().trim();
                String runtime = sReader.next().trim();

                int startYear = Integer.parseInt(runtime.substring(0, 4));
                int endYear;
                if (runtime.length() > 5) {
                    endYear = Integer.parseInt(runtime.substring(5, 9));
                }else{
                    endYear = Calendar.getInstance().get(Calendar.YEAR);
                }
                String genre = sReader.next().trim();
                String[] arrOfGenres = genre.split(Pattern.quote(", ").trim());
                double rating = Double.parseDouble(sReader.next().trim().replaceAll(",", "."));
                String seasons = sReader.next().trim();
                Image cover = new Image(new FileInputStream("out/shows/" + title + ".jpg"));

                content.add(new Show(title, arrOfGenres, rating, cover, runtime, startYear, endYear, seasons));
                shows.add(new Show(title, arrOfGenres, rating, cover, runtime, startYear, endYear, seasons));
                sReader.nextLine();
            }
            sReader.close();
        } catch (IOException e) {
            System.out.println("There was a problem reading from the #shows.txt file");
        }
        //shuffles the arraylist so that it doesnt display movies and then series.
        //adds all movies and shows to an arrayList used to store search / sort terms
        contentSort.addAll(content);

    }

    public Content getSelectedContent(){return selectedContent;}

    public ArrayList<Content> getContent() {
        return content;
    }

    public ArrayList<Content> getContentSort() {
        return contentSort;
    }

    public void resetContentSort() {
        contentSort.clear();
        contentSort.addAll(content);
    }

    // DISPLAY USED FOR TESTING
    public void display() {
        int i = 1;
        for (Content c : contentSort) {
            if (c instanceof Movie) {
                System.out.println(i + " Movie " + c.display());
            } else {
                System.out.println(i + " Show " + c.display());
            }
            i++;
        }

    }

    //SEARCH METODER
    public ArrayList searchByRating(double sTerm) {
        // Iterates over Arraylist and deleting all items that with a lower rating than "sTerm"
        contentSort.removeIf(content -> content.getRating() < sTerm);
        {
            //contentSort.sort(Comparator.comparingDouble(Content::getRating).reversed());
            return contentSort;
        }
    }

    public ArrayList<Content> searchByYear(int sTermLower, int sTermUpper) {
        contentSort.removeIf(content -> {
            if (content instanceof Movie) {
                return ((Movie) content).getYear() < sTermLower || ((Movie) content).getYear() > sTermUpper;
            } else {
                return ((Show) content).getStartYear() < sTermLower || ((Show) content).getEndYear() > sTermUpper;
            }
        });
        return contentSort;
    }

    //For explanation look at the searchByRating method
    public ArrayList searchByTitle(String sTerm) {
        contentSort.removeIf(content -> !content.getTitle().toLowerCase().contains(sTerm.toLowerCase()));
        contentSort.sort(Comparator.comparingDouble(Content::getRating).reversed());
        return contentSort;
    }

    //same as searchByRating method but instead of looking for a searchterm, this method
    //checks if the item is a Movie
    public ArrayList searchForMovies() {
        contentSort.removeIf(content -> {
            return content instanceof Show;
        });
        return contentSort;
    }

    public ArrayList searchForShows() {
        contentSort.removeIf(content -> {
            return content instanceof Movie;
        });
        return contentSort;
    }

    public ArrayList<Content> searchByGenre(String sTerm) {
        //creates local array of items that is the same genre as the search
        ArrayList<Content> arrayOfSearchTerm = new ArrayList<>();

        //forloop with a nestet loop that loops over the Array of genres that each item has
        //if the items contains the search term it is added to the local array
        for (Content c : contentSort) {
            for (int i = 0; i < c.getGenre().length; i++) {
                if (c.getGenre()[i].toLowerCase().contains(sTerm.toLowerCase())) {
                    arrayOfSearchTerm.add(c);
                }
            }

        }
        //compares the local and contentSort array and returns the intersection of the two.
        contentSort.retainAll(arrayOfSearchTerm);
        return contentSort;
    }

    //SORTING METHOD
    public void sortByTitle( ArrayList<Content> array) {
        array.sort(Comparator.comparing(Content::getTitle));
    }
    
    public HashMap getSeasonAndEpisodesMap( Show show) {
        //creates hashmap to store key: season, value: episodes
        HashMap<Integer, Integer> hash_map = new HashMap<>();

        //creates an array of strings that contains "season-episodes"
        String[] tempString = show.getSeasons().replaceAll(" ", "").split(",");

        for (String s : tempString) {
            //first defines lastindex of the split charecter '-'
            int lastIndexOf = s.lastIndexOf('-');

            //parses the substring before and after the '-' which correspond to season and episodes
            int season = Integer.parseInt(s.substring(0, lastIndexOf));
            int episodes = Integer.parseInt(s.substring(lastIndexOf + 1));

            //adds the season and episodes to the hashmap
            hash_map.put(season, episodes);
        }
        return hash_map;
    }


    public void displaySeasonAndEpisodes(Show show) {
        HashMap hash_map = getSeasonAndEpisodesMap(show);
        for (Object k : hash_map.keySet()) {
            String key = k.toString();
            String value = hash_map.get(k).toString();
            System.out.println(key + " episodes " + value);
        }
    }


    public void drawContentList( List<Content> contents,  FlowPane list) {
        list.getChildren().clear();
        for (Content c : contents) {
            Button newButton = new Button();
            newButton.setGraphic(new ImageView(c.getCover()));
            newButton.setStyle(" -fx-background-color: transparent");
            newButton.setOnAction(e -> {
                selectedContent = c;

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/ContentScene/ContentSceneView.fxml"));
                try {
                    contentSceneBP = loader.load();
                    Stage Megaflix = (Stage) newButton.getScene().getWindow();
                    Megaflix.setScene(new Scene(contentSceneBP));
                } catch (IOException t) {
                    t.printStackTrace();
                }

            });
            newButton.setOnMouseEntered(e -> {
                newButton.setScaleX(1.1);
                newButton.setScaleY(1.1);
            });
            newButton.setOnMouseExited(e -> {
                newButton.setScaleY(1);
                newButton.setScaleX(1);
            });

            list.getChildren().addAll(newButton);
        }
    }

}
