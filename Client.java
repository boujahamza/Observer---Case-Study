import java.util.Observer;

@SuppressWarnings("deprecation")
public class Client {

	public static void main(String[] args) {
		Subject subject = new Subject(0);
		Observer binary = new BinaryObserver(subject);
		Observer octal = new OctalObserver(subject);
		Observer hexa = new HexaObserver(subject);
		Thread thread = new Thread(subject);
		thread.start();
	}
}
