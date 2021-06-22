package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_캔디캔디_2878 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());		// 택희가 가진 사탕수
		int N = Integer.parseInt(st.nextToken());		// 친구 수
		
		long [] candy = new long[N];					// 친구들이 원하는 사탕수
		
		long sum = 0;									// 친구들이 원하는 사탕의 합
		for(int i=0; i<N; i++) {
			long data = Integer.parseInt(br.readLine());
			candy[i] = data;
			sum += data;
		}
		
		long remain = sum - M;							// 못나눠주는 사탕의 수
		Arrays.sort(candy);
		
		long div = (long) Math.pow(2,  64);
		
		long angry = 0;
		for(int i=0; i<N; i++) {
			long notReceive = Math.min(candy[i], remain/(N-i) );		// 못나눠주는 사탕수 할당
			angry += notReceive * notReceive;							// 분노계산
			angry %= div;												// 나머지연산
			
			remain -= notReceive;										// 못나눠주는 사탕줄이기
		}

		System.out.println(angry);
	}

}
