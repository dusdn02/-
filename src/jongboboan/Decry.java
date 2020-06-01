package jongboboan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Decry extends JPanel{
	amho amho;
	encryp encr;
	Result res;
	JButton result = new JButton("���");
	JLabel amhokey = new JLabel("��ȣŰ : ");
	JLabel atext = new JLabel("���� : ");
	JTextField T_amhokey = new JTextField();
	JTextField T_atext = new JTextField();
	public String str;
	public String key;
	public String decryption;
	
	public Decry(amho amho) {
		this.amho = amho;
		setLayout(null);
		
		result.setBounds(200, 250,100,80);
		amhokey.setBounds(100, 100, 50,50);
		T_amhokey.setBounds(150, 100, 280, 40);
		atext.setBounds(100, 150, 50,50);
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
				String blankCheck="";
				
//				encr.setBoard(key);
				
				for( int i = 0 ; i < amho.encr.encryption.length() ; i++ ) 
				{
					if(amho.encr.encryption.charAt(i)==' ') //��������
						amho.encr.encryption = amho.encr.encryption.substring(0,i)+amho.encr.encryption.substring(i+1,amho.encr.encryption.length());
				}
				
				decryption = strDecryption(key, amho.encr.encryption, amho.zCheck);
				
				for( int i = 0 ; i < decryption.length() ; i++)
				{
					if(blankCheck.charAt(i)=='1')
					{
						decryption = decryption.substring(0,i)+" "+decryption.substring(i,decryption.length());
					}
				}
				
				System.out.println("��ȣȭ�� ���ڿ� : " + decryption);
				amho.change("���");
//				res = new Result(key, str, decryption);
				
			}
		});
	}
	String strDecryption(String key, String str, String zCheck) {
		ArrayList<char[]> playFair = new ArrayList<char[]>(); //�ٲٱ� �� ���ھ�ȣ�� ������ ��
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); //�ٲ� ���� ���ھ�ȣ ������ ��
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0; //���� ��ȣ �� ������ ������ ��,�� ��
		String decStr ="";

		int lengthOddFlag = 1;
		
		
		for( int i = 0 ; i < str.length() ; i+=2 )
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			tmpArr[1] = str.charAt(i+1);
			playFair.add(tmpArr);
		}
		
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < amho.alphabetBoard.length ; j++ )
			{
				for( int k = 0 ; k < amho.alphabetBoard[j].length ; k++ )
				{
					if(amho.alphabetBoard[j][k] == playFair.get(i)[0])
					{
						x1 = j;
						y1 = k;
					}
					if(amho.alphabetBoard[j][k] == playFair.get(i)[1])
					{
						x2 = j;
						y2 = k;
					}
				}
			}
			
			if(x1==x2) //���� ���� ��� ���� �ٷ� �Ʒ��� ����
			{
				tmpArr[0] = amho.alphabetBoard[x1][(y1+4)%5];
				tmpArr[1] = amho.alphabetBoard[x2][(y2+4)%5];
			}
			else if(y1==y2) //���� ���� ��� ���� �ٷ� �� �� ����
			{
				tmpArr[0] = amho.alphabetBoard[(x1+4)%5][y1];
				tmpArr[1] = amho.alphabetBoard[(x2+4)%5][y2];
			}
			else //��, �� �ٸ���� ���� �밢���� �ִ� ��.
			{
				tmpArr[0] = amho.alphabetBoard[x2][y1];
				tmpArr[1] = amho.alphabetBoard[x1][y2];
			}
			
			decPlayFair.add(tmpArr);
			
		}
		
		for(int i = 0 ; i < decPlayFair.size() ; i++) //�ߺ� ���ڿ� ��������
		{
			if(i!=decPlayFair.size()-1 && decPlayFair.get(i)[1]=='x' 
					&& decPlayFair.get(i)[0]==decPlayFair.get(i+1)[0])
			{	
				decStr += decPlayFair.get(i)[0];
			}
			else
			{
				decStr += decPlayFair.get(i)[0]+""+decPlayFair.get(i)[1];
			}
		}
		
		
		
		for(int i = 0 ; i < zCheck.length() ; i++ )//z��ġ ã�Ƽ� q�� ��������
		{
			if( zCheck.charAt(i) == '1' ) 
				decStr = decStr.substring(0,i)+'z'+decStr.substring(i+1,decStr.length());
			
		}
		
		
		
		if(amho.oddFlag) decStr = decStr.substring(0,decStr.length()-1);
		
		/*
		 //����
		for(int i = 0 ; i < decStr.length(); i++)
		{
			if(i%2==lengthOddFlag){
				decStr = decStr.substring(0, i+1)+" "+decStr.substring(i+1, decStr.length());
				i++;
				lengthOddFlag = ++lengthOddFlag %2;
			}
		}
		*/
		return decStr;
	}
	
}
