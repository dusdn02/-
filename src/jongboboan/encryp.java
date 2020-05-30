package jongboboan;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class encryp extends JPanel{
	amho amho = new amho();
	JButton result = new JButton("결과");
	JLabel amhokey = new JLabel("암호키 : ");
	JLabel atext = new JLabel("문장 : ");
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
			if(str.charAt(i)==' ') //공백제거
			{
				str = str.substring(0,i)+str.substring(i+1,str.length());
				blankCheck+=10;
			}
			else
			{
				blankCheck+=0;
			}
			if(str.charAt(i)=='z') //z를 q로 바꿔줘서 처리함.
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
		
		
		
		for( int i = 0 ; i < str.length() ; i+=2 ) // arraylist 세팅
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			try{
				if( str.charAt(i) == str.charAt(i+1)) //글이 반복되면 x추가
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
			for( int j = 0 ; j < amho.alphabetBoard.length ; j++ ) //쌍자암호의 각각 위치체크
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
			
			
			if(x1==x2) //행이 같은경우
			{
				tmpArr[0] = amho.alphabetBoard[x1][(y1+1)%5];
				tmpArr[1] = amho.alphabetBoard[x2][(y2+1)%5];
			}
			else if(y1==y2) //열이 같은 경우
			{
				tmpArr[0] = amho.alphabetBoard[(x1+1)%5][y1];
				tmpArr[1] = amho.alphabetBoard[(x2+1)%5][y2];
			} 
			else //행, 열 모두 다른경우
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
		String keyForSet = "";					// 중복된 문자가 제거된 문자열을 저장할 문자열.
		boolean duplicationFlag = false;		// 문자 중복을 체크하기 위한 flag 변수.
		int keyLengthCount = 0;					// alphabetBoard에 keyForSet을 넣기 위한 count변수.
		
		key += "abcdefghijklmnopqrstuvwxyz"; 	// 키에 모든 알파벳을 추가.

		
		// 중복처리
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
		//배열에 대입
		for( int i = 0 ; i < amho.alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <amho.alphabetBoard[i].length ; j++ )
			{
				amho.alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}		
		//배열에 대입
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
