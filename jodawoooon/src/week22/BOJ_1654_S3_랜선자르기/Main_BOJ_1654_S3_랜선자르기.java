package week22.BOJ_1654_S3_랜선자르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1654_S3_랜선자르기 {
	static int K, N;
	static int[] arr;
	static long left, right, ans;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		left = 1; //left=1
		
		arr = new int[K];
		for (int k = 0; k < K; k++) {
			arr[k] = Integer.parseInt(br.readLine());
			right = Math.max(right, arr[k]);//right=가장 긴  전선 길이
		}

		
		while(left <= right) {
			long mid = (left+right)/2; //중간 값
			long cnt = 0; //만들 수 있는 랜선의 개수의 합
			
			for (int k = 0; k < K; k++) { 
				cnt += arr[k] / mid; //arr[k]로 만들 수 있는 랜선 갯수
			}
			
			if(cnt >= N) { //랜선의 개수가 N보다 크거나 같으면 
				ans = Math.max(ans, mid);
				left = mid + 1;
			}
			else right = mid - 1;
		}
		
		
		System.out.println(ans);
		
	}
}
