package week22.BOJ_2805_S3_나무자르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2805_S3_나무자르기 {
	static int N;
	static long[] trees;
	static long left, right, ans, M; 
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //나무의 수
		M = Long.parseLong(st.nextToken()); //집으로 가져가야 되는 나무의 길이 M
		
		trees = new long[N];
		
		left = 1;
		
		st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n < N ; n++) {
			trees[n] = Integer.parseInt(st.nextToken());
			right = Math.max(right, trees[n]);
		}
		   
		//적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
		
		while(left<=right) {
			long mid = (left+right)/2;
			long sum = 0;

			for(int n = 0 ; n < N ; n++) {
				if(trees[n]>mid) {
					sum += (trees[n]-mid);
				}
			}

			if(sum>=M) {
				ans = Math.max(mid, ans);
				left = mid + 1;
			}
			else right = mid - 1;
		}
		
		System.out.println(ans);
		
	}
}
