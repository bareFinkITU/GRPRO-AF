package Model;

import SubModel.Media;
import SubModel.Movie;
import SubModel.Profile;
import SubModel.Show;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class MediaModel {

    private         ArrayList<Media> media;
    private         ArrayList<Media> mediaSort;
    private static  MediaModel instance;
    private         Media selectedMedia;
    private         Movie selectedMovie;
    private         Show selectedShow;

    private MediaModel() {
        media = new ArrayList<>();
        mediaSort = new ArrayList<>();
        initializeMedia();
        sortByTitle(media);
    }

    public ArrayList<String> getGenres(){
        //En liste af alle genre 1 gang, denne bruges ift. når genrene skal vises.
        ArrayList<String> genreList = new ArrayList<>();
        for (Media c: media){
            for (int i = 0; i < c.getGenre().length; i++) {
                //looper gennem genrene fra alle film/serier.
                if(!genreList.contains(c.getGenre()[i]) ){
                    //hvis listen ikke allerede indeholder genren, så tilføjes den.
                    genreList.add(c.getGenre()[i]);
                }
            }
        }
        return genreList;
    }

    public Movie getSelectedMovie(){
        return selectedMovie;
    }

    public Show getSelectedShow(){
        return selectedShow;
    }

    public static MediaModel getInstanceOf() { //singleton
        if (instance == null) {
            instance = new MediaModel();
        }
        return instance;
    }

    public void initializeMedia() {

        try {
            //movie scanner
            Scanner mReader = new Scanner(new File("out/movies/#movies.txt"));
            mReader.useDelimiter(";");

            while (mReader.hasNext()) {
                String      title = mReader.next().trim();
                int         year = Integer.parseInt(mReader.next().trim());
                String      genre = mReader.next().trim();
                String[]    arrOfGenres = genre.split(Pattern.quote(", ").trim());
                double      rating = Double.parseDouble(mReader.next().trim().replaceAll(",", "."));
                Image       cover = new Image(new FileInputStream("out/movies/" + title + ".jpg"));

                media.add(new Movie(title, arrOfGenres, rating, cover, year));
                mReader.nextLine();
            }
            mReader.close();
        } catch (IOException e) {
            /*System.out.println("There was a problem reading from the #movies.txt file");*/
        }

        try {
            //shows scanner
            Scanner sReader = new Scanner(new File("out/shows/#shows.txt"));
            sReader.useDelimiter(";");
            //splitter strengen op hver gang den møder semi-kolon
            while (sReader.hasNext()) {
                String      title = sReader.next().trim();
                String      runtime = sReader.next().trim();
                int         startYear = Integer.parseInt(runtime.substring(0, 4));
                int         endYear;
                String      genre = sReader.next().trim();
                String[]    arrOfGenres = genre.split(Pattern.quote(", ").trim());
                double      rating = Double.parseDouble(sReader.next().trim().replaceAll(",", "."));
                String      seasons = sReader.next().trim();
                Image       cover = new Image(new FileInputStream("out/shows/" + title + ".jpg"));

                if (runtime.length() > 5) {
                    endYear = Integer.parseInt(runtime.substring(5, 9));
                }else{
                    endYear = Calendar.getInstance().get(Calendar.YEAR);
                }

                media.add(new Show(title, arrOfGenres, rating, cover, runtime, startYear, endYear, seasons));
                sReader.nextLine();
            }
            sReader.close();
        } catch (IOException e) {
            /*System.out.println("There was a problem reading from the #shows.txt file");*/
        }
        //adds all movies and shows to an arrayList used to store search / sort terms
        mediaSort.addAll(media);

    }

    public Media getSelectedMedia(){
        return selectedMedia;
    }

    public void setSelectedMedia(Media m){
        selectedMedia = m;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public ArrayList<Media> getMediaSort() {
        return mediaSort;
    }

    public void resetMediaSort() {
        mediaSort.clear();
        mediaSort.addAll(media);
    }

    // DISPLAY USED FOR TESTING

    //SEARCH METODER
    public ArrayList searchByRating(double sTerm) {
        // Iterates over Arraylist and deleting all items that with a lower rating than "sTerm"
        mediaSort.removeIf(media -> media.getRating() < sTerm);
        {
            //mediaSort.sort(Comparator.comparingDouble(Media::getRating).reversed());
            return mediaSort;
        }
    }

    public ArrayList<Media> searchByYear(int sTermLower, int sTermUpper) {
        mediaSort.removeIf(media -> {
            if (media instanceof Movie) {
                return ((Movie) media).getYear() < sTermLower || ((Movie) media).getYear() > sTermUpper;
            } else {
                return ((Show) media).getStartYear() < sTermLower || ((Show) media).getEndYear() > sTermUpper;
            }
        });
        return mediaSort;
    }

    //For explanation look at the searchByRating method
    public ArrayList searchByTitle(String sTerm) {
        mediaSort.removeIf(media -> !media.getTitle().toLowerCase().contains(sTerm.toLowerCase()));
        mediaSort.sort(Comparator.comparingDouble(Media::getRating).reversed());
        return mediaSort;
    }

    //same as searchByRating method but instead of looking for a searchterm, this method
    //checks if the item is a Movie
    public ArrayList searchForMovies() {
        mediaSort.removeIf(media -> {
            return media instanceof Show;
        });
        return mediaSort;
    }

    public ArrayList searchForShows() {
        mediaSort.removeIf(media -> {
            return media instanceof Movie;
        });
        return mediaSort;
    }

    public ArrayList<Media> searchByGenre(String sTerm) {
        //creates local array of items that is the same genre as the search
        ArrayList<Media> arrayOfSearchTerm = new ArrayList<>();

        //forloop with a nestet loop that loops over the Array of genres that each item has
        //if the items contains the search term it is added to the local array
        for (Media c : mediaSort) {
            for (int i = 0; i < c.getGenre().length; i++) {
                if (c.getGenre()[i].toLowerCase().contains(sTerm.toLowerCase())) {
                    arrayOfSearchTerm.add(c);
                }
            }

        }
        //compares the local and mediaSort array and returns the intersection of the two.
        mediaSort.retainAll(arrayOfSearchTerm);
        return mediaSort;
    }

    //SORTING METHOD
    public void sortByTitle( ArrayList<Media> array) {
        array.sort(Comparator.comparing(Media::getTitle));
    }
    
    public HashMap getSeasonAndEpisodesMap(Show show) {
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

    public ArrayList searchInMyList(Profile profile){
        mediaSort.retainAll(profile.getFavorites());
        return mediaSort;
    }

    public Boolean selectedMediaIsMovie(){
        if (selectedMedia instanceof Movie){
            selectedMovie = (Movie) selectedMedia;
            return true;
        }
        return false;
    }

    public Boolean selectedMediaIsShow(){
        if (selectedMedia instanceof Show){
            selectedShow = (Show) selectedMedia;
            return true;
        }
        return false;
    }
}
