package jongboboan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class encryp extends JPanel {
	amho amho;
	Result res;
	JButton result = new JButton("���");
	JLabel amhokey = new JLabel("��ȣŰ : ");
	JLabel atext = new JLabel("���� : ");
	JTextField T_amhokey = new JTextField();
	JTextField T_atext = new JTextField();
	public String str = "";
	public String key = "";
	public String encryption = "";
	String encr="";

	
	
	public encryp(amho amho) {
		this.amho = amho;
		setLayout(null);

		result.setBounds(200, 250, 100, 80);
		amhokey.setBounds(100, 100, 50, 50);
		T_amhokey.setBounds(150, 100, 280, 40);
		atext.setBounds(100, 150, 50, 50);
		T_atext.setBounds(150, 150, 280, 40);

		add(result);
		add(amhokey);
		add(atext);
		add(T_amhokey);
		add(T_atext);

//		System.out.println(key);
		result.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				key = T_amhokey.getText();// ��ȣŰ �ޱ�
				str = T_atext.getText();// �� �ޱ�
				String blankCheck = "";

				setBoard(key);

				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == ' ') {
						str = str.substring(0, i) + str.substring(i + 1, str.length());
						blankCheck += 10;
					} else {
						blankCheck += 0;
					}
//					if (str.charAt(i) == 'z') {
//						str = str.substring(0, i) + 'q' + str.substring(i + 1, str.length());
//						amho.zCheck += 1;
//					} else {
//						amho.zCheck += 0;
//					}
				}

				encryption = strEncryption(key, str);
				amho.change("���");
//				res = new Result(key, str, encryption);
//				am_Result(key, str, encryption);
				System.out.println(key + "***" + str + "***" + encryption);
			}
		});

	}

	String strEncryption(String key, String str) {
		ArrayList<char[]> playFair = new ArrayList<char[]>();
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>();
		String enStr = "";
		

		str = str.replaceAll(" ", "");// str ���� ����
//		System.out.println(str);

		for (int i = 0; i < str.length(); i += 2) {
			char[] tmp = new char[2];

			
			tmp[0] = str.charAt(i);
			try{
				// ���� �α��ڰ� ���ٸ� �ޱ��ڸ� x��
				if( str.charAt(i) == str.charAt(i+1))
				{
					tmp[1] = 'x';
					i--;
				}else{
					tmp[1] = str.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e)// str�� Ȧ����� �������� x
			{
				tmp[1] = 'x'; 
			}

//			// ���� �α��ڰ� ���ٸ� �ޱ��ڸ� x��
//			if (tmp[0] == tmp[1]) {
//				tmp[1] = 'x';
//				i--;
//			}

			// qz��� ġȯ
//			if ((tmp[0] == 'q' && tmp[1] == 'z') || (tmp[0] == 'z' && tmp[1] == 'q')) {
//				char temp;
//				temp = tmp[0];
//				tmp[0] = tmp[1];
//				tmp[1] = temp;
//				
//				System.out.println("qzġȯ");
//			}

			// str�� Ȧ����� �������� x
//			if (str.length() % 2 == 1 && i == str.length() - 1) {
//				tmp[1] = 'x';
//			}

			playFair.add(tmp);
			
		}

//		System.out.println(enStr);

		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		for (int i = 0; i < playFair.size(); i++) {
			char[] tmp = new char[2];
			for (int j = 0; j < amho.alphabetBoard.length; j++) {
				for (int k = 0; k < amho.alphabetBoard[j].length; k++) {
					if (amho.alphabetBoard[j][k] == playFair.get(i)[0]) {
						x1 = j;
						y1 = k;
					}
					if (amho.alphabetBoard[j][k] == playFair.get(i)[1]) {
						x2 = j;
						y2 = k;
					}
				}
			}
			if((playFair.get(i)[0] == 'q' && playFair.get(i)[1] == 'z') ||
					(playFair.get(i)[0] == 'z' && playFair.get(i)[1] == 'q')){
				tmp[0]=playFair.get(i)[1];
				tmp[1]=playFair.get(i)[0];
				System.out.println("qzġȯ");
			}else if (x1 == x2) // ���� �������
			
			{
				tmp[0] = amho.alphabetBoard[x1][(y1 + 1) % 5];
				tmp[1] = amho.alphabetBoard[x2][(y2 + 1) % 5];
			} else if (y1 == y2) // ���� ���� ���
			{
				tmp[0] = amho.alphabetBoard[(x1 + 1) % 5][y1];
				tmp[1] = amho.alphabetBoard[(x2 + 1) % 5][y2];
			} else // ��, �� ��� �ٸ����
			{
				tmp[0] = amho.alphabetBoard[x2][y1];
				tmp[1] = amho.alphabetBoard[x1][y2];
			}
			
			encPlayFair.add(tmp);
		}
		for(int i = 0 ; i < encPlayFair.size() ; i++)
		{
			enStr += encPlayFair.get(i)[0]+""+encPlayFair.get(i)[1]+" ";
		}
		
		
		return enStr;

	}

	void setBoard(String key) {
		String tmp_key = ""; // �ߺ��� ���ڰ� ���ŵ� ���ڿ��� ������ ���ڿ�.
		int chk = 0;

		key += "abcdefghijklmnopqrstuvwxyz"; // Ű�� ��� ���ĺ��� �߰�.

		// �ߺ�ó��
		for (int i = 0; i < key.length(); i++) {
			for (int j = 0; j < tmp_key.length(); j++) {
				if (key.charAt(i) == tmp_key.charAt(j)) {
					chk = 1;
					break;
				}
			}
			if (chk == 0)
				tmp_key += key.charAt(i);
			chk = 0;

		}
//		System.out.println("keyȮ�� : "+tmp_key);
		// ��ȣ�ǿ� �ֱ�
		int cnt = 0;
		for (int i = 0; i < amho.alphabetBoard.length; i++) {
			for (int j = 0; j < amho.alphabetBoard[i].length; j++) {
				amho.alphabetBoard[i][j] = tmp_key.charAt(cnt++);
			}
		}
		// ��ȣ�� ���
		for (int i = 0; i < amho.alphabetBoard.length; i++) {
			for (int j = 0; j < amho.alphabetBoard[i].length; j++) {
				System.out.print(amho.alphabetBoard[i][j] + "-");
			}
			System.out.println();
		}
	}
}
