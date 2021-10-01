package week28_BOJ__11441_S3_합구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/* 배열의 길이, 구간의 갯수*/
	static int N, M;
	/* 누적합 배열 */
	static int[] arr;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/* 인덱스 0은 0을 가짐 */
		arr = new int[N+1];
		
		/* 누적합 계산*/
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		/* 구간계산 */
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(arr[to] - arr[from-1]);
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}
