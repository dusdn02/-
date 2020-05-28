package jongboboan;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class amho extends JFrame{
	
	
	public static char alphabetBoard[][] = new char[5][5];
	
	public amho() {
		setLayout(null);
		Panel p = new Panel();
		
		JButton am = new JButton("��ȣȭ");
		JButton bok = new JButton("��ȣȭ");
		am.setBounds(25, 100,200,150);
		bok.setBounds(300, 100,200,150);
		
		am.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȣȭ �г�?������ ����
			}
		});
		bok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȣȭ �г�?������ ����
			}
		});
		
		add(am);
		add(bok);
		
		add(p);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 400);
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new amho();
		
		Scanner sc = new Scanner(System.in);
//		System.out.print("��ȣȭ�� ���ڿ��� �Է��ϼ��� : ");
//		String str =  sc.nextLine();	
		
		//��Ʈ������ �Է� �ޱ�
		System.out.print("��ȣȭ�� ���� Ű�� �Է��ϼ��� : ");
		String key = sc.nextLine();	
		
		
		//��Ʈ���� char�� �ٲٱ�, �ߺ�����
		setBoard(key);
						
		
	}
	
	private static void setBoard(String key) {
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
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}		
		//�迭�� ����
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				System.out.print(alphabetBoard[i][j]+"-");
			}
			System.out.println();
		}		
						
		
	}
	

}

