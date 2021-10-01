package week28.boj10986;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10986_G3_나머지_합 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 나머지를 저장하는 배열 
		int[] arr = new int[M];
		
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sum += Integer.parseInt(st.nextToken())%M;
			arr[sum%M]++;
		}
		
		long result = arr[0];
		for(int i=0; i<M; i++) {
			int n = arr[i];
			result += (long) n*(n-1)/2;
		}
		
		System.out.println(result);
		

	}

}
