package week32.boj9663;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9663_G5_N_Queen {
	static int N,cnt;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for(int i=0; i<N; i++) {
			arr = new int[N];
			arr[0] = i;
			backTracking(1);
		}
		
		System.out.println(cnt);
		

	}
	private static void backTracking(int col) {
		if(col==N) {
			cnt++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			arr[col] = i;
			if(isPossible(col)) {
				backTracking(col+1);
			}
		}
		
		
	}
	private static boolean isPossible(int col) {
		for(int i=0; i<col; i++) {
			if(arr[col] == arr[i] || Math.abs(i-col) == Math.abs(arr[i]-arr[col])) {
				return false;
			}
		}
		return true;
	}

}
