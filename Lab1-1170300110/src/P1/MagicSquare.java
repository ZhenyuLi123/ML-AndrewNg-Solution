package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MagicSquare {

	/**
	 * �ж��Ƿ�Ϊ�÷�
	 * @param fileName
	 * @return ����Ƿ���true
	 */
	public static boolean isLegalMagicSquare(String fileName) {

		// ���ļ�
		String path = "src" + File.separator + "P1" + File.separator + "txt";

		File f = new File(path, fileName);
		//System.out.println(f.getPath());

		ArrayList<String> arrayList = new ArrayList<>();

		try {

			InputStreamReader input = new InputStreamReader(new FileInputStream(f));
			BufferedReader bf = new BufferedReader(input);
			String str;
			while ((str = bf.readLine()) != null) {
				arrayList.add(str);
			}
			bf.close();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int length = arrayList.size();
		int width = arrayList.get(0).split("\t").length;

		// �����Ƿ��Ƿ���
		for (int i = 1; i < length; i++) {
			int widthNext = arrayList.get(i).split("\t").length;
			if (widthNext != width) {
				return false;
			}
		}

		/// �����Ƿ��и��� �� С��
		for (int i = 0; i < length; i++) {
			if (arrayList.get(i).contains("-") || arrayList.get(i).contains(".")) {
				return false;
			}
		}

		/// Ӧ�ò���һ���Ƿ���\t�ָ���
		for (int i = 0; i < length; i++) {
			if (arrayList.get(i).contains(" ")) {
				return false;
			}
		}

		/// ��ʼת��һ����ά������
		int[][] arr = new int[length][length];
		String[] arrayString = new String[length + 1];

		for (int i = 0; i < length; i++) {
			arrayString = arrayList.get(i).split("\t");
			for (int j = 0; j < length; j++) {
				// System.out.println(arrayString[j]);
				arr[i][j] = Integer.valueOf(arrayString[j]);
			}
		}

		/// �����Ƿ�ת���ɹ�
//		for(int i = 0; i < length; i++) {
//			for(int j = 0; j < length; j++) {
//				System.out.println(arr[i][j]);
//			}
//		}
//		

		/// ��һ�����
		int sumFlag = 0;
		for (int i = 0; i < length; i++) {
			sumFlag += arr[0][i];
		}

		/// �����
		int sum = 0;
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < length; j++) {
				sum += arr[i][j];
			}
			if (sum != sumFlag) {
				return false;
			}
			sum = 0;
		}

		/// �����
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				sum += arr[j][i];
			}
			if (sum != sumFlag) {
				return false;
			}
			sum = 0;
		}

		/// �����Խ������
		for (int i = 0; i < length; i++) {
			sum += arr[i][i];
		}
		if (sum != sumFlag) {
			return false;
		}
		sum = 0;

		for (int i = 0; i < length; i++) {
			sum += arr[i][length - 1 - i];
		}
		if (sum != sumFlag) {
			return false;
		}

		return true;
	}

	/**
	 * ����÷�����
	 * 
	 * @param n
	 * @return ���n�ǷǷ����뷵��false ���򷵻�true
	 */
	public static boolean generateMagicSquare(int n) {

		/// ������
		if (n % 2 == 0) {
			System.out.println("Input Error");
			return false;
		}

		/// С����
		if (n < 0) {
			System.out.println("Input Error");
			return false;
		}

		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;

		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				/// ÿ������n�����֣���һ�����ֳ�����ǰһ�����ֵ��·�����row++���ɡ�
				row++;
			else {
				/// �ܽᣬ���Էŵ����Ϸ������Խ�磬�������ȥ��
				if (row == 0)
					/// �������Խ���ˣ���ô��һ�����ֳ��������һ�С�
					row = n - 1;
				else
					/// ÿ�γ��Էŵ���ǰ���ֵ���һ�С�
					row--;
				if (col == (n - 1))
					/// �������Խ���ˣ���ô��һ�����ֳ����ڵ�һ�С�
					col = 0;
				else
					/// ÿ�γ��Էŵ���ǰ���ֵ���һ�С�
					col++;
			}
		}

		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();
		}
		
		///�ļ��������
		String path = "src" + File.separator + "P1" + File.separator + "txt" + File.separator + "6.txt";
		try {
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path))));
			
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					out.print(magic[i][j]);
					out.print("\t");
				}
				out.println();
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isLegalMagicSquare("1.txt"));
		System.out.println(isLegalMagicSquare("2.txt"));
		System.out.println(isLegalMagicSquare("3.txt"));
		System.out.println(isLegalMagicSquare("4.txt"));
		System.out.println(isLegalMagicSquare("5.txt"));
		if(generateMagicSquare(7)) {
			///�������ɹ����ж��Ƿ�Ϊ�÷���
			System.out.println(isLegalMagicSquare("6.txt"));
		}
	}
}
