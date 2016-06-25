package datastructure;

public class MinStack {
    
    static Node top;
    static int minVal = Integer.MAX_VALUE;

    public static void main(String[] args) {
        
        MinStack stack = new MinStack();
        int N = 5;
        while(N-- > 0){
                int data = N;
                // keeps a pointer to the minVal
                minVal = Math.min(minVal, data);
                stack.push(data, minVal);
       }
            
     // pop example
        stack.pop(stack);
        stack.pop(stack);
        stack.pop(stack);
     
     // returns min
     int retMin = stack.peekMin(stack);
     System.out.println(retMin);
     

//     int n = 5;
//     String binary = Integer.toBinaryString(n); // base 2
//     System.out.println(binary);
        
    }
    
    private int peekMin(MinStack stack){
        return stack.peek().min;
    }
    
    private void push(int data, int min){
        Node newNode = new Node(data, min);
        if(top==null){
            top = newNode;
        }else{
            newNode.next = top;
            top = newNode;
        }
    }
    
    private int pop(MinStack stack){
        if(top == null) return 0;
        int item = top.data;
        top = top.next;

        minVal = stack.peek() != null ? stack.peek().min : Integer.MAX_VALUE;
        
        return item;
    }
    
      private Node peek(){
        if(top == null) return null;
        return top;
    }
    
    private boolean isEmpty(){
        return top == null;
    }
    
    
    public class Node{
        public int data;
        public int min;
        public Node next;
        public Node(int data, int min){
            this.data = data;
            this.min = min;
            this.next = null;
        }
    }
}