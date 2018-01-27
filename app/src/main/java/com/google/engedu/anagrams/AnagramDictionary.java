/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> arr = new ArrayList<>();
    HashSet<String> set = new HashSet<>();
    HashMap<String,ArrayList<String>> map = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            arr.add(word);
            set.add(word);

            if(map.containsKey(sortLetter(word))){
                map.get(sortLetter(word)).add(word);
            }else{
               ArrayList<String> arr1 = new ArrayList<String>();
               arr1.add(word);
               map.put(sortLetter(word), arr1);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
       /* for(String i:set){
            if(word.equals(i) && !word.contains(base)){
                return true;
            }
        }*/
       if(set.contains(word) && !word.contains(base))
           return true;
    else
        return false;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        int i=0;
        while(i<arr.size()){
            if(sortLetter(targetWord).equals(sortLetter(arr.get(i)))){
                result.add(arr.get(i));
                map.put(pickGoodStarterWord(),result);
            }
            i++;
        }
        return result;
    }
    private String sortLetter(String word) {
        char[] temp = word.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }
    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> result1 = new ArrayList<String>();

            for(char i='a';i<='z';i++){
                String temp=word;
//                temp=temp.concat(""+i);
                temp+=i;
                System.out.println(temp);
                if(map.containsKey(sortLetter(temp)) /*&& isGoodWord(temp,word)*/){
                    result.addAll(map.get(sortLetter(temp)));
                }
            }
        return result;
    }

    public String pickGoodStarterWord() {
        return "leaf";
    }
}
