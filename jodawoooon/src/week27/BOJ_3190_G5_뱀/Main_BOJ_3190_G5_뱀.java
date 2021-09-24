package week27.BOJ_3190_G5_뱀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_3190_G5_뱀 {
	static int N, K, L, board[][], time;
	static int dx[] = {0,1,0,-1}; // 우 하 좌 상
	static int dy[] = {1,0,-1,0};
	static HashMap<Integer, Character> command;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		command = new HashMap<>();
		
		
		N= Integer.parseInt(br.readLine()); //board의 크기
		K= Integer.parseInt(br.readLine()); //사과의 갯수
		
		board = new int[N+1][N+1];

		
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = 1; //사과
		}
		
		L = Integer.parseInt(br.readLine()); //뱀의 방향 변환 횟수
		
		for(int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C  = st.nextToken().charAt(0);

			//게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전
			//x 인덱스로 C 알아내기 -> hashmap
			command.put(X,  C);
		}
		
		
		Deque<Node> queue = new LinkedList<>();
		queue.add(new Node(1,1)); //뱀의 초기위치 (1,1)
		board[1][1] = -1; //뱀
		//게임 시작

		
		int dir = 0;
		time = 1; //첫 이동 시 1초
		
		while(true) {

			Node head = queue.peekFirst();
			
			int nx = head.x + dx[dir];
			int ny = head.y + dy[dir];
			
			if(!check(nx,ny)) break;
			
			queue.addFirst(new Node(nx,ny)); 
			//먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			
			
			if(board[nx][ny]==1) { 
				//만약 이동한 칸에 사과가 있다면
				board[nx][ny]=-1; 
				//그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			}else if(board[nx][ny]==0) { 
				//만약 이동한 칸에 사과가 없다면
				board[nx][ny]=-1;
				//몸길이를 줄여서 꼬리가 위치한 칸을 비워준다
				Node tail = queue.pollLast();
				board[tail.x][tail.y]=0;
			}

			if(command.containsKey(time)) {
				if(command.get(time)=='L') {
					//왼쪽
					dir = (dir+3)%4;
				}else if(command.get(time)=='D') {
					//오른쪽
					dir = (dir+1)%4;
				}
			}
			

			time++;
		}
		
		System.out.print(time);
		
		
	}
	
	private static boolean check(int nx, int ny) {
		//벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
		
		if(nx<=0||ny<=0||nx>N||ny>N) return false; //벽 검사
		
		if(board[nx][ny]==-1) return false; //자기자신의 몸
		
		return true;
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
