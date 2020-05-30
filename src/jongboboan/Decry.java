package jongboboan;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Decry extends JPanel{
	JButton result = new JButton("결과");
	JLabel amhokey = new JLabel("암호키 : ");
	JLabel atext = new JLabel("문장 : ");
	
	public Decry() {
		setLayout(null);
		
		result.setBounds(200, 250,100,80);
		amhokey.setBounds(100, 100, 50,50);
		atext.setBounds(100, 150, 50,50);
		
		add(result);
		add(amhokey);
		add(atext);
	}
	
}
