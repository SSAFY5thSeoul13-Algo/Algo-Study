package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16234_인구이동 {
	/*
	 *  1.국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 
	 *  	두 나라가 공유하는 국경선을 오늘 하루동안 연다. => DFs
		2.위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
		3.국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다. =>BFS
		4.연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
		5.연합을 해체하고, 모든 국경선을 닫는다.
	 */
	
/*	while문 안에서 bfs를 이용해서 국경선을 열어 연합국가들을 찾았고, 이 국가들의 인덱스를 list에 저장했습니다.
	모든 파악이 끝나면 list 안에 있는 국가들의 인구수를 연합인구수 (sum) / 연합 칸의 개수 (blockCnt)로 바꾸어주었습니다.

	처음에는 DFS로 풀어보았으나 변수 초기화 문제인지.. 왜인지 모르겠는데 잘 안돼서 BFS로 해결했습니다.
	문제에서 주어진 조건대로 하나하나 구현하였고 얼마전에 풀었던 아기상어 문제랑 비슷한 느낌이라 유사한 방식으로 풀었습니다


	293880	500*/


	static int N, L, R, A[][], sum, blockCnt, ans;
	
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static ArrayList<Point> groupList = new ArrayList<>();
	static boolean visited[][];
	static Queue<Point> q;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //땅넓이
		L = Integer.parseInt(st.nextToken()); //인구차이min
		R = Integer.parseInt(st.nextToken()); //인구차이max
	
		A = new int[N][N];
		visited = new boolean[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//인구 이동은 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
		boolean flag = false;
		loop:
		while(true) {
//			for (int[] ia : A) {
//				System.out.println(Arrays.toString(ia));
//			}
			groupList.clear();
			sum=0;
			blockCnt=0;
			visited = new boolean[N][N];
			q = new LinkedList<>();
			//변수 초기화
			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					groupList.clear();
					sum=0;
					blockCnt=0;
					//변수 초기화
					
					
					bfs(i,j); //연합을 찾는다
					
					int size = groupList.size();
					//연합을 다 지정하면
					if(size>1) {
						//인구이동할게 있으면
						flag = true;
						
						int blockPopulation = sum/blockCnt;
						//(연합의 인구수) / (연합을 이루고 있는 칸의 개수)
						
						for (int k = 0; k < size; k++) {
							Point p = groupList.get(k);
							int x = p.x;
							int y = p.y;
							
							A[x][y] = blockPopulation;
						}
					}
				}

			}
			
			if(flag) { //인구이동했으면
				ans++; //인구이동횟수 증가
				flag = false;
			}else { //인구이동 안했으면
				break; //중지
			}
		}
		
		System.out.println(ans);

	}
	
	private static void bfs(int i, int j) {
		q.add(new Point(i,j));
		
		
		while(true) {
			if(q.isEmpty()) break;
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			
			if(!visited[x][y]) {
				visited[x][y]=true;
				
				blockCnt++; //연합을 이루고 있는 칸의 개수
				sum+=A[x][y]; //연합의 인구수 저장
				groupList.add(new Point(x,y)); //나라의 좌표를 groupList에 담는다.
				
				for (int d = 0; d < 4; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					if(nx>=0&&ny>=0&&nx<N&&ny<N) {
						if(!visited[nx][ny]) { 
							//주어진 조건을 만족하면 q에 넣는다.
							if(Math.abs(A[nx][ny]-A[x][y])>=L&&
									Math.abs(A[nx][ny]-A[x][y])<=R) {
							
								q.add(new Point(nx,ny));
							}
						}
						
					}
				}
			}
		}

	}

		
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
