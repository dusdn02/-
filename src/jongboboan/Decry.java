package jongboboan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Decry extends JPanel {
	amho amho;
	encryp encr;
	Result res;
	JButton result = new JButton("���");
	JLabel amhokey = new JLabel("��ȣŰ : ");
	JLabel atext = new JLabel("���� : ");
	JTextField T_amhokey = new JTextField();
	JTextField T_atext = new JTextField();
	public String str="";
	public String key="";
	public String decryption="";

	public Decry(amho amho) {
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

		result.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				key = T_amhokey.getText();
				str = T_atext.getText();
				
				setBoard(key);

				str = str.replaceAll(" ", "");// str ���� ����
				
				decryption = strDecryption(key, str);
				

				System.out.println("��ȣȭ�� ���ڿ� : " + decryption);
				amho.change("���");

			}
		});
	}

	String strDecryption(String key, String str) {
		ArrayList<char[]> playFair = new ArrayList<char[]>(); // �ٲٱ� �� ���ھ�ȣ�� ������ ��
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); // �ٲ� ���� ���ھ�ȣ ������ �� 
		String decStr = "";

		for (int i = 0; i < str.length(); i += 2) {
			char[] tmp = new char[2];
			tmp[0] = str.charAt(i);
			tmp[1] = str.charAt(i + 1);
			playFair.add(tmp);
		}
		
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
			}else if (x1 == x2) { // ���� ���� ��� ���� �ٷ� �Ʒ��� ����
				tmp[0] = amho.alphabetBoard[x1][(y1 + 4) % 5];
				tmp[1] = amho.alphabetBoard[x2][(y2 + 4) % 5];
			} else if (y1 == y2) { // ���� ���� ��� ���� �ٷ� �� �� ����
				tmp[0] = amho.alphabetBoard[(x1 + 4) % 5][y1];
				tmp[1] = amho.alphabetBoard[(x2 + 4) % 5][y2];
			} else { // ��, �� �ٸ���� ���� �밢���� �ִ� ��.
				tmp[0] = amho.alphabetBoard[x2][y1];
				tmp[1] = amho.alphabetBoard[x1][y2];
			}

			decPlayFair.add(tmp);
		}
		
		
		for (int i = 0; i < decPlayFair.size(); i++) // �ߺ� ���ڿ� ��������
		{
			if (i != decPlayFair.size() - 1 && decPlayFair.get(i)[1] == 'x' && decPlayFair.get(i)[0] == decPlayFair.get(i + 1)[0]) {
				decStr += decPlayFair.get(i)[0];
			} else {
				decStr += decPlayFair.get(i)[0] + "" + decPlayFair.get(i)[1];
			}
		}
		

		if (decStr.length() % 2 == 1 )//&& decStr.substring(decStr.length())=="x"
			decStr = decStr.substring(0, decStr.length() - 1);

		System.out.println(decStr);
		
		return decStr;
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
