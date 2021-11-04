import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Subject extends Observable implements Runnable {
	private List<Observer> observers = new ArrayList<Observer>();
	private int state;
	
	public Subject(int val) {
		this.setState(val);
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
		this.notifyObservers();
	}
	
	
	
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				this.setState((this.getState()+1)%60);
				Thread.sleep(2000);
				this.setChanged();
			}
		}catch(InterruptedException e){
			
		}
	}
}
