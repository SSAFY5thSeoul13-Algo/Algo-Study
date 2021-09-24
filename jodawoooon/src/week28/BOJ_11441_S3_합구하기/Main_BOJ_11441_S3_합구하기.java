package week28.BOJ_11441_S3_합구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 11411 합구하기
 * @Author : Daun JO
 * @Date : 2021. 9. 24. 
 *
 */
public class Main_BOJ_11441_S3_합구하기 {
	
	static int N, M, arr[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];	//누적합

		st = new StringTokenizer(br.readLine());
		
		int sum = 0 ;
		for(int i = 1; i<=N ; i++) {
			sum += Integer.parseInt(st.nextToken());
			arr[i] = sum;
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			
			System.out.println(arr[y]-arr[x-1]); //x~y 구간합
		}
	}

}

