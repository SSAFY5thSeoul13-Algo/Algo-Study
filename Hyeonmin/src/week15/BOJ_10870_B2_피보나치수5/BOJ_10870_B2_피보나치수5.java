package week15.BOJ_10870_B2_피보나치수5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10870_B2_피보나치수5 {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		//N이 0인경우 0을 출력하고 그대로 종료
		if(N==0) {
			System.out.println(0);
			return;
		}
		
		arr = new int[N+1];
		
		//1번째 숫자는 1
		arr[1] = 1;
		
		//N까지 fn = f(n-1) + f(n-2) 값을 저장
		for (int i = 2; i <= N; i++) {
			arr[i] = arr[i-1] + arr[i-2];
		}
		
		//N번째 수를 출력
		System.out.println(arr[N]);
	}
}
