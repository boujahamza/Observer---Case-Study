import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings({ "deprecation", "serial" })
public abstract class AbstractObserver extends JFrame implements Observer, ActionListener{
	private String name;
	private Subject subject;
	private JLabel label = new JLabel("State Binary Value : Undefined");
	private JPanel labelPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel emptyPanel = new JPanel();
	private JButton subscribe = new JButton("Subscribe-Me");
	private JButton unsubscribe = new JButton("Unsubscribe-Me");
	
	public AbstractObserver(Subject subject,String name) {
		super("Welcome to the "+name+" Observer");
		this.name = name;
		this.subject = subject;
		this.setLayout(new FlowLayout());
		labelPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		labelPanel.add(label);
		buttonPanel.add(subscribe);
		buttonPanel.add(unsubscribe);
		emptyPanel.setPreferredSize(new Dimension(300,150));
		subscribe.addActionListener((ActionListener) this);
		unsubscribe.addActionListener((ActionListener) this);
		this.add(labelPanel);
		this.add(emptyPanel);
		this.add(buttonPanel);
		this.subscribe.setEnabled(true);
		this.unsubscribe.setEnabled(false);
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(name.toLowerCase().equals("binary"))
			this.getLabel().setText("State Binary Value: " + Integer.toBinaryString(subject.getState()));
		else if(name.toLowerCase().equals("octal"))
			this.getLabel().setText("State Octal Value: " + Integer.toOctalString(subject.getState()));
		else if(name.toLowerCase().equals("hexa"))
			this.getLabel().setText("State Hexa Value: " + Integer.toHexString(subject.getState()));
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == subscribe) {
			this.subject.addObserver(this);;
			this.subscribe.setEnabled(false);
			this.unsubscribe.setEnabled(true);
		}
		else if(event.getSource() == unsubscribe) {
			this.subject.deleteObserver(this);;
			this.getLabel().setText("State "+name+" Value: Undefined");
			this.subscribe.setEnabled(true);
			this.unsubscribe.setEnabled(false);
		}
	}

}
