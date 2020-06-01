package jongboboan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class encryp extends JPanel{
	amho amho;
	Result res;
	JButton result = new JButton("결과");
	JLabel amhokey = new JLabel("암호키 : ");
	JLabel atext = new JLabel("문장 : ");
	JTextField T_amhokey = new JTextField();
	JTextField T_atext = new JTextField();
	public String str = "";
	public String key="";
	public String encryption="";
	
	public encryp(amho amho){
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
		
		
		
//		System.out.println(key);
		result.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				key = T_amhokey.getText();//암호키 받기
				str = T_atext.getText();//평문 받기
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
				amho.change("결과");
//				res = new Result(key, str, encryption);
//				am_Result(key, str, encryption);
				System.out.println(key+"***"+str+"***"+encryption);
			}
		});

		
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
		String tmp_key = "";					// 중복된 문자가 제거된 문자열을 저장할 문자열.
		int chk=0;				
		
		key += "abcdefghijklmnopqrstuvwxyz"; 	// 키에 모든 알파벳을 추가.

		
		// 중복처리
		for(int i=0; i<key.length(); i++) {
			for(int j=0; j<tmp_key.length(); j++) {
				if(key.charAt(i)==tmp_key.charAt(j)) {
					chk=1;
					break;
				}
			}
			if(chk==0)
				tmp_key += key.charAt(i);
			chk=0;
			
		}
//		System.out.println("key확인 : "+tmp_key);
		//암호판에 넣기
		int cnt = 0;	
		for( int i = 0 ; i < amho.alphabetBoard.length ; i++ ){
			for( int j = 0; j <amho.alphabetBoard[i].length ; j++ ){
				amho.alphabetBoard[i][j] = tmp_key.charAt(cnt++);
			}
		}		
		//암호판 출력
		for( int i = 0 ; i < amho.alphabetBoard.length ; i++ ){
			for( int j = 0; j <amho.alphabetBoard[i].length ; j++ ){
				System.out.print(amho.alphabetBoard[i][j]+"-");
			}
			System.out.println();
		}		
	}
}
