package week15.BOJ_14646_S4_욱제는결정장애야;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[] select;
	public static void main(String[] args) throws Exception{
		/*
			돌림판 N조각, N개의 메뉴
			
			2N번 돌리면 N일 동안의 메뉴선정 가능 
			메뉴를 선정하며 돌림판에 붙어있을 수 있는 스터커의 최대 갯수
			
			
		 *  */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N입력
		N = Integer.parseInt(br.readLine());
		// 배열 N+1입력 (메뉴가 1부터 시작)
		select = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int max = 0;
		int cnt = 0;
		for(int i=0; i<2*N; i++) {
			int data = Integer.parseInt(st.nextToken());
			
			// 처음 뽑은 메뉴라면
			if(!select[data]) {
				cnt++;		 // 스티커 붙이기
				select[data] = true; // 메뉴선택 체크
				max = Math.max(max, cnt); // 스티커 최댓값 갱신
			}else {
				cnt--;	// 스티커 제거
			}
		}
		
		System.out.println(max);
	}

}


