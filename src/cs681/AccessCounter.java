package cs681;
import java.util.HashMap;
import java.util.concurrent.locks.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccessCounter {

	private static AccessCounter instance = null;
	private HashMap<java.nio.file.Path, Integer> accessCounter =
								new HashMap<java.nio.file.Path, Integer>();

	private ReentrantLock lockMethods = new ReentrantLock();
	private static ReentrantLock lockInstance = new ReentrantLock();

	public AccessCounter() {}

	public static AccessCounter getInstance() {
		lockInstance.lock();
		try{
			if(instance==null) {
				instance = new AccessCounter();
			}
			return instance;
		} finally {
			lockInstance.unlock();
		}
	}

	public void increment(Path path) {
		lockMethods.lock();
		try {
			if(this.accessCounter.containsKey(path)) {
				System.out.println("increment(): <" + path + "> already contained -> increment by 1");
				this.accessCounter.put(path, this.accessCounter.get(path) + 1);
			}
			else {
				System.out.println("increment(): <" + path + "> not contained -> add & set count to 1");
				this.accessCounter.put(path, 1);
			}
		} finally {
		lockMethods.unlock();
		}
	}

	public Integer getCount(Path path) {
		lockMethods.lock();
		try {
			if(this.accessCounter.containsKey(path)){
				System.out.println("getcount(): <" + path + "> has been accessed " + this.accessCounter.get(path) + " times");
				return this.accessCounter.get(path);
			}
			else {
				return 0;
			}
		} finally {
		lockMethods.unlock();
		}
	}	
}
