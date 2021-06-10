package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_15787_S2_기차가_bit {
//	처음에는 ArrayList로 풀었다가 시간,공간차이가 많이나서
//	비트마스킹으로 다시 풀었습니다.
//
//	각 기차를 표현하는 map array와 기차를 건널때 상태를 기록하는 visit 배열을 사용했습니다.
//	기차의 상태를 0이 20개인 bit로 나타낼 수 있으므로 각 명령을 비트마스킹으로 구현했습니다.
//
//	1. 기차에 사람을 태운다 ⇒ x번째 비트를 1로 바꾸어준다
//	 map[i] |= (1<<x) ; x를 1로바꾸어준뒤 or연산하여 1이 되게 한다.
//	2. 기차에 사람을 내린다 => x번째 비트를 0으로 바꾸어준다 => and연산
//	 map[i] &= ~(1<<x) ; x를 1로 바꾼뒤 not처리하고 x번째 비트만 0이 되게하여 and연산한다.
//	3. 모두 한칸씩 뒤로 간다 ⇒ 왼쪽 shift하여 한칸씩 이동 후 마지막 0으로 바꾸기
//	4. 모두 한칸씩 앞으로 간다 ⇒ 모두 오른쪽 shift하여 하나씩 이동 후 첫번째 0으로 바꾸기
//
//
//	- 결과
//	39868	312

	
	static int N,M, ans;
	static int map[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //기차의 수
		M = Integer.parseInt(st.nextToken()); //명령의 수
		
		map = new int[N+1]; //1~N개의 기차. 각 기차에는 20개의 좌석
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
	
			if(cmd==1) {
				int i = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				//i번째 기차에 x번째 좌석에 사람을 태워라. 사람 이미 있으면 continue => or
				map[i] = map[i] | (1<<x); 
				
			}else if(cmd==2) {
				int i = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				//i번째 기차에 x번째 좌석 사람 하차 => and
				map[i] = map[i] & ~(1<<x);
				
			}else if(cmd==3) {
				int i = Integer.parseInt(st.nextToken());
				
				//모두 한칸씩 뒤로감.
				map[i] = map[i] << 1;
				//만약 20번째 사람있으면 하차
				map[i] = map[i] & ((1<<21)-1);
				
			}else if(cmd==4) {
				int i = Integer.parseInt(st.nextToken());
				
				//모두 한칸씩 앞으로
				map[i] = map[i] >> 1;
				map[i] = map[i] & ~1;
				
			}
			
			
		}
		
		
		
		//M 명령 후 1번기차부터 순서대로 은하수를 건넌다
		boolean visited[] = new boolean[1 << 21];
		for (int i = 1; i <= N; i++) {
			
			if(visited[map[i]]) continue;
			else {
				ans++;
				visited[map[i]]=true;
			}
		}
		
		
		System.out.println(ans);
		
	}

}
