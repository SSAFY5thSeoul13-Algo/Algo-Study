package week22.BOJ_1654_S3_랜선자르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int K, N;
	static int[] line;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		line = new int[K];
		
		for(int i=0; i<K; i++) {
			line[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(line);
		
		// 자연수이기 때문에 1부터!
		long min = 1;
		long max = line[line.length-1];
		long ans = -1;
		
		while(min <= max) {
			long mid = (min+max) / 2;
			
			// 만들 수 있는 랜선의 갯수
			int cnt = 0;
			for(int len : line) {
				cnt += len/mid;
			}
			
			// 내가 원하는 갯수라면
			if(N <= cnt) {
				// 최솟값을 올린다
				ans = Math.max(ans, mid);
				min = mid+1;
				
			}else {
				// 최댓값을 낮춘다.
				max = mid-1;
			}
			
		}
		
		System.out.println(ans);
	}

}
