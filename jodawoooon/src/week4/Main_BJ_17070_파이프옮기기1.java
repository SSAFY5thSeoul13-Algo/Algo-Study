package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_17070_파이프옮기기1 {

	/*
	 * 집 N*N
	 * r은 행의번호 c는 열의번호 (둘다 1부터 시작)
	 * 각 칸은 빈칸이거나 벽이다
	 * 파이프는 연속된 두개의 칸을 차지함
	 * 3가지 방향.
	 * 좌,우  / 상,하  / 좌상,우하
	 * 
	 * 파이프는 빈칸만 차지 
	 * 방향은 우, 우하, 하  방향
	 * 45도씩만 회전 가능
	 * 
	 * 가로 => 가로 or 대각선 
	 * 세로 => 세로 or 대각선
	 * 대각선 => 가로 or 세로 or 대각선
	 * 
	 * 가로 이동 시 => 우 검사
	 * 세로 이동 시 => 하 검사
	 * 대각선 이동시 => 우,하, 우하 검사
	 * 
	 * 
	 * 
	 * (1,1)-(1,2)의 파이프의 한쪽끝은 (N,N)으로 이동시키는 방법의 개수. => BFS
	 */
	
	
/*	처음에는 BFS로 풀었으나 계속 시간초과가 나왔습니다. 
 * 
 * 최대한 코드를 고쳐보려 했으나 해결하기 어려울 것 같아서 같은 로직을 큐를 버리고 재귀로 구현했더니 통과할 수 있었습니다...

	0:가로, 1:세로, 2:대각선으로 상태를 두고
	가로 => 가로 or 대각선
	세로 => 세로 or 대각선
	대각선 => 가로 or 세로 or 대각선으로 이동할 수 있으므로
	해당 조건에 맞게 검사하며 파이프를 이동시켰습니다.

	메모리 : 15348kb  /	 시간 : 264ms*/
	
	static int N, arr[][], cnt;
	static int dr[] = {0,1,1};//우 하 우하
	static int dc[] = {1,0,1};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //집의 크기
		
		arr = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(1,2,0);
		System.out.println(cnt);
	}


	private static void solve(int r, int c, int dir) {
		if(r==N&&c==N) { //마지막칸까지 이동시킨경우
			cnt++; //cnt++
			return;
		}
		
		for (int d = 0; d < 3; d++) {
			if(dir==0&&d==1) continue; //현재 가로로 놓여있다면 가로, 대각선으로 옮기는 경우만 확인한다
			if(dir==1&&d==0) continue; //현재 세로로 놓여있다면 세로, 대각선으로 옮기는 경우만 확인한다
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr<=N&&nc<=N) {
				if(d==0||d==1) {
					if(arr[nr][nc]==0) {
						solve(nr,nc,d);
					}
				}else if(d==2) { //대각선인 경우 3곳이 벽이 아닌지 확인해야한다.
					//범위확인은 할 필요없다
					if(arr[nr][nc]==0&&arr[nr-1][nc]==0&&arr[nr][nc-1]==0) {
						solve(nr,nc,d);
					}
				}
			}
		}
		
		
		
	}
	
	
	
}

