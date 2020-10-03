// CSE 143 AJ with Cherie Ruan
// Homework 1
// Name: YU DU
// Date: October 5, 2017

// Class LetterInventory keeps track of
// the inventory of each alphabetic letters

public class LetterInventory {
   
   private int[] letterCount; // list of integers which store counts of each letter
   private int size; // current sum of all of the counts
   
   public static final int NUMBER_OF_LETTERS = 26; // English have 26 letters in total
   
   // pre: receives a string and any non-alphabetic characters will be ignored
   // post: constructs an inventory that keeps track of the number of letters
   //       this given string has and updates the sum of the counts at the same time
   public LetterInventory(String data) {
      letterCount = new int[NUMBER_OF_LETTERS];
      size = 0;
      
      for (int i = 0; i < data.length(); i++) {
         char current = Character.toLowerCase(data.charAt(i));
         if (current - 'a' < NUMBER_OF_LETTERS && current - 'a' >= 0) {
            letterCount[current - 'a']++;
            size++;
         }
      }
   }
   
   // pre: given character must be an alphabetic character
   //      (throw an IllegalArgumentException otherwise)
   // post: returns the number of the given character in this inventory
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if (letter - 'a' >= NUMBER_OF_LETTERS || letter - 'a' < 0) {
         throw new IllegalArgumentException();
      }
      return letterCount[letter - 'a'];
   }
   
   // pre: given character must be an alphabetic character
   //      and given integer must be postivie or zero
   //      (throw an IllegalArgumentException otherwise)
   // post: changes the number of the given character into
   //       the given integer and updates the sum accordingly
   public void set(char letter, int value) {
      letter = Character.toLowerCase(letter);
      if (letter - 'a' >= NUMBER_OF_LETTERS || value < 0) {
         throw new IllegalArgumentException();
      }
      size += value - letterCount[letter - 'a'];
      letterCount[letter - 'a'] = value;
   }
   
   // post: returns the sum of all the counts in
   // this inventory
   public int size() {
      return size;
   }
   
   // post: returns whether this inventory is empty
   public boolean isEmpty() {
      return size == 0;
   }
   
   // post: returns a string of this inventory all in lowercase and
   //       in sorted order and surrounded by square brackets
   public String toString() {
      String result = "[";
      for (int i = 0; i < NUMBER_OF_LETTERS; i++) {
         for (int j = 0; j < letterCount[i]; j++) {
            result += "" + (char) ('a' + i);
            
         }
      }
      return result + "]";
   }
   
   // post: returns a new inventory which has combined counts and
   //       sum of counts of this inventory and given inventory
   public LetterInventory add(LetterInventory other) {
      LetterInventory sum = new LetterInventory("");
      for (int i = 0; i < NUMBER_OF_LETTERS; i++) {
         sum.letterCount[i] = this.letterCount[i] + other.letterCount[i];
      }
      sum.size = this.size + other.size;
      return sum;
   }
   
   // post: returns a new inventory which has the counts and sum of counts of
   //       subtracting the given inventory from this inventory
   //       returns null if any resulting count would be negative
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory result = new LetterInventory("");
      for (int i = 0; i < NUMBER_OF_LETTERS; i++) {
         result.letterCount[i] = this.letterCount[i] - other.letterCount[i];
         if (result.letterCount[i] < 0) {
            return null;
         } else {
            result.size = this.size - other.size;
         }
      }
      return result;
   }
}