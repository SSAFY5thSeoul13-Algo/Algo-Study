package week15.BOJ_1934_S5_최소공배수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1934_S5_최소공배수 {
	
/*	최소공배수는 두 자연수의 공통된 배수 중 가장 작은 수
	최소공배수 = 두 자연수의 곱 / 최대공약수
	
	유클리드 호제법 :
	2개의 자연수 a,b(a>b)에 대해 a를 b로 나눈 나머지를 r이라고 하면 
	a와 b의 최대공약수는 b와 r의 최대공약수와 같다
	
	이 성질에 따라 나머지를 구하는 과정을 반복하여
	나머지가 0이 되었을 때 나누는 수가 a와 b의 최대공약수이다.
	*/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());

			int d = gcd(Math.max(n1, n2),Math.min(n1, n2)); //최대공약수
			int ans = n1*n2/d; //최소공배수
			
			System.out.println(ans);
		}
		
	}

	private static int gcd(int a, int b) {
		while(true) {
			if(a%b==0) break;
			
			int tmp = a%b;
			a = b;
			b = tmp;
		}
		return b;
	}

}
