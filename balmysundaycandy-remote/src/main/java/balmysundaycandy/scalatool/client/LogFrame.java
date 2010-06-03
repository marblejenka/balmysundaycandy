package balmysundaycandy.scalatool.client;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LogFrame extends JFrame {

	Dimension selfdimension = new Dimension(400,800);
	
	TextArea textArea = new TextArea();

	public LogFrame() {
		setSize(selfdimension);

		getContentPane().add(textArea);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
	public void addlog(String log) {
		textArea.setText(textArea.getText() + log);
	}

	public static void main(String[] args) {
		new LogFrame();
	}
}
