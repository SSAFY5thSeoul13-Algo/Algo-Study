package week15.BOJ_1292_쉽게푸는문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1292_S5_쉽게푸는문제 {
	static int start, end, sum;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		arr = new int[end+1];
		
		int num = 1;
		int count = 0;
		
		for (int i = 1; i <= end; i++) {
			arr[i] = num;
			count++;
			if(num == count) {
				num++;
				count = 0;
			}
		}
		
		for (int i = start; i <= end; i++) {
			sum += arr[i];
		}
		
		System.out.println(sum);
	}
}
