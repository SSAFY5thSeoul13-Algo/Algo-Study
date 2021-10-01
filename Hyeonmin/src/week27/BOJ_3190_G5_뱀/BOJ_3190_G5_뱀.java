package week27.BOJ_3190_G5_뱀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_G5_뱀 {
	//뱀 몸통
	static Deque<int[]> dq = new ArrayDeque<>();
	//방향 변하는 시기
	static Queue<Node> q = new LinkedList<>();;
	static int N, K, L, time=0, dir=0;
	static int[][] map;
	//우 하 좌 상
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Node{
		int time;
		char dir;
		
		public Node(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			//사과는 2로 표시
			map[x][y] = 2;
		}
		
		L = Integer.parseInt(br.readLine());
		
		//방향 변화 입력
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			
			q.offer(new Node(time, dir));
		}
		
		//시작 위치
		dq.offerLast(new int[] {1,1});
		map[1][1] = 1;
		
		while(true) {
			time++;
			int[] point = dq.peekLast();
			
			int nx = point[0] + dx[dir];
			int ny = point[1] + dy[dir];
			
			//범위 밖
			if(nx<1 || ny<1 || nx>N || ny>N || map[nx][ny] == 1)	break;
			
			//사과를 먹는 경우
			if(map[nx][ny] == 2) {
				map[nx][ny] = 1;
				dq.offerLast(new int[] {nx,ny});
			}
			else {
				int[] deletePoint = dq.pollFirst();
				
				map[deletePoint[0]][deletePoint[1]] = 0;
				
				map[nx][ny] = 1;
				dq.offerLast(new int[] {nx,ny});
			}
			
			if(!q.isEmpty()) {
				Node node = q.peek();
				
				//방향을 바꾸는 경우
				if(node.time == time) {
					q.poll();
					
					//좌
					if(node.dir == 'L') {
						dir = dir == 0 ? 3 : dir-1;
					}
					//우
					else if(node.dir == 'D'){
						dir = dir == 3 ? 0 : dir+1;
					}
				}
			}
			
		}
		
		System.out.println(time);

	}

}
