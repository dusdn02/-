package jongboboan;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Result extends JPanel {
	amho amho;

	public Result(encryp encr) {

		JLabel R_amhokey = new JLabel();
		JLabel R_text = new JLabel();
		JLabel R_amtext = new JLabel();

		setLayout(null);
		R_amhokey.setBounds(150, 100, 300, 50);
		R_text.setBounds(150, 150, 300, 50);
		R_amtext.setBounds(150, 200, 300, 50);

//		System.out.println("key : " + encr.key);
		if(amho.ab_chk==1) {
			
		}
		R_amhokey.setText("암호키 : " + encr.key.toString());
		R_text.setText("평문 : " + encr.str.toString());
		R_amtext.setText("암호문 : " + encr.encryption.toString());

		add(R_amhokey);
		add(R_text);
		add(R_amtext);

	}
}
