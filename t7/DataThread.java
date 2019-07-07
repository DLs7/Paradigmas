import java.util.ArrayList;

public class DataThread {

    ArrayList<GitHubData> data;
    
	public DataThread(ArrayList<GitHubData> data) {
		this.data = data;
    }
    
	public synchronized void conditionWait() {
		try {
            this.wait();
        } catch (InterruptedException e) {}
    }
    
	public synchronized void conditionNotify() {
		this.notifyAll();
	}
}