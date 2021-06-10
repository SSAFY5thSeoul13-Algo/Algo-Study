package week15.BOJ_1934_S5_최소공배수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int max = Math.max(a,b);
			int min = Math.min(a,b);
			
			int gcd = GCD(min, max%min);
			
			System.out.println(max*min / gcd);
		}

	}
	
	static int GCD(int A, int B) {
		if(B == 0) return A;
		return GCD(B, A%B);
	}

}
