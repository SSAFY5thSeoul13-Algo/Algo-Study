package week28.BOJ_2661_G4_좋은수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2661_G4_좋은수열 {
	static int N;
	static List<String> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		makeNum("", 0, 0);
	}
	
	static void makeNum(String num, int depth, int prevNum) {
		if(depth == N) {
			System.out.println(num);
			System.exit(0);
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			if(prevNum == i)	continue;
			
			String nNum = num + Integer.toString(i);
			
			
			if(checkIsGoodNum(nNum)) {
				makeNum(nNum, depth+1, i);
			}
		}
	}
	
	static boolean checkIsGoodNum(String num) {
		if("".equals(num))
			return true;
		
		for (int i = 1; i <= num.length()/2; i++) {
			String num1 = num.substring(num.length()-i);
			String num2 = num.substring(num.length()-i*2, num.length()-i);
			
			if(num1.equals(num2)) {
				return false;
			}
		}
		
		return true;
	}
}
