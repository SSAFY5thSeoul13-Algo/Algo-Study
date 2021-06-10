package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_다리만들기2_17472 {

	/* map의 가로세로 */
	static int N,M;
	/* Infinity */
	static int INF = 11;
	/* 세계를 담을 배열  */
	static int[][] map;
	/* 각 나라에서 다른나라로 연결할 수 있는 다리의 길이저장 */
	static int[][] dist;
	
	static int ans = -1;
	/* 나라 List */
	static List<Country> countries = new ArrayList<>();
	/* 존재하는 나라수 */
	static int countryNum = 0;
	/* delta array */
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findCountry();	 
		findLength();
		
		System.out.println(prim());
	}
	
	/* map에서 나라찾기 */
	static void findCountry() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					countries.add(new Country());
					countries.get(countryNum).list.add(new Point(i,j));
					// 0:바다, 1:땅 따라서 2번부터 나라
					map[i][j] = countryNum + 2;
					// 각 나라별 땅찾기
					setIslandNum(i,j);
					countryNum++;
				}
			}
		}
	}
	
	/* 각 나라별 연결될 다리길이 구하기 */
	static void findLength() {
		dist = new int[countryNum][countryNum];
		// 다리길이 무한대로 초기화
		for(int i=0; i<countryNum; i++) {
			for(int j=0; j<countryNum; j++) {
				dist[i][j] = INF;
			}
		}
		// 각 나라별 모든 땅을 4방탐색하면서 다른나라와이 거리구하기
		for(int i=0; i<countryNum; i++) {
			for(Point p : countries.get(i).list) {
				setLength(p.y, p.x);
			}
		}
	}
	
	/* 한나라에서 다른나라 사이의 거리구하기 */
	static void setLength(int y, int x) {
		int start = map[y][x] - 2;
		for(int dir=0; dir<4; dir++) {
			
			int zy = y;
			int zx = x;
			int length = 0;
			while(true) {
				
				zx += dx[dir];
				zy += dy[dir];
				
				if(zy<0 || zx<0 || zy>=N || zx>=M) break;
				if(map[zy][zx] != 0) {
					if(length > 1) {
						int dest = map[zy][zx] - 2;
						
						if(dist[start][dest] > length) {
							dist[start][dest] = dist[dest][start] = length;
						}
					}
					break;
				}
				
				length++;
			}
			
		}
		
	}
	
	static int prim() {
		int[] minDistArr = new int[countryNum];
		boolean[] select = new boolean[countryNum];
		for(int i=0; i<countryNum; i++) {
			minDistArr[i] = INF;
		}
		minDistArr[0] = 0;	// 1번째 나라 선택을 위한 초석
		
		int ret = 0;
		// 각 나라별로 순회
		for(int i=0; i<countryNum; i++) {
			int now = -1, minDist = INF;
			
			// 그래프에서 가장 짧은 간선을 가진 노드선택
			for(int j=0; j<countryNum; j++) {
				if(!select[j] && minDist > minDistArr[j]) {
					minDist = minDistArr[j];
					now = j;
				}
			}
			// 가장 짧은게 없다면 그 그래프는 연결되어 있지 않음
			if(now < 0) return -1;
			
			// 가중치 증가
			ret += minDist;
			// 선택완료
			select[now] = true;

			// 노드선택에 따른 minDistArr 갱신
			for(int j=0; j<countryNum; j++) {
				minDistArr[j] = Math.min(minDistArr[j], dist[j][now]);
			}
		}
		return ret;
	}
	
	// x, y가 어떤나라의 땅인지 정하기 (DFS)
	static void setIslandNum(int y, int x) {
		for(int z=0; z<4; z++) {
			int zx = x + dx[z];
			int zy = y + dy[z];
			
			if(zy<0 || zx<0 || zy>=N || zx>=M) continue;
			if(map[zy][zx] != 1) continue;
			
			countries.get(countryNum).list.add(new Point(zy,zx));
			map[zy][zx] = countryNum + 2;
			setIslandNum(zy,zx);
		}
	}
	
	static class Country{
		public List<Point> list = new ArrayList<>();
		
	}
	static class Point{
		int y, x;
		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "(" + y + "," + x + ")";
		}
		
	}

}
