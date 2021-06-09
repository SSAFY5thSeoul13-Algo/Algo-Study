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
			
			//두 수 중에 더 작은 것을 num1에 저장
			if(num1 > num2) {
				int temp = num1;
				num1 = num2;
				num2 = temp;
			}
			
			//두 수의 곱 
			int multi = num1 * num2;
			
			//두 수의 최대 공약수
			int factor = 0;
			
			//유클레드 호제법
			while(true) {
				int num = num2%num1;
				
				//최대 공약수를 구했으면 저장하고 종료
				if(num == 0) {
					factor = num1;
					break;
				}
				
				//다음 진행
				num2 = num1;
				num1 = num;
			}
			
			//최대 공배수는 두 수의 곱에서 최대 공약수를 나눈 값
			System.out.println(multi / factor);
		}
	}
}
