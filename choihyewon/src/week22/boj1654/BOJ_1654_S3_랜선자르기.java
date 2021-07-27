package week22.boj1654;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1654_S3_랜선자르기 {
	static int K,N;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		
		long left = 1;
		long right = arr[K-1];
		
		while(left<=right) {
			long mid = (left + right)/2;
			// 자른 랜선의 개수 
			int cnt = 0;
			
			for(int i=0; i<K; i++) {
				cnt += arr[i]/mid;
			}
			
			if(cnt>=N) {
				left = mid + 1;
			}else if(cnt<N){
				right = mid - 1;
			}
			
		}
		
		System.out.println(right);

	}

}
