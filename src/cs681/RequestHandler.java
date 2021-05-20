package cs681;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler extends AccessCounter implements Runnable {

	private Path path;
	private boolean done = false;
	private final ReentrantLock lock = new ReentrantLock();
	
	public RequestHandler(Path p) {
		this.path = p;
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}
	
	public void run() {
		lock.lock();
		try {
			if (done) {
				System.out.println("Stopped access counter.");
			}
			AccessCounter ac = AccessCounter.getInstance();
			ac.increment(this.path);
			ac.getCount(this.path);
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

		Path a = Paths.get("a.html");
		Path b = Paths.get("b.html");

		RequestHandler req1 = new RequestHandler(a);
		RequestHandler req2 = new RequestHandler(b);
		RequestHandler req3 = new RequestHandler(a);
		RequestHandler req4 = new RequestHandler(b);
		RequestHandler req5 = new RequestHandler(a);
		RequestHandler req6 = new RequestHandler(b);
		RequestHandler req7 = new RequestHandler(a);
		RequestHandler req8 = new RequestHandler(b);
		RequestHandler req9 = new RequestHandler(a);
		RequestHandler req10 = new RequestHandler(b);
		RequestHandler req11= new RequestHandler(a);
		RequestHandler req12 = new RequestHandler(b);
		
		Thread t1 = new Thread(req1);
		Thread t2 = new Thread(req2);
		Thread t3 = new Thread(req3);
		Thread t4 = new Thread(req4);
		Thread t5 = new Thread(req5);
		Thread t6 = new Thread(req6);
		Thread t7 = new Thread(req7);
		Thread t8 = new Thread(req8);
		Thread t9 = new Thread(req9);
		Thread t10 = new Thread(req10);
		Thread t11 = new Thread(req11);
		Thread t12 = new Thread(req12);
		
		t1.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req1.setDone();
		t1.interrupt();
		System.out.println("thread 1 done");

		t2.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req2.setDone();
		t2.interrupt();
		System.out.println("thread 2 done");

		t3.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req3.setDone();
		t3.interrupt();
		System.out.println("thread 3 done");

		t4.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req4.setDone();
		t4.interrupt();
		System.out.println("thread 4 done");

		t5.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req5.setDone();
		t5.interrupt();
		System.out.println("thread 5 done");

		t6.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req6.setDone();
		t6.interrupt();
		System.out.println("thread 6 done");

		t7.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req7.setDone();
		t7.interrupt();
		System.out.println("thread 7 done");

		t8.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req8.setDone();
		t8.interrupt();
		System.out.println("thread 8 done");

		t9.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req9.setDone();
		t9.interrupt();
		System.out.println("thread 9 done");

		t10.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req10.setDone();
		t10.interrupt();
		System.out.println("thread 10 done");

		t11.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req11.setDone();
		t11.interrupt();
		System.out.println("thread 11 done");

		t12.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		req12.setDone();
		t12.interrupt();
		System.out.println("thread 12 done");
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
			t11.join();
			t12.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("done");
        
	}

}
