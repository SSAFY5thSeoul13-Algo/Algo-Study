package week15.boj1934;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최대공배수_1934 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// 최대공배수 구하는 법 : 두 수의 곱 / 최대공약수 
			// 최대공약수는 유클리드 호제법을 이용하여 구한다.
			int gcd = GCD(A,B);
			
			int lcm = (A*B)/gcd;
			
			System.out.println(lcm);
		}

	}

	private static int GCD(int a, int b) {
		if(b==0) {
			return a;
		}
		
		return GCD(b, a%b);
	}

}
