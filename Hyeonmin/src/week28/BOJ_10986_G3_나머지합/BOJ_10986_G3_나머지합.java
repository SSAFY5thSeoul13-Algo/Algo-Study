package week28.BOJ_10986_G3_나머지합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10986_G3_나머지합 {
	static int N, M;
	static long count;
	static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		int num = 0;
		
		for (int i = 1; i <= N; i++) {
			num = (num + Integer.parseInt(st.nextToken()))%M;
			arr[num]++;
		}
		
		for (int i = 0; i < M; i++) {
			count += (long)arr[i]*(arr[i]-1)/2;
		}
		
		count += arr[0];
		
		System.out.println(count);
	}

}
