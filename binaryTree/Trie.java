package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Prefix based binary tree
 * l = average length of words & n = number of words
 * Insertion/Deletion - 0(l*n) 
 * Search 0(l)
 * */
class Trie{
    
    
     Node root = new Node();

     boolean contains(String word){
         Node current = root;
         char[] word_ = word.toCharArray();
         for(Character c:word_){
             Node next = current.children.get(c);
             if(next==null) return false;
             else current=next;
         }
         return current.isTerminal;
     }
    

     List<Integer> getItem(String word){
         Node current = root;
         char[] word_ = word.toCharArray();
         for(Character c:word_){
              Node next = current.children.get(c);
              if(next==null) {
                next = new Node(c); 
                current.children.put(c,next); 
              }
              current=next;
         }
         current.isTerminal=true;
         return current.positions;
     }
     
     void print(){
         List<Node> l = new ArrayList<>();
         l.add(root);
         output(l,"");
     }
     
     //Depth First Search
     void output(List<Node> currentPath,String indent){
         Node current = currentPath.get(currentPath.size()-1);
         if(current.isTerminal){
             String word="";
             for(Node n:currentPath) word+=n.letter;
             System.out.println(indent+word+" "+current.positions);
             indent+="  ";
         }
         for(Entry<Character,Node> e:current.children.entrySet()){
             Node node = e.getValue();
             List<Node> newlist = new ArrayList<>();
             newlist.addAll(currentPath);
             newlist.add(node);
             output(newlist,indent);
         }
     }

}

class Node{
    char letter;
    boolean isTerminal=false;
    Map<Character,Node> children=new TreeMap<>();
    List<Integer> positions = new ArrayList<>();
    public Node(){}
    public Node(char letter){
        this.letter=letter;
    }
}

