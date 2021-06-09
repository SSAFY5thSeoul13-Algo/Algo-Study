package week15.boj10870;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 피보나치_수_5_10870 {
	static int fibo0, fibo1,result;
	static int[] fibo;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		fibo = new int[21];
		
		for(int i = 0; i <= N; i++) {
			if(i == 0) fibo[0] = 0;
			else if(i == 1) fibo[1] = 1;            
			else fibo[i] = fibo[i - 1] + fibo[i - 2];

		}
		
		System.out.println(fibo[N]);
	}
	


	

}
