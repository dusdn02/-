package jongboboan;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Result extends JPanel {
	amho amho;

	public Result(encryp ende) {

		JLabel R_amhokey = new JLabel();
		JLabel R_text = new JLabel();
		JLabel R_amtext = new JLabel();
		String key = null;
		String t_str= null;
		String a_str= null;

		setLayout(null);
		R_amhokey.setBounds(150, 100, 300, 50);
		R_text.setBounds(150, 150, 300, 50);
		R_amtext.setBounds(150, 200, 300, 50);
		
		key=ende.key.toString();
		t_str=ende.str.toString();
		a_str=ende.encryption.toString();

		
		R_amhokey.setText("암호키 : " + key);
		R_text.setText("평문 : " + t_str);
		R_amtext.setText("암호문 : " + a_str);

		add(R_amhokey);
		add(R_text);
		add(R_amtext);

	}
	public Result(Decry ende) {

		JLabel R_amhokey = new JLabel();
		JLabel R_text = new JLabel();
		JLabel R_amtext = new JLabel();
		String key = null;
		String t_str= null;
		String a_str= null;

		setLayout(null);
		R_amhokey.setBounds(150, 100, 300, 50);
		R_text.setBounds(150, 150, 300, 50);
		R_amtext.setBounds(150, 200, 300, 50);

//		System.out.println("key : " + encr.key);
			key=ende.key.toString();
			t_str=ende.str.toString();
			a_str=ende.decryption.toString();
		
		R_amhokey.setText("암호키 : " + key);
		R_text.setText("평문 : " + a_str);
		R_amtext.setText("암호문 : " + t_str);

		add(R_amhokey);
		add(R_text);
		add(R_amtext);

	}
}
