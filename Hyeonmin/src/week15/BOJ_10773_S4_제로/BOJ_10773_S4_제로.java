package week15.BOJ_10773_S4_제로;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10773_S4_제로 {
	static int K, sum;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		arr = new int[K];
		
		int idx = 0;
		
		for (int i = 0; i < K; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num == 0) {
				arr[idx-1] = 0;
				idx--;
			}
			else {
				arr[idx++] = num;
			}
		}
		
		for (int i = 0; i < K; i++) {
			sum +=arr[i];
		}
		
		System.out.println(sum);
	}
}
