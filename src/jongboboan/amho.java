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
	JButton am = new JButton("��ȣȭ");
	JButton bok = new JButton("��ȣȭ");
	
	char alphabetBoard[][] = new char[5][5];
	boolean oddFlag = false; //���ڼ� ���
	String zCheck ="";
	
	//�׼Ǹ����ʷ� �г� �̵�
	public void change(String panelName) {
		encr=new encryp();
		decr = new Decry();
		if (panelName.equals("��ȣȭ")) {
			getContentPane().removeAll();
			getContentPane().add(encr);
			revalidate();
			repaint();
		} else if (panelName.equals("��ȣȭ")) {
			getContentPane().removeAll();
			getContentPane().add(decr);
			decr.revalidate();
			decr.repaint();
		}else if(panelName.equals("���")) {
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
				//��ȣȭ ������ ����
				setTitle("��ȣȭ");
				change("��ȣȭ");
			}
		});
		bok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȣȭ ������ ����
				setTitle("��ȭȭ");
				change("��ȣȭ");
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
//		System.out.print("��ȣȭ�� ���ڿ��� �Է��ϼ��� : ");
//		String str =  sc.nextLine();	
		
		//��Ʈ������ �Է� �ޱ�
//		System.out.print("��ȣȭ�� ���� Ű�� �Է��ϼ��� : ");
//		String key = sc.nextLine();	
		
		
		//��Ʈ���� char�� �ٲٱ�, �ߺ�����
//		setBoard(key);
						
		
	}
	
	 
	

}

