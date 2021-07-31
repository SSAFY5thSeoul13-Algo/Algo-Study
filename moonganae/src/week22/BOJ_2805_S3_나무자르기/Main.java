package week22.BOJ_2805_S3_나무자르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 나무 수, 상근이가 집으로 가져갈 나무 길이
	static int N, M;
	// 나무 높이 배열
	static int[] h;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		h = new int[N];
		
		st = new StringTokenizer(br.readLine());
		int max = -1;
		int answer=-1;
	
		// 가장 높은 나무 찾기
		for(int i=0; i<N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max,  h[i]);
		}
	
		// 절단기의 높이는 0 ~ max까지
		int left = 0;
		int right = max;
		
		while(left <= right) {
			int mid = (left+right) / 2;
			
			long cnt = 0L;
			
			for(int height : h) {
				if(height - mid > 0) {
					cnt += height - mid;
				}
			}
			// 필요한 나무 M이 충족되면
			if(cnt >= M) {
				// 높이를 늘이자
				left = mid +1;
				answer = mid;
			}
			// 나무가 부족하면
			else {
				// 높이를 낮추자
				right = mid - 1;
			}
		}

		
		System.out.println(answer);
	}

}
