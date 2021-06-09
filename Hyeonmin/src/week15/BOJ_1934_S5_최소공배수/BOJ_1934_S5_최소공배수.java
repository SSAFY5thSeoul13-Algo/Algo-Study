package week15.BOJ_1934_S5_최소공배수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1934_S5_최소공배수 {
	static int T, num1, num2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			
			//두 수의 최대 공약수
			int factor = 0;
			
			for (int i = num1; i > 0 ; i--) {
				//최대 공약수를 구하면 반복을 종료
				if(num2 % i == 0 && num1 % i == 0) {
					factor = i;
					break;
				}
			}
			
			//최대 공배수는 두수의 곱에서 최대 공약수를 나눈 값
			System.out.println(num1 * num2 / factor);
		}
	}
}
