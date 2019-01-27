package org.dreambig.iexpress.swearfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class SwearWordsFilter {

    public static final SwearWordsFilter instance =  new SwearWordsFilter();

    private final CopyOnWriteArrayList<String> swearWords;

    private SwearWordsFilter() {
        try {
            //Get file from resources folder
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("bagOfWords").getFile());
            List<String> words = new ArrayList<>();
            String line = null;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null)
                    words.add(line);
            }
            swearWords = new CopyOnWriteArrayList<String>(words);
        }
        catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }

    public static SwearWordsFilter getInstance() {
        return instance;
    }

    public boolean haveSwearWords(String sentence){
        List<String> sentenceWithStopWords = StopWords.removeStopWords(sentence);
       long swearWordsCnt =sentenceWithStopWords.stream().filter(swearWords::contains).count();
        System.out.println(" swear words found ="+swearWordsCnt);
        return swearWordsCnt != 0 ;
    }
}
