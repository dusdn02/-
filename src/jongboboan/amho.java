package jongboboan;

import java.util.Scanner;

public class amho {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputS = new String();
		char input[] = new char[25];
		char temp[] = new char[25];
		//��Ʈ������ �Է� �ޱ�
		inputS = sc.next();
		
		for(int i=0; i<inputS.length(); i++) {
			temp[i]=inputS.charAt(i);
		}
		//��Ʈ���� char�� �ٲٱ�, �ߺ�����
		for(int i=0; i<inputS.length(); i++) {
			for(int j=1;j<inputS.length();j++) {
				if(temp[i] == inputS.charAt(j)) {
					continue;
				}
				System.out.print("input[i]:"+input[i]+"  inputS.charAt(j):"+inputS.charAt(j));
				System.out.println();
				input[i]=inputS.charAt(j);
			}
		}
		for(int i=0; i<input.length; i++) {
			System.out.print(input[i]);
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
