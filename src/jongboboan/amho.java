package jongboboan;

import java.util.Scanner;

public class amho {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputS = new String();
		char input[] = new char[25];
		//��Ʈ������ �Է� �ޱ�
		inputS = sc.next();
		
		//�Է¹��� ���ڿ� �ߺ�����
		
		
		//��Ʈ���� char�� �ٲٱ�
		for(int i=0; i<inputS.length(); i++) {
			input[i]=inputS.charAt(i);
		}
		
		
		//�Է¹��� ���ڸ� ��ȣ�ǿ� �־��� ��� ����� ���ϱ�
		int col = (inputS.length())/5;
		int row = (inputS.length())%5;
//		System.out.println("col:"+col+", row:"+row);
		
		char arr[][] = new char[5][5];
		char alpabet = 'A';
		
		for(int i=col; i<arr.length; i++) {
			for(int j=row; j<arr[0].length; j++) {
				arr[i][j]=input[i+j];
			}
		}
		
		for(int i=col; i<arr.length; i++) {
			for(int j=row; j<arr[0].length; j++) {
				arr[i][j]=input[i+j];
			}
		}
		
		for(int i=0; i<arr.length; i++) { 
			for(int j=0; j<arr[0].length; j++) {
				arr[i][j]=alpabet;
				alpabet++;
			}
		}
	}

}
