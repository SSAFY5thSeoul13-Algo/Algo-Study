package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_15787_S2_기차가 {
	/*
	 * 
	 * 70844	1036
	 * 
	 * 
	 * 
	 * 
	N개의 기차. (1번~N번)
	기차에는 20개의 일렬로 된 좌석이 있음. 한좌석에 사람 한명 탐
	
	1~N번 기차에 대해 M개의 명령이 주어짐
	
	명령의 종류
	1 i x : i번째 기차에 x번째 좌석에 사람을 태워라. 사람 이미 있으면 continue
	2 i x : i번째 기차에 x번째 좌석 사람 하차 . 사람 없으면 continue
	3 i : i번째 기차에 앉은 승객들이 모두 한칸씩 뒤로감. 만약 20번째 사람있으면 하차
	4 i : i번째 기차에 앉은 승객들이 모두 한칸씩 앞으로감. 1번 사람있으면 하차
	
	M 명령 후 1번기차부터 순서대로 은하수를 건넌다
	기차는 순서대로 지나가며 기차가 지나갈때의 승객의 상태를 기록
	이미 목록에 존재하는 기록이면 해당 기차는 건널 수 없음
	
	은하수를 건널 수 있는 기차의 수는?
			*/
	
	static int N,M, ans;
	static boolean map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //기차의 수
		M = Integer.parseInt(st.nextToken()); //명령의 수
		
		map = new boolean[N+1][21]; //1~N개의 기차. 각 기차에는 20개의 좌석
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
	
			if(cmd==1) {
				int i = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				//i번째 기차에 x번째 좌석에 사람을 태워라
				if(map[i][x]) continue; //사람 이미 있으면 continue
				map[i][x] = true;
				
			}else if(cmd==2) {
				int i = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				//i번째 기차에 x번째 좌석 사람 하차
				if(!map[i][x]) continue; //사람 없으면 continue
				map[i][x] = false;
				
			}else if(cmd==3) {
				int i = Integer.parseInt(st.nextToken());
				
				//모두 한칸씩 뒤로감.
				for (int j = 20; j > 1; j--) {
					map[i][j] = map[i][j-1];
				}
				map[i][1] = false;
				
			}else if(cmd==4) {
				int i = Integer.parseInt(st.nextToken());
				
				//모두 한칸씩 앞으로
				for (int j = 1; j < 20; j++) {
					map[i][j] = map[i][j+1];
				}
				map[i][20] = false;
			}
			
			
		}
		
		
		
		//M 명령 후 1번기차부터 순서대로 은하수를 건넌다
		ArrayList<boolean[]> checkList = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			boolean flag = false;
			for (int j = 0; j < checkList.size(); j++) {
				boolean[] tmp = checkList.get(j);
				
				int cnt = 0;
				for (int k = 1; k <= 20; k++) {
					if(map[i][k]==tmp[k]) cnt++;
				}
				
				//System.out.println(cnt);
				if(cnt==20) {
					flag = true;
					break;
				}
				
			}
			
			if(flag) continue;
			else {
				checkList.add(map[i]);
				ans++;
			}
			
		}
		
		
		System.out.println(ans);
		
	}

}
