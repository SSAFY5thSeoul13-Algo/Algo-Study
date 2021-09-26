package week28.boj2661;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2661_G4_좋은수열 {
	static int N;
	static boolean isEnd = false;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = "";
		
		backTracking(str);

	}
	private static void backTracking(String str) {
		if(isEnd) {
			return;
		}
		
		if(str.length()==N) {
			System.out.println(str);
			// 종료되었음을 의미 
			isEnd = true;
			return;
		}
		
		// 숫자는 1,2,3 으로만 이루어져 있으므로 1부터 3까지 반복 
		for(int i=1; i<=3; i++) {
			if(isGood(str+i)) {
				backTracking(str+i);
			}
		}
		
	}
	// 좋은 수열인지 아닌지 판별 
	private static boolean isGood(String str) {
		// 수열의 모든 경우를 탐색
		for(int i=1; i<=str.length()/2; i++) {
			String str1 = str.substring(str.length()-i-i, str.length()-i);
			String str2 = str.substring(str.length()-i,str.length());
			// 만약 나쁜 수열이라면 false return 
			if(str1.equals(str2)) {
				return false;
			}
		}
		
		return true;
		
	}


}
