package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
2 ≤ N, M ≤ 50
1 ≤ T ≤ 50
1 ≤ 원판에 적힌 수 ≤ 1,000
2 ≤ x ≤ N
0 ≤ d ≤ 1
1 ≤ k < M
*/
public class BOJ_17822_G3_원판돌리기 {
	//N: 원판의 수, M: 원판에 적힌 숫자 개수, T: 회전 수, cnt: 남은 숫자 갯수
	static int N, M, T, cnt;
	//원판 정보
	static int[][] grid;
	//상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static class Node{
		int y;
		int x;
		int num;
		
		public Node(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		//원판크기와 숫자 갯수 저장
		grid = new int[N+1][M+1];
		cnt = N*M;
		
		//원판 정보 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//T만큼 회전을 실행
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			//회전
			rotate(idx, dir, num);
		}
		
		
		System.out.println(getSum());
	}
	
	static void rotate(int idx, int dir, int num) {
		//방향에 따른 index변화 값
		int d = dir == 0 ? -1 : 1;
		
		//각 원판을 회전
		for (int i = idx; i <= N; i+=idx) {
			//num 값만큼 회전을 실행
			for (int j = 0; j < num; j++) {
				
				int prev = 1;
				
				//회전시 마지막에 변하지 않은 위치를 저장할 인덱스와 넣을 값
				int tempIdx = 1 - d;
				
				if(tempIdx < 1)
					tempIdx = M;
				
				int temp = grid[i][1];
				
				//회전 진행
				for (int m = 1; m < M; m++) {
					//다음위치의 인덱스
					int next = prev + d;
					
					//범위 확인
					if(next < 1)
						next = M;
					else if(next > M)
						next = 1;
					
					//현재 인덱스의 값에 다음 위치 인덱스 값을 저장
					grid[i][prev] = grid[i][next];
					
					//다음 인덱스를 현재 인덱스로 저장
					prev = next;
				}
				
				//회전이 끝나고 마지막 변하지 않은 부분을 변경시켜줌
				grid[i][tempIdx] = temp;
			}
			
		}
		
		//인접한 숫자를 지우기 전 남아있는 수자의 수
		int startCnt = cnt;
		
		//인접한 숫자 삭제
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				bfs(i, j);
			}
		}
		
		//삭제 전후 숫자 수가 같다 => 인접한 숫자가 없었으므로 그에 따른 처리 실행
		if(cnt == startCnt) {
			process();
		}
		
	}
	
	//인접 숫자 지우는 bfs
	static void bfs(int y, int x) {
		if(grid[y][x] == 0)
			return;
		
		//1번 원의 1인덱스를 시작점으로 지정
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(y,x,grid[y][x]));
		//인접한 인덱스가 없는 경우를 파악하기 위한 변수 -> bfs 실행 횟수
		int count = 0;
		int temp = grid[y][x];
		
		//탐색 실행
		while(!q.isEmpty()) {
			Node node  = q.poll();
			
			//값이 0인 경우 삭제된 숫자이므로 스킵
			if(grid[node.y][node.x] == 0) {
				continue;
			}
			
			//삭제된 숫자가 아닌경우 bfs실행 횟수를 증가하고 삭제처리
			count++;
			cnt--;
			grid[node.y][node.x] = 0;
			
			//4방향
			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				
				//원판 범위 확인
				if(ny < 1 || ny > N)
					continue;
				
				//숫자 위치 확인
				if(nx < 1)
					nx = M;
				else if(nx > M)
					nx =1;
				
				//인접한 숫자가 같은 숫자이면 큐에 넣음
				if(grid[ny][nx] == node.num) {
					q.offer(new Node(ny,nx,node.num));
				}
			}
		}
		
		//만약 인접한 숫자가 없었던 경우 맨 처음 삭제된 숫자를 복구한다
		if(count == 1) {
			grid[y][x] = temp;
			cnt++;
		}
	}
	
	//현재 원판에 있는 수의 총 합을 반환
	static int getSum() {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum += grid[i][j];
			}
		}
		
		return sum;
	}
	
	//인접한 수가 없었던 경우
	static void process() {
		//평균을 구함
		double avg = (double) getSum() / cnt;
		
		//모든 원판 위 숫자에 대해서
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				//삭제처리 되지 않은 경우
				if(grid[i][j] != 0) {
					
					//평균보다 작으면 +1
					if(grid[i][j] < avg) {
						grid[i][j] += 1;
					}
					//평균보다 크면 -1
					else if(grid[i][j] > avg) {
						grid[i][j] -= 1;
					}
				}
			}
		}
	}
}