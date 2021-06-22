package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_G4_연구소3 {
	//해당 실행에서 결과가 나온 경우
	static boolean isFinish;
	//처음 시작에 벽과 바이러스의 개수
	static int defaultCnt;
	static int N, M;	//M : 활성 바이러스 갯수
	//결과
	static int result = Integer.MAX_VALUE;
	//활성화할 바이러스 인덱스
	static int[] tgt;
	//연구소 정보
	static int[][] map;
	//바이러스 좌표
	static List<int[]> virus = new ArrayList<int[]>();
	//방문한 방 개수
	static int visitCnt=0;
	//방문여부를 저장하는 2차원 배열
	static boolean[][] isVisited;
	//상 하 좌 우
	static int[] deltaX = {0, 0, -1, 1};
	static int[] deltaY = {-1, 1, 0, 0};
	//bfs를 위한 큐
	static Queue<Info> queue = new LinkedList<Info>();
	//해당 실행결과의 시간
	static int time;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		//연구소 크기와 활성화 바이러스 개수 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//연구소 배열 생성
		map = new int[N][N];

		//활성화 바이러스 인데스를 저장할 배열
		tgt = new int[M];
		
		//연구소 정보 갱신
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				//바이러스인 경우
				if(map[i][j] == 2) {
					int[] v = {i,j};
					//바이러스 리스트에 추가
					virus.add(v);
					defaultCnt++;
				}
				//벽인 경우
				else if(map[i][j] == 1) {
					defaultCnt++;
				}
			}
		}
		
		//활성화 바이러스 선택
		comb(0, 0);

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);

	}
	static void comb(int cnt, int idx) {
		//활성화 바이러스를 모두 고른 경우
		if(cnt==M) {
			//방문 여부 배열 생성
			isVisited = new boolean[N][N];
			//바이러스 활동 시작
			process(0,0);
			
			return;
		}
		
		if(idx >= virus.size())
			return;
		
		tgt[cnt] = idx;
		comb(cnt+1, idx+1);
		comb(cnt, idx+1);
	}
	
	//각 바이러스의 시작단계
	static void process(int y, int x) {
		//활성화 바이러스가 있는 곳의 정보 입력
		for (int i = 0; i < M; i++) {
			queue.offer(new Info(0, virus.get(tgt[i])[0], virus.get(tgt[i])[1]));
			
		}

		//최소 시간을 구하기 위해 최대값을 저장
		time = Integer.MAX_VALUE;
		//현재 실행중인 process 결과 여부
		isFinish= false;
		//시작 visitCnt를 default로 저장 : 벽과 바이러스가 있는 방을 카운트에서 제외하기 위해
		visitCnt = defaultCnt;
		
		//큐에 있는 것이 없어지거나 결과가 나올 때 까지 반복
		while(!queue.isEmpty() && !isFinish) {
			Info info = queue.poll();
			//바이러스 활동
			bfs(info.time, info.y, info.x);
		}
		//큐를 비움
		queue.clear();

		//이번 활동에서 나온 결과와 기존 결과를 비교해 저장
		result = Math.min(time, result);
		
	}
	
	static void bfs(int curTime, int y, int x) {

		//이미 방문했거나 결과가 나왔으면 끝냄
		if(isVisited[y][x] || isFinish == true) {
			return;
		}
		
		//바이러스가 있는 방은 카운트에서 제외
		if(map[y][x]==2) {
			visitCnt--;
		}
		
		//현재 방의 방문 여부와 카운트 갱신
		isVisited[y][x] = true;
		visitCnt++;
		
		//가능한 모든 방을 방문한 경우
		if(visitCnt == N*N) {
			//끝났다는 것을 표시
			isFinish = true;
			//이번 실행의 시간을 저장
			time =curTime;
			return;
		}
		
		//상하좌우 4방향 이동
		for (int i = 0; i < 4; i++) {
			//이동가능하면 큐에 삽입
			if(canGo(y, x, i)) {
				queue.offer(new Info(curTime+1, y+deltaY[i], x+deltaX[i]));
			}
		}
	}
	
	//해당 위치에 바이러스가 이동할 수 있는지 여부를 반환하는 메소드
	static boolean canGo(int y, int x, int dir) {
		//배열 범위 밖으로 이동하는 경우
		if(y+deltaY[dir] < 0 || y+deltaY[dir] >= N || x+deltaX[dir] < 0 || x+deltaX[dir] >= N) {
			return false;
		}
		//이동하려는 곳이 이미 지나온 곳이거나 벽인 경우
		if(isVisited[y+deltaY[dir]][x+deltaX[dir]]==true || map[y+deltaY[dir]][x+deltaX[dir]] == 1) {
			return false;
		}
		
		return true;
	}
	
	//다음 이동위치와 시간 저장 위한 클래스
	static class Info{
		//현재 걸린 시간
		int time;
		//좌표
		int x;
		int y;
		
		public Info(int time, int y, int x) {
			super();
			this.time = time;
			this.x = x;
			this.y = y;
		}
	}
}