package week22.BOJ_1654_S3_랜선자르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654_S3_랜선자르기 {
	static int K, N;
	static int[] arr;
	static long result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[K];
		
		//이분 탐색범위의 오른쪽 끝 값
		long right = 0;
		
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			right = Math.max(arr[i], right);
		}
		
		//이분 탐색범위의 왼쪽 끝 값
		long left = 1;
		
		//탐색
		while(left <= right) {
			//범위의 중간 값. 자르게 될 랜선의 길이
			long middle = (left+right)/2;
			
			boolean isEnough = calcLines(middle);
			
			//N개 이상의 랜선을 만들 수 있는 경우
			if(isEnough) {
				result = Math.max(result, middle);
				left = middle+1;
			}
			else {
				right = middle-1;
			}
		}
		
		System.out.println(result);
	}
	
	static boolean calcLines(long middle) {
		int count = 0;
		
		for (int i = 0; i < K; i++) {
			long num = arr[i]/middle;
			
			count += num;
		}
		
		//N개 이상의 랜선을 만드는 경우
		if(count >= N)
			return true;
		
		return false;
	}
}
