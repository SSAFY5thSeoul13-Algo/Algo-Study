package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_11559_G5_PuyoPuyo {
/*	
	BFS
	
	터트릴 뿌요 그룹이 없을때까지 while문을 반복해
	주어진 조건대로 뿌요뿌요 게임을 구현했습니다.

	먼저 2중 for문에서 중복되지않게 bfs를 돌려서
	같은색 뿌요가 4개이상 연결되어있는 list를 찾았습니다.
	그리고 터트린 뒤에는 cnt를 증가시켜 while문을 탈출할 수 있는 변수를 설정했습니다

	터트릴 수 있는 그룹을 다 터트린 뒤에는 1연쇄씩 증가시켰고 (ans++)

	이 후 중력에 의해 공중에 떠있는 뿌요들은 다 아래로 떨어트려주었습니다.
	이 과정은 마찬가지로 2중 for문과 while문을 이용해
	떨어질 수 있는 곳까지 끌어 내리는 형태로 구현했습니다


	- 결과
	11604	80
	*/
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int ans;
	static char map[][];
	static boolean[][] visited;
	static int R = 12;
	static int C = 6;
	static ArrayList<Node> popList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		} 
		//기본 맵 셋팅
		
		while(true) {
			
			int cnt = 0; //터진 뿌요 group 카운트
			visited = new boolean[R][C];
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					
					popList = new ArrayList<>(); //터트릴 대상 노드를 담아놓는 arrayList
					
					if(map[i][j]=='.') continue;
					if(visited[i][j]) continue;
					
					
					if(bfs(i,j)>=4) {
						//1. 같은 색 뿌요가 4개 이상 연결되어 있으면 한꺼번에 없어진다. 
						for (Node n : popList) {
							map[n.x][n.y]='.';
						}
						cnt++; //없앴으면 그룹 cnt증가
					}
					
				}
			}
		
			
			if(cnt==0) break; //2. 터질게 하나도 없으면 while문을 나간다.
			ans++; //터트릴 그룹 다 터트렸으면 1연쇄 증가 (동시에 터짐)
			
			
			//3. 중력의 영향을 받아 차례대로 아래로 떨어짐
			for (int y=0; y<C; y++) {
				for (int x=R-1 ; x>=0; x--) { //중력에의해 밑에것부터 떨어짐
					if(map[x][y]=='.') continue;

					
					int nx = x+1; //밑으로 내려간다.
					while(true) {
						if(nx==R) break; //범위밖으로 나가면 break
						
						if(map[nx][y]=='.') { //아랫행이 빈공간이면
							map[nx][y] = map[x][y]; //빈공간으로 현재 뿌요를 끌어내린다
							map[x][y] = '.'; //현재 공간은 빈공간이됨
							
							x = nx;
							nx++; //한 행 더 밑으로 내려간다
							
						}else break; //밑에 막히면 break
					}
					
				}
			}
			//print();
		
		}
		

		System.out.println(ans);
	}
	
	
	
	private static int bfs(int i, int j) {
		Queue<Node> queue = new LinkedList<>();
	
		queue.add(new Node(i,j));
		visited[i][j] = true;
		popList.add(new Node(i,j));
		
		int cnt = 1; //터질 뿌요 개수 cnt
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(!isIn(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==map[i][j]) { //같은색이면 터트릴 수 있다.
					visited[nx][ny] = true;
					//System.out.println(nx+","+ny+" "+map[nx][ny]);
					
					cnt++;
					popList.add(new Node(nx,ny));
					queue.add(new Node(nx,ny));
				}
			}
		}
		
		return cnt;
	}
	private static boolean isIn(int nx, int ny) {
		if(nx<0||ny<0||nx>=R||ny>=C) return false;
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
	
	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}System.out.println("======================");
	}
	
	
}
