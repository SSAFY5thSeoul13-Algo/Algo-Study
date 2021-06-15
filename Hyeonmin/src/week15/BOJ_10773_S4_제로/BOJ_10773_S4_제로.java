package week15.BOJ_10773_S4_제로;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10773_S4_제로 {
	static int K, sum;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		//부른 숫자를 저장할 배열
		arr = new int[K];
		
		//숫자를 저장해야될 배열의 인덱스
		int idx = 0;
		
		for (int i = 0; i < K; i++) {
			int num = Integer.parseInt(br.readLine());
			
			//0을 부르면 마지막 저장 숫자를 0으로 변경
			if(num == 0) {
				arr[idx-1] = 0;
				idx--;
			}
			//숫자 저장
			else {
				arr[idx++] = num;
			}
		}
		
		//모든 수를 더함
		for (int i = 0; i < K; i++) {
			sum +=arr[i];
		}
		
		System.out.println(sum);
	}
}
