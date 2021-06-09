package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16197_두동전 {
	
	/*
	 * BFS
	 * 두 동전을 동시에 이동시키는 문제
	 * 	11864	76
	 */
	
	static int N, M, ans, arr[][], coin[];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static Queue<Node> queue = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M]; //N*M 크기의 보드
		//각 칸은 비어있거나 벽, 두개의 빈칸에는 동전이 하나씩 있다.
		//버튼은 왼,오,위,아래. 버튼을 누르면 동전이 버튼의 방향으로 "동시에" 이동한다.
		
		//1. 동전이 이동하려는 칸이 벽('#')이면 : 이동X
		//2. 동전이 이동하려는 방향이 보드 밖이면 : 동전이 떨어짐
		//3. 그 외엔 한칸씩 이동. : 이미 동전이 있어도 이동
		//4. 두 동전 중 하나만 보드에서 떨어트리기 위해 버튼을 최소 몇 번 눌러야 하는가?
		
		//bfs
		coin = new int[4]; //초기동전위치
		int t = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char tmp = str.charAt(j);
				if(tmp=='o') { //동전
					arr[i][j] = 1;
					queue.add(new Node(i,j,0)); //동전 큐에 집어넣기
				}else if (tmp=='.') {
					arr[i][j] = 0;
				}else if (tmp=='#') {
					arr[i][j] = -1;
				}
			}
		}
		//기본 맵 셋팅 : 1이상은 동전, 0은 빈칸 -1은 벽
		
		bfs();

		
	}

	private static void bfs() {
		
		boolean visited[][][][] = new boolean[N][M][N][M];
		
		//두개를 "동시에" 움직여야한다.
		
		while(!queue.isEmpty()) {
			Node n1 = queue.poll();
			int x1 = n1.x;
			int y1 = n1.y;
			//1번 동전 위치 : x1,y1
			
			Node n2 = queue.poll();
			int x2 = n2.x;
			int y2 = n2.y;
			//2번 동전 위치  : x2,y2
			
			
			int cnt = n1.cnt;
			if(cnt >= 10 ) {
				//cnt1과 cnt2는 같다. (동시에 움직인다)
				System.out.println(-1);
				return;
			}
			
			
			for (int d = 0; d < 4; d++) {
				int nx1 = x1+dx[d];
				int ny1 = y1+dy[d];
				
				int nx2 = x2+dx[d];
				int ny2 = y2+dy[d];
				
				
				//동전이 맵에서 떨어지는지 체크한다.
				int outCnt = 0;
				
				if(nx1<0||nx1>=N||ny1<0||ny1>=M) outCnt++;
				if(nx2<0||nx2>=N||ny2<0||ny2>=M) outCnt++;
				
				if(outCnt==1) {
					//두 동전 중 하나만 보드에서 떨어트리면 종료
					//cnt 출력
					System.out.println(cnt+1);
					return;
				}
				
				if(outCnt==2) continue;
				//두개의 동전 모두 떨어지면 안됨 => continue
				
				
				//두 동전 다 떨어지지 않았다면 (이동한다)
				
				
				//동시에 이동시켜야한다
				if(visited[nx1][ny1][nx2][ny2]) continue;
				visited[nx1][ny1][nx2][ny2] = true;
				if(arr[nx1][ny1]!=-1&&arr[nx2][ny2]!=-1) {
					//두 동전의 다음 위치가 모두 벽이 아니면
					//둘다 한칸씩 이동한다.
					arr[x1][y1]=0;
					arr[x2][y2]=0;
					arr[nx1][ny1]=1;
					arr[nx2][ny2]=1;
					queue.add(new Node(nx1,ny1,cnt+1));
					queue.add(new Node(nx2,ny2,cnt+1));
				}else if(arr[nx1][ny1]!=-1&&arr[nx2][ny2]==-1) {
					//1번 동전의 다음 위치는 벽이 아니고,
					//2번 동전의 다음 위치는 벽이면
					//1번 동전만 이동한다
					arr[x1][y1]=0;
					arr[nx1][ny1]=1;
					queue.add(new Node(nx1,ny1,cnt+1));
					queue.add(new Node(x2,y2,cnt+1));
				}else if(arr[nx1][ny1]==-1&&arr[nx2][ny2]!=-1) {
					//1번 동전의 다음 위치가 벽이고
					//2번 동전의 다음 위치는 벽이 아니면
					//2번 동전만 이동한다.
					arr[x2][y2]=0;
					arr[nx2][ny2]=1;
					queue.add(new Node(x1,y1,cnt+1));
					queue.add(new Node(nx2,ny2,cnt+1));
				}
				
				
				
			}
		}
		
		System.out.println(-1);
		return;
	}
	
	static class Node{
		int x,y,cnt;

		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
	}
}
