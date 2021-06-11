package week15.BOJ_10870_B2_피보나치수5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_10870_B2_피보나치수5 {

	static int N, fibo[];
	public static void main(String[] args) throws Exception {
		//0번째 = 0, 1번째, 1
		//2번째 = Fn + Fn-1 +Fn-2
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		if(N==0) {
			System.out.println(0);
			return;
		}
		if(N==1) {
			System.out.println(1);
			return;
		}
		
		//N=2 이상이면
		fibo = new int[N+1]; //피보나치수열 배열
		fibo[0] = 0;
		fibo[1] = 1;
		
		System.out.println(fibo(N));
	}
	private static int fibo(int n) {
		if(n>=2&&fibo[n]==0) {
			fibo[n] = fibo(n-1)+fibo(n-2);
		}
		return fibo[n];
	}
}
