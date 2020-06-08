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
	JButton result = new JButton("결과");
	JLabel amhokey = new JLabel("암호키 : ");
	JLabel atext = new JLabel("문장 : ");
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

				str = str.replaceAll(" ", "");// str 공백 제거
				
				decryption = strDecryption(key, str);
				

				System.out.println("복호화된 문자열 : " + decryption);
				amho.change("결과");

			}
		});
	}

	String strDecryption(String key, String str) {
		ArrayList<char[]> playFair = new ArrayList<char[]>(); // 바꾸기 전 쌍자암호를 저장할 곳
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); // 바꾼 후의 쌍자암호 저장할 곳 
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
				System.out.println("qz치환");
			}else if (x1 == x2) { // 행이 같은 경우 각각 바로 아래열 대입
				tmp[0] = amho.alphabetBoard[x1][(y1 + 4) % 5];
				tmp[1] = amho.alphabetBoard[x2][(y2 + 4) % 5];
			} else if (y1 == y2) { // 열이 같은 경우 각각 바로 옆 열 대입
				tmp[0] = amho.alphabetBoard[(x1 + 4) % 5][y1];
				tmp[1] = amho.alphabetBoard[(x2 + 4) % 5][y2];
			} else { // 행, 열 다른경우 각자 대각선에 있는 곳.
				tmp[0] = amho.alphabetBoard[x2][y1];
				tmp[1] = amho.alphabetBoard[x1][y2];
			}

			decPlayFair.add(tmp);
		}
		
		
		for (int i = 0; i < decPlayFair.size(); i++) // 중복 문자열 돌려놓음
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
		String tmp_key = ""; // 중복된 문자가 제거된 문자열을 저장할 문자열.
		int chk = 0;

		key += "abcdefghijklmnopqrstuvwxyz"; // 키에 모든 알파벳을 추가.

		// 중복처리
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
//		System.out.println("key확인 : "+tmp_key);
		// 암호판에 넣기
		int cnt = 0;
		for (int i = 0; i < amho.alphabetBoard.length; i++) {
			for (int j = 0; j < amho.alphabetBoard[i].length; j++) {
				amho.alphabetBoard[i][j] = tmp_key.charAt(cnt++);
			}
		}
		// 암호판 출력
		for (int i = 0; i < amho.alphabetBoard.length; i++) {
			for (int j = 0; j < amho.alphabetBoard[i].length; j++) {
				System.out.print(amho.alphabetBoard[i][j] + "-");
			}
			System.out.println();
		}
	}

}
