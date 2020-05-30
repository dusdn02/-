package jongboboan;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class encryp extends JPanel{
	amho amho = new amho();
	JButton result = new JButton("���");
	JLabel amhokey = new JLabel("��ȣŰ : ");
	JLabel atext = new JLabel("���� : ");
	JTextField T_amhokey = new JTextField();
	JTextField T_atext = new JTextField();
	
	public encryp(){
		String encryption;
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
		
		String key = T_amhokey.getText();
		String str = T_atext.getText();

		String blankCheck="";
		
		setBoard(key);
		
		for( int i = 0 ; i < str.length() ; i++ ) 
		{
			if(str.charAt(i)==' ') //��������
			{
				str = str.substring(0,i)+str.substring(i+1,str.length());
				blankCheck+=10;
			}
			else
			{
				blankCheck+=0;
			}
			if(str.charAt(i)=='z') //z�� q�� �ٲ��༭ ó����.
			{
				str = str.substring(0,i)+'q'+str.substring(i+1,str.length());
				amho.zCheck+=1;
			}
			else 
			{
				amho.zCheck+=0;
			}
		}
		
		encryption = strEncryption(key, str);
	}
	
	String strEncryption(String key, String str){
		ArrayList<char[]> playFair = new ArrayList<char[]>();
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>();
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0;
		String encStr ="";
		
		
		
		for( int i = 0 ; i < str.length() ; i+=2 ) // arraylist ����
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			try{
				if( str.charAt(i) == str.charAt(i+1)) //���� �ݺ��Ǹ� x�߰�
				{
					tmpArr[1] = 'x';
					i--;
				}else{
					tmpArr[1] = str.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e)
			{
				tmpArr[1] = 'x'; 
				amho.oddFlag = true;
			}
			playFair.add(tmpArr);
		}
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{
			System.out.print(playFair.get(i)[0]+""+playFair.get(i)[1]+" ");
		}
		System.out.println();
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < amho.alphabetBoard.length ; j++ ) //���ھ�ȣ�� ���� ��ġüũ
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
			
			
			if(x1==x2) //���� �������
			{
				tmpArr[0] = amho.alphabetBoard[x1][(y1+1)%5];
				tmpArr[1] = amho.alphabetBoard[x2][(y2+1)%5];
			}
			else if(y1==y2) //���� ���� ���
			{
				tmpArr[0] = amho.alphabetBoard[(x1+1)%5][y1];
				tmpArr[1] = amho.alphabetBoard[(x2+1)%5][y2];
			} 
			else //��, �� ��� �ٸ����
			{
				tmpArr[0] = amho.alphabetBoard[x2][y1];
				tmpArr[1] = amho.alphabetBoard[x1][y2];
			}
			
			encPlayFair.add(tmpArr);
			
		}
		
		for(int i = 0 ; i < encPlayFair.size() ; i++)
		{
			encStr += encPlayFair.get(i)[0]+""+encPlayFair.get(i)[1]+" ";
		}
		
		
		return encStr;
	}
	
	void setBoard(String key) {
		String keyForSet = "";					// �ߺ��� ���ڰ� ���ŵ� ���ڿ��� ������ ���ڿ�.
		boolean duplicationFlag = false;		// ���� �ߺ��� üũ�ϱ� ���� flag ����.
		int keyLengthCount = 0;					// alphabetBoard�� keyForSet�� �ֱ� ���� count����.
		
		key += "abcdefghijklmnopqrstuvwxyz"; 	// Ű�� ��� ���ĺ��� �߰�.

		
		// �ߺ�ó��
		for( int i = 0 ; i < key.length() ; i++ ) 
		{
			for( int j = 0 ; j < keyForSet.length() ; j++ )
			{
				if(key.charAt(i)==keyForSet.charAt(j))
				{
					duplicationFlag = true;
					break;
				}
			}
			if(!(duplicationFlag)) keyForSet += key.charAt(i);
			duplicationFlag = false;
		}
		//�迭�� ����
		for( int i = 0 ; i < amho.alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <amho.alphabetBoard[i].length ; j++ )
			{
				amho.alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}		
		//�迭�� ����
		for( int i = 0 ; i < amho.alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <amho.alphabetBoard[i].length ; j++ )
			{
				System.out.print(amho.alphabetBoard[i][j]+"-");
			}
			System.out.println();
		}		
						
		
	}
}
