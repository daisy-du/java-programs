// YU DU
// CSE 143 AJ with Cherie Ruan
// Homework 5
// Date: November 3, 2017

// Class GrammarSolver is used to store and use different grammars
// in Backus-Naur Form(BNF) to makes random sentence from the grammar.

import java.util.*;

public class GrammarSolver {
   private SortedMap<String, List<List<String>>> grammar;
   
   // pre: given list is not empty and there is
   //       only one entires for each nonterminal
   //       in the given List (throws IllegalArgumentException otherwise)
   // post: constructs a GrammarSolver object that stores
   //       the rules and corresponding value pairs from the given grammar
   public GrammarSolver(List<String> grammar) {
      if (grammar.isEmpty()) {
         throw new IllegalArgumentException();
      }
      this.grammar = new TreeMap<String, List<List<String>>>();
      for (String sentence: grammar) {
         String[] rulesAndValues = sentence.split("::=");
         String rules = rulesAndValues[0];
         if (grammarContains(rules)) {
            throw new IllegalArgumentException();
         }
         String[] smallparts = rulesAndValues[1].split("[|]");
         List<List<String>> multivalue = new ArrayList<>();
         for (String part: smallparts) {
            List<String> value = new ArrayList<>();
            String[] words = part.trim().split("[ \t]+");
            for (String word : words) {
               value.add(word.trim());
            }
            multivalue.add(value);
         }
         this.grammar.put(rules, multivalue);
      } 
   }
   
   // post: returns whether given symbol is a
   //       nonterminal
   public boolean grammarContains(String symbol) {
      return grammar.containsKey(symbol);
   }
   
   // pre : grammar contains the given symbol and times >= 0
   //       (throws IllegalArgumentException otherwise)
   // post: returns an array of String which contains
   //       given number of words corresponding to given symbol
   public String[] generate(String symbol, int times) {
      if (!grammarContains(symbol) || times < 0) {
         throw new IllegalArgumentException();
      }
      Random random = new Random();
      String[] sentence = new String[times];
      for (int i = 0; i < times; i++) {
         sentence[i] = generate(random, symbol).trim();
      }
      return sentence;
   }
   
   // post: randomly generates one String under the same rule
   //        as the given symbol and returns that String
   private String generate(Random random, String symbol) {
      if (grammarContains(symbol)) {
         List<List<String>> rules = grammar.get(symbol);
         List<String> rule = rules.get(random.nextInt(rules.size()));
         String result = "";
         for (String element : rule) {
            result += generate(random, element);
         }
         return result;
      } else {
         return symbol.trim() + " ";
      }
   }
   
   // post: returns a string representation of nonterminal symbols from
   //       the grammar as a sorted, comma-separated list enclosed in square brackets
   public String getSymbols() {
      return grammar.keySet().toString();
   }
}