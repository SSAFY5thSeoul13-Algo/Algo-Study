package week24.BOJ_2428_S3_표절;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/* 솔루션의 수, 슬라이딩 윈도우 시작인덱스 */
	static int N, si;
	/* 정답수 */
	static long ans;
	/* 파일크기 배열 */
	static int[] A;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = new int[N];
		/* 1. 솔루션 파일 크기 입력 */
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 2. 파일크기로 정렬 */
		Arrays.sort(A);
		
		for(int i=1; i<N; i++) {
			
			/* 검사대상인지 체킹 */
			while(isCheck(si, i) == false) {
				// 현재 인덱스와 같다면 모든 범위를 탐색함
				if(si==i) break;
				// 아니라면 검색범위 좁히기 -> 슬라이딩 윈도우 시작인덱스 증가
				si++;
			}
			/* 검색해야할 파일 쌍수 더하기 */
			ans += (i - si);
		}
		
		System.out.println(ans);
	}
	
	/**
	 * 
	 * @param start 슬라이딩 윈도우 시작인덱스
	 * @param cur 현재 검사할 파일 인덱스
	 * @return 검사여부
	 */
	static boolean isCheck(int start, int cur) {
		if(A[cur] * 0.9 <= A[start]) return true;
		
		return false;
	}

}
