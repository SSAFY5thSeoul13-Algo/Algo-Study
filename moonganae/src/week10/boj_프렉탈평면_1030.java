package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 프렉탈평면
 * 번호 : 1030
 * 난이도 : 골3
 * 풀이시간 : 3시간
 * 사용 알고리즘 : 분할정복
 */
public class boj_프렉탈평면_1030 {

	static int S, N, K, R1, C1, R2, C2;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());	// 시간
		N = Integer.parseInt(st.nextToken());	// 단위시간당 커지는 정시각형 크기
		K = Integer.parseInt(st.nextToken());	// 단위시간당 커지는 가운데 검은색 정사각형 크기
		R1 = Integer.parseInt(st.nextToken());	// 출력할 시작행
		R2 = Integer.parseInt(st.nextToken());	// 출력할 끝행
		C1 = Integer.parseInt(st.nextToken());	// 출력할 시작열
		C2 = Integer.parseInt(st.nextToken());	// 출력할 끝열
		
		int n = N;
		int k = K;
		
		/* 단위시간 s초일때 n과 k의 크기를 구하기 */
		for(int i=1; i<S; i++) {
			n *= N;
			k *= N;
		}
		
		/* 순회하면서 검은색일 경우 1, 흰색일 경우 0을 출력한다. */
		StringBuilder sb = new StringBuilder();
		for(int i=R1; i<=R2; i++) {
			for(int j=C1; j<=C2; j++) {
				sb.append(isBlack(n,k,i,j));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	/* n : 현재 전체 사각형 크기
	 * k : 현재 가운데 검은색 사각형 크기
	 * y, x : 확인할 행과 열
	 * 
	 * return : 0 => 흰색 , 1 => 검은색
	 *  */
	static int isBlack(int n, int k, int y, int x) {
		
		/* 1x1 : 즉 단위시간 0에서는 흰색 정사각형이므로 0리턴*/
		if(n==1)
			return 0;
		
		/* 전체 사각형 중 가운데 영역일 경우 */
		int s = (n-k) / 2;
		
		/* 가운데라 검으색일 경우 1리턴 */
		if(s<=y && y<s+k && s<=x && x<s+k)
			return 1;
		
		
		/* 가운데 영역이 아닐경우는 분할정복으로 이전 단위시간으로 가서 확인해야 한다. */
		int nextN = n/N;	// 이전 단위시간 n
		/* 범위를 줄이면서 좌표보정도 해주어야 한다. */
		return isBlack(nextN, k/N, y%nextN, x%nextN);
	}
}
