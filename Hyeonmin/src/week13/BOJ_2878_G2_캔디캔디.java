package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2878_G2_캔디캔디 {
	//M: 사탕 수, N: 친구 수, sum: 친구들이 원하는 사탕의 총 합
	static long M, N, sum;
	static long result;
	//각 친구가 원하는 사탕의 수
	static long[] arr;
	static long t = (long) Math.pow(2, 64);
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new long[(int) N];
		
		//각자 원하는 사탕 수와 총 사탕수 구함
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			sum += arr[i];
		}
		
		//배열 정렬
		Arrays.sort(arr);
		
		//부족한 사탕 수
		long num = sum - M;
		
		//원하는 사탕 수가 적은 순서부터
		for (int i = 0; i < N; i++) {
			//부족한 사탕의 총 수를 남은 인원으로 나눈 값과 현재 받지 못한 사탕의 수를 비교해서 더 작은 것을 선택
			long temp = Math.min(arr[i], num/(N-i));
			//부족한 사탕 총 수에서 현재 사람이 받지 못한 사탕 수 제외
			num -= temp;
			
			//만족도 계산
			result = (result + temp*temp)%t;
		}
		
		System.out.println(result);
	}
}