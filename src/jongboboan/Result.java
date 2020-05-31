package jongboboan;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Result extends JPanel{
	encryp encr = new encryp();
	JLabel R_amhokey = new JLabel();
	JLabel R_text = new JLabel();
	JLabel R_amtext = new JLabel();
	
	public Result(String key, String str, String encry){
		
		setLayout(null);
		R_amhokey.setBounds(150, 100, 200, 50);
		R_text.setBounds(150, 150, 200, 50);
		R_amtext.setBounds(150, 200, 200, 50);
		
		R_amhokey.setText("암호키 : "+key);
		R_text.setText("평문 : " + str);
		R_amtext.setText("암호문 : "+encry);
		
		add(R_amhokey);
		add(R_text);
		add(R_amtext);
	}
}
