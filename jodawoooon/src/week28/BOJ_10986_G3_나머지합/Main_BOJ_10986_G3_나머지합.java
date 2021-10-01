package week28.BOJ_10986_G3_나머지합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 10986 나머지 합
 * @Author : Daun JO
 * @Date : 2021. 9. 27. 
 * @Algorithm : Prefix Sum
 *
 */

public class Main_BOJ_10986_G3_나머지합 {
	static int N, M;
	static long mod[];
	static long cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mod = new long[M];
		st = new StringTokenizer(br.readLine());
		long sum = 0;
		for(int i = 0; i< N ; i++) {
			sum += Integer.parseInt(st.nextToken());
			if(sum%M==0) cnt++; 	// (1,i) 구간
			mod[(int)(sum%M)]++;		// 누적합의 나머지 카운트
		}
		
		for(int i = 0; i < M ; i++) {
			cnt += (mod[i]*(mod[i]-1))/2; //mod[i]C2
		}
		System.out.println(cnt);
		
	}
}
