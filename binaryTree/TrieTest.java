package binaryTree;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TrieTest{
    
    public static void main(String[] args){
        
        String text = readFile("TrieTest.txt");
        String[] words = getWords(text);
        Trie t = createIndex(words);
        //t.print();
        
        printPositions(t,"and");
    }
    
    static void printPositions(Trie t,String s){
        if(t.contains(s)){
            System.out.println(s+": "+t.getItem(s));
        }
    }
    
    static Trie createIndex(String[] words){
        Trie t = new Trie();
        for(int i=0;i<words.length;i++){
            t.getItem(words[i]).add(i);
        }
        return t;
    }
    
    static String[] getWords(String text){
        return text.toLowerCase().split(" ");
    }
    
    static String readFile(String filename){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename), Charset.defaultCharset())){
            String line = null;
            while ( (line = reader.readLine()) != null ) {sb.append(line);}
        }catch(Exception e){e.printStackTrace();}
        return sb.toString();
    }
}