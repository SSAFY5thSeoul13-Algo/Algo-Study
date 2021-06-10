package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10836_G4_여왕벌 {
	static int M, N;
	//왼쪽 끝 열
	static int[] left;
	//위쪽 끝 열
	static int[] top;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		left = new int[M];
		top = new int[M];
		
		//초기 크기 설정
		for (int i = 0; i < M; i++) {
			left[i] = 1;
			top[i] = 1;
		}
		
		for (int n = 0; n < N; n++) {
			
			st = new StringTokenizer(br.readLine());
			
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			//왼쪽 열 성장 반영
			for (int i = M-1; i >= 0; i--) {
				int temp = 0;
				
				if(zero > 0) {
					temp = 0;
					zero--;
				}
				else if(one > 0) {
					temp = 1;
					one--;
				}
				else if(two > 0) {
					temp = 2;
					two--;
				}
				
				left[i] += temp;
			}
			
			//맨 위 행 성장 반영
			for (int i = 1; i < M; i++) {
				int temp = 0;
				
				if(zero > 0) {
					temp = 0;
					zero--;
				}
				else if(one > 0) {
					temp = 1;
					one--;
				}
				else if(two > 0) {
					temp = 2;
					two--;
				}
				
				top[i] += temp;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			sb.append(left[i]+" ");
			for (int j = 1; j < M; j++) {
				sb.append(top[j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
}