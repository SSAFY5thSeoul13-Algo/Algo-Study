package week24.BOJ_2428_S3_표절;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2428_S3_표절_풀이2 {
	static int N;
	static int[] sizes;
	static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sizes = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//각 파일 사이즈
		for (int i = 0; i < N; i++) {
			sizes[i] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(sizes);
		
		int idx = 0;
		
		for (int i = 0; i <N; i++) {
			
			while(idx < N && sizes[i] >= 0.9*sizes[idx]) {
				idx++;
			}
			
			result += (idx-1) - i;
		}
		
		System.out.println(result);
		
	}
}
