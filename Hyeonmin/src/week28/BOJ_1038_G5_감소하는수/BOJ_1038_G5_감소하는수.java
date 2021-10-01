package week28.BOJ_1038_G5_감소하는수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1038_G5_감소하는수 {
	static int N, count;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		calcNum(0, 0, 0);
		
		Collections.sort(list);
		
		if(N > list.size()-1)
			System.out.println(-1);
		else if(N == 1022)
			System.out.println("9876543210");
		else
			System.out.println(list.get(N));
	}
	
	static void calcNum(int num, int step, int prevNum) {
		if(step > 9) {
			return;
		}
		
		for (int i = num; i <= 9; i++) {
			int nNum = (int) (i*Math.pow(10, step)+prevNum);
			list.add(nNum);
			
			if(i != 9)
				calcNum(i+1, step+1, nNum);
		}
	}
}
