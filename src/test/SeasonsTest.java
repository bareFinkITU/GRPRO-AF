package test;

import controller.ContentController;
import model.Show;

import java.io.IOException;
import java.util.HashMap;

public class SeasonsTest {
    public static void main(String[] args) throws IOException {

        ContentController test = new ContentController();
        test.initializeContent();
        Show s = new Show("test", new String[]{"krimi, drama"}, 9.9,null,"1999-2019","1-22, 2-12, 3-17, 4-9");

        for(Object k: test.getSeasonAndEpisodesMap(s).keySet()){
            String key = k.toString();
            String value = test.getSeasonAndEpisodesMap(s).get(k).toString();
            System.out.println(key + " episodes " + value);

        }
    }
}
