package multithreading;

public class WaitNotify {
	public static void main(String[] args) {
		System.out.println(WaitNotify.class.getSimpleName());
		
		Object obj = new Object();
		MyThread t = new MyThread(obj);
		t.start();
		
		// test sending message to thread
		for(int i=1; i<=5; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(obj){
				t.setText("message " + i);
				//if(i==2) t.isCancelled = true;
				obj.notify();
			}
		}
		
	}
	
	
	public static class MyThread extends Thread {
		Object obj;
		String s = "";
		boolean isCancelled = false;
		
		public MyThread(Object obj){
			this.obj = obj;
		}
		
		public void setText(String str){
			this.s = str;
		}
		
		public void run(){
			while(!isCancelled){
				synchronized(obj){
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("MyThread-> " + s);
				}
			}
		}
	}
}
