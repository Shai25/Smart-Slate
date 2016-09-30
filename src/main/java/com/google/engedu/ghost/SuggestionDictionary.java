package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SuggestionDictionary {

    private TstNode root;

    public SuggestionDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        root = new TstNode("a");
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= 1)
            {
                root.firstTime=0;
                root.position=0;
                root.add(line.trim());
            }
        }
    }

    public boolean isWord(String word) {
        return root.isWord(word);
    }

    public  String suggestion(String wrong)
    {
        return root.suggestion(wrong);
    }
    public  String suggestion2(String wrong)
    {
        return root.suggestion2(wrong);
    }

}
