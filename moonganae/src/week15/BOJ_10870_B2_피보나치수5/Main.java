package week15.BOJ_10870_B2_피보나치수5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+3];
		
		dp[1] = 1;
		dp[2] = 1;
		
		System.out.println(fibo(N));
	}
	
	static int fibo(int n) {
		
		if(n <= 2) return dp[n];
		
		int a = dp[n-1];
		if(a == 0) 
			a = fibo(n-1);
		
		
		int b = dp[n-2];
		if(b==0)
			b = fibo(n-2);
		
		dp[n] = a + b;
		return dp[n];
	}

}
