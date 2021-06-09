package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 난이도 : 실버3
 * 시간 : 15분 
 * */
public class boj_스타트와링크_14889 {
	static int N, R;
	static int[][] ability;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		R = N/2;
		
		ability = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0,0);
		System.out.println(min);
	}
	/* N명중 R(N/2) 명을 뽑는 조합 */
	static void comb(int num, int start, int mask) {
		if(num == R) {
			
			int startTeam = calTeamAbil(mask);
			int linkTeam = calTeamAbil(~mask);
			
			min = Math.min(Math.abs(startTeam - linkTeam), min);
			return;
		}
		
		for(int i=start; i<N; i++) {
			if( (mask & 1<<i) != 0) continue;
			
			comb(num+1, i+1, mask | 1<<i);
		}
	}
	/* 같은 팀의 능력치의 합구하기 */
	static int calTeamAbil(int mask) {
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			if( (mask & 1<<i) == 0) continue;
			for(int j=i; j<N; j++) {
				if( (mask & 1<<j) == 0) continue;
				
				cnt += ability[i][j] + ability[j][i];
			}
		}
		
		return cnt;
	}

}
