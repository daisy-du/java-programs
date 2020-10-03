// YU DU
// CSE 143 AJ with Cherie Ruan
// Homework 6
// Date: November 16, 2017

// Class AnagramSolver is used to find and print
// all the combination of words (in given list)
// that have the same letters as a given phrase

import java.util.*;

public class AnagramSolver {
   
   private List<String> dictionary;
   private Map<String, LetterInventory> wordAndLetterInventorys;
   
   // post: constructs an AnagramSolver object and
   //       stores the given list as String and
   //       LetterInventory pairs as dictionary
   public AnagramSolver(List<String> list) {
      dictionary = list;
      wordAndLetterInventorys = new HashMap<String, LetterInventory>();
      for (String word: dictionary) {
         wordAndLetterInventorys.put(word, new LetterInventory(word));
      }
   }
   
   // pre : max >= 0 (throws IllegalArgumentException otherwise)
   // post: finds and prints to System.out all combinations of
   //       words (from the dictionary) that have the same letters
   //       as a given phrase and keeps the number of words in the combination
   //       less or equal to given max times; if max == 0, prints all the
   //       combinations
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      List<String> smallerDictionary = new ArrayList<String>();
      LetterInventory target = new LetterInventory(s);
      for (String word : dictionary) {
         if (target.subtract(wordAndLetterInventorys.get(word)) != null) {
            smallerDictionary.add(word);
         }
      }
      List<String> combinations = new ArrayList<String>();
      print(target, smallerDictionary, max, combinations);
   }
   
   // post: prints when the given target is satisfied, that is when the word
   //       combination can complete the given target; when it is not satisfied,
   //       continue building the combination by filtering through all the words
   //       from the given smaller dictionary; only continue building up combination
   //       if the number of words inside the combination is less than
   //       given max or max == 0
   private void print(LetterInventory target, List<String> dictionary, int max,
   List<String> combinations) {
      if (target.isEmpty()) {
         System.out.println(combinations);
      }
      if (combinations.size() < max || max == 0) {
         for (int i = 0; i < dictionary.size(); i++) {
            LetterInventory word = new LetterInventory(dictionary.get(i));
            if (target.subtract(word) != null) {
               combinations.add(dictionary.get(i));
               print(target.subtract(word), dictionary, max, combinations);
               combinations.remove(combinations.size() - 1);
            }
         }
      }
   }
}