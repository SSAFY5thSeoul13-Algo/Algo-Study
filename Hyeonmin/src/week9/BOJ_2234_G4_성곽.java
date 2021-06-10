package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_G4_성곽 {
	//N : 행, M : 열, num : 방의 갯수, max : 제일 큰 방의 크기, sum : 2개의 방을 합친 넓이의 최대값
	static int N, M, num, max, sum;
	//방의 벽 정보를 저장할 배열
	static int[][] map;
	//방의 번호를 저장할 배열
	static int[][] visited;
	//좌 상 우 하
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1];
		//방번호가 1부터 시작해서 더미값을 넣음
		list.add(0);
		
		//벽 정보 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//각 위치를 시작점으로 bfs
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				bfs(i, j);
			}
		}
		
		//전체 탐색을 하면서 각 방에서의 다른 방과 합칠수 있는 경우 넓이의 최대값을 구함
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				int temp = getSum(i,j);
				
				if(sum < temp)
					sum = temp;
				
			}
		}
		
		//출력
		System.out.println(num);
		System.out.println(max);
		System.out.println(sum);
	}
	
	static void bfs(int startY, int startX) {
		//방번호가 0이 아닌 경우 이미 방문을 한 방이다
		if(visited[startY][startX] != 0)
			return;
		
		//방번호 갱신
		num++;
		
		//시작점을 큐에 넣음
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {startY,startX});
		
		//방 사이즈
		int size = 0;
		
		while(!q.isEmpty()) {
			//탐색하는 방을 큐에서 꺼냄
			int[] next = q.poll();
			int y = next[0];
			int x = next[1];
			
			//이미 방문한 경우 스킵
			if(visited[y][x] != 0)
				continue;
			
			//방문 체크로 방번호를 배열에 저장하고 방 사이즈 1 증가
			visited[y][x] = num;
			size++;
			
			//현재 방의 벽 정보를 가져옴
			int bit = map[y][x];
			
			//4방향에 대해서
			for (int d = 0; d < 4; d++) {
				//다음 방의 좌표
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				//만약 그 방에 가는데 문제가 없으면 큐에 해당 방 좌표를 입력
				if(canGo(ny, nx, d, bit)) {
					q.offer(new int[] {ny, nx});
				}
			}
		}
		
		//이번에 구한 방 크기가 더 크면 max에 저장
		if(max < size)
			max =size;
		
		//리스트에 방의 크기를 저장
		list.add(size);
	}
	
	//bfs에서 다음 방을 갈 수 있는지 여부를 반환
	static boolean canGo(int ny, int nx, int d, int bit) {
		//범위 밖이거나 이미 방문한 방이면 false
		if(ny < 1 || nx < 1 || ny > N || nx > M || visited[ny][nx] != 0)
			return false;
		
		//방향을 벽 정보와 비교하기 위해 변환
		int dir = (int) Math.pow(2, d);
		
		//&연산이 0이 아닌경우는 해당 방향이 막혀있다는 것이므로 false
		if((bit & dir) != 0) {
			return false;
		}
		
		return true;
	}
	
	//방을 합치는 경우 합친 방의 크기를 반환
	static int getSum(int y, int x) {
		//함친 방의 크기를 저장할 변수
		int temp = 0;
		//현재 방의 번호
		int rNum = visited[y][x];
		
		//4방향
		for (int d = 0; d < 4; d++) {
			//확인할 방의 좌표
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			//범위 밖인경우 스킵
			if(ny < 1 || nx < 1 || ny > N || nx > M)
				continue;
			
			//확인할 방의 번호
			int nNum = visited[ny][nx];
			
			//두 방의 번호가 다르다 => 방 사이에 벽이 있다
			if(rNum != nNum) {
				//이미 구한 합의 값과 새로 확인한 합 중에서 큰 값을 저장
				temp = Math.max(temp, list.get(visited[y][x]) + list.get(visited[ny][nx]));
			}
		}
		
		//구한 합을 반환
		return temp;
	}
}