package multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
	
	Queue<T> queue;
	int limit = 100;
	
	public BlockingQueue(){
		queue = new LinkedList<>();
	}
	
	public BlockingQueue(int limit){
		queue = new LinkedList<>();
		this.limit = limit;
	}
	
	public synchronized void insert(T data) throws InterruptedException{
		
		while(queue.size() == limit){
			this.wait();
		}
		
		if(queue.size() == 0){
			this.notifyAll();
		}
		
		queue.add(data);
		
	}
	
	public synchronized T remove() throws InterruptedException{
		
		while(queue.isEmpty()){
			this.wait();
		}
		
		if(queue.size() == limit){
			this.notifyAll();
		}
		
		return queue.remove();
		
	}
	
}
