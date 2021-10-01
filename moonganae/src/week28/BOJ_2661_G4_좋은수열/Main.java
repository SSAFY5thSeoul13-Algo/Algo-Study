package week28.BOJ_2661_G4_좋은수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	/* 수열의 길이 */
	static int N;
	/* dfs 종료 플레그 */
	static boolean finish = false;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		
		/* 1로 시작하는 수열 */
		dfs(1, "1");
		

	}
	
	static void dfs(int length, String num) {
		
		if(finish) return;
		
		if(length == N) {
			System.out.println(num);
			finish = true;
			return;
		}
		
		/* 1, 2, 3순으로 적용해본다. */
		for(int i=1; i<=3; i++) {
			/* 좋은 수열인가? */
			if(isGood(num+i)) {
				/* 좋은 수열이면 넘어간다. */
				dfs(length+1, num + i);
			}
		}
	}
	
	static boolean isGood(String num) {
		
		int length = num.length();
		int limit = length / 2;
		
		/* 현재 수열의 길이의 절반까지만 좋은 수열 여부를 확인 */
		for(int i=1; i<=limit; i++) {
			int start = length - i;
			if(num.substring(start-i, length-i).equals(num.substring(start, length))) {
				return false;
			}
		}
		
		return true;
	}
	
}
