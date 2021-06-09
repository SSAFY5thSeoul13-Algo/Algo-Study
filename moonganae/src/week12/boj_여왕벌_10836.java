package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_여왕벌_10836 {

	/* 배열의 크기, 날짜수 */
	static int M, N, limit;
	
	/* 날짜별 성장수, 현재 성장치 보관 */
	static int[] input, grow;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		limit = 2*M - 1;
		grow = new int[limit];
		input = new int[3];
		
		/* 첫날 성장치는 1 */
		for(int i=0; i<limit; i++)
			grow[i] = 1;
		
		for(int i=0; i<N; i++) {
			// 성장치 셋팅
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<3; j++){
				input[j] = Integer.parseInt(st.nextToken());
			}
			// 성장
			process();
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int i=M-1; i>=0; i--) {
			// 첫째열
			sb.append(grow[i] + " ");
			
			// 나머지열 
			for(int j=0; j<M-1; j++) {
				sb.append(grow[M+j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	public static void process() {
		
		int oneLimit = input[0]+input[1];
		// 1 성장
		for(int i=input[0]; i<oneLimit; i++) {
			grow[i] += 1;
		}
		
		// 2성장
		for(int i=oneLimit; i<limit; i++) {
			grow[i] += 2;
		}
	}
}
