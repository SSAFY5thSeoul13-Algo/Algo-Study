package week15.BOJ_11068_S5_회문인수;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static ArrayList numStr = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		
//		System.setIn(new FileInputStream("./input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			int num = Integer.parseInt(br.readLine());
			
			System.out.println(solution(num));
		}
	}
	
	static int solution (int num) {
		
		/* 2~64진법 확인 */
		for(int i=2; i<65; i++) {
			
			/* i진법으로 변환 */
			conversion(num, i);
			
			/* 회문인==펠린드롬인지 확인 */
			if(isPalindrome()) {
				return 1;
			}
				
		}
		
		return 0;
	}

	private static void conversion(int num, int i) {
		
		numStr.clear();
		
		while(num/i != 0) {
			numStr.add(num % i);
			num /= i;
		}
		numStr.add(num % i);
		
	}
	
	
	static boolean isPalindrome() {
		int size = numStr.size();
		
		for(int i=0; i<size/2; i++) {
			if( numStr.get(i) != numStr.get((size-1) - i) )
				return false;
		}
		return true;
	}
}
