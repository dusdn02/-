package jongboboan;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class amho extends JFrame{
	public amho amho;
	public encryp encr;
	public Decry decr;
	public Result result = new Result();
	Panel p = new Panel();
	JButton am = new JButton("암호화");
	JButton bok = new JButton("복호화");
	
	char alphabetBoard[][] = new char[5][5];
	boolean oddFlag = false; //글자수 출력
	String zCheck ="";
	
	//액션리스너로 패널 이동
	public void change(String panelName) {
		encr=new encryp();
		decr = new Decry();
		if (panelName.equals("암호화")) {
			getContentPane().removeAll();
			getContentPane().add(encr);
			revalidate();
			repaint();
		} else if (panelName.equals("복호화")) {
			getContentPane().removeAll();
			getContentPane().add(decr);
			decr.revalidate();
			decr.repaint();
		}else if(panelName.equals("결과")) {
			getContentPane().removeAll();
			getContentPane().add(result);
			result.revalidate();
			result.repaint();
		}
	}
	
	
	
	public amho() {
		p.setLayout(null);
		
		am.setBounds(25, 100,200,150);
		bok.setBounds(300, 100,200,150);
		
		am.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//암호화 프레임 열기
				setTitle("암호화");
				change("암호화");
			}
		});
		bok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//복호화 프레임 열기
				setTitle("복화화");
				change("복호화");
			}
		});
		
		p.add(am);
		p.add(bok);
		
		add(p);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 400);
	    setVisible(true);
	}

	
	public static void main(String[] args) {
		
		new amho();
		
//		Scanner sc = new Scanner(System.in);
//		System.out.print("암호화할 문자열을 입력하세요 : ");
//		String str =  sc.nextLine();	
		
		//스트링으로 입력 받기
//		System.out.print("암호화에 쓰일 키를 입력하세요 : ");
//		String key = sc.nextLine();	
		
		
		//스트링을 char로 바꾸기, 중복제거
//		setBoard(key);
						
		
	}
	
	 
	

}

