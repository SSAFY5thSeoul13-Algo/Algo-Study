package week24.boj2428;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2428_S3_표절 {
	static int N;
	static long result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] files = new int[N];
	
		for(int i=0; i<N; i++) {
			files[i] = Integer.parseInt(st.nextToken());
		}
		
		// 배열을 오름차순으로 정렬 
		Arrays.sort(files);
		
		for(int i=0; i<N; i++) {
			int left = i;
			int right = N-1;
			while(left<=right) {
				int mid = (left+right)/2;
				
				if(files[i]>=files[mid]*0.9) {
					left = mid + 1;
				}else {
					right = mid - 1;
				}
			}
			result += right - i;
		}
		
		
		
		System.out.println(result);
	}
	


}
