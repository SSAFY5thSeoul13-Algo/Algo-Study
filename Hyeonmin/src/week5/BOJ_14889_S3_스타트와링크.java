package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_S3_스타트와링크 {
	static int N;
	static int[][] map;
	static boolean[] selected;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		selected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st =  new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//팀을 만들기 위한 조합을 실행
		comb(0, 0);
		
		System.out.println(min);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == N/2) {
			makeTeam();
			
			return;
		}
		if(idx == N) {
			return;
		}
			
		selected[idx] = true;
		comb(idx+1, cnt+1);
		selected[idx] = false;
		comb(idx+1, cnt);
	}
	
	static void makeTeam() {
		//두개의 팀을 저장할 배열
		int[] sTeam = new int[N/2];
		int[] lTeam = new int[N/2];
		//각 팀의 인덱스
		int sIdx = 0;
		int lIdx = 0;
		
		//조합에서 선택한 경우 sTeam, 안한경우 lTeam으로 입력
		for (int i = 0; i < N; i++) {
			if(selected[i]) {
				sTeam[sIdx++] = i;
			}
			else {
				lTeam[lIdx++] = i;
			}
		}
		
		//각 팀의 능력치를 저장할 변수
		int sValue = 0;
		int lValue = 0;
		
		//반복문을 통해서 각 팀의 능력치의 합을 모두 더한다
		for (int i = 0; i < lTeam.length; i++) {
			for (int j = 0; j < lTeam.length; j++) {
				sValue += map[sTeam[i]][sTeam[j]];
				lValue += map[lTeam[i]][lTeam[j]];
			}
		}
		
		//두 팀의 능력치 차이를 구함
		int result = Math.abs(sValue-lValue);
		
		//더 작은 값을 저장
		min = Math.min(min, result);
	}
	
}
