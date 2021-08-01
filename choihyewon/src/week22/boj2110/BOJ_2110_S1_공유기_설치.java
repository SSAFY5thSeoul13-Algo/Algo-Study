package week22.boj2110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_S1_공유기_설치 {
	static int N,C;
	static int[] arr;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int left = 1;
		int right = arr[N-1] - arr[0];
		int result = 0;
		
		while(left<=right) {
			int mid = (left+right) / 2;
			int prev = arr[0];
			int cnt = 1;
			for(int i=1; i<N; i++) {
				if(arr[i]-prev>=mid) {
					cnt++;
					prev = arr[i];
				}
			}
			if(cnt>=C) {
				result = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
			
		}
		
		System.out.println(result);

	}

}
