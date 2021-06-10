package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17472_G3_다리만들기2 {
	static int N, M;
	static int[][] map;
	//섬에 번호를 부여할때 사용할 배열
	static boolean[][] isChanged; 
	//상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	//결과
	static int result = 0;
	//섬의 개수
	static int islandCnt = 0;
	//크루스칼 알고리즘에서 root를 저장하는 배열
	static int parents[];
	
	static List<Bridge> bridgeList = new ArrayList<Bridge>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N =Integer.parseInt(st.nextToken());
		M =Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isChanged = new boolean[N][M];
		
		//나라 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//부여할 섬의 번호
		int inum =1;
		
		//전체를 순회하면서 섬의 번호를 부여
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//섬에 해당하는 위치를 만났을 때. 번호를 부여하지 않은 섬이면 번호 부여
				if(map[i][j] == 1 && map[i][j] <=inum && !isChanged[i][j]) {
					changeIsland(i, j, inum++);
				}
			}
		}
		
		islandCnt = inum-1;
		
		//map을 하나씩 돌면서 다리를 짓기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					buildBridge(i, j);
				}
			}
		}
		
		//완성된 다리를 길이순으로 정렬
		Collections.sort(bridgeList, (o1, o2) ->  o1.length > o2.length ? 1 : -1);
		
		/** 크루스칼 알고리즘 **/
		//선택한 다리의 갯수
		int count = 0;
		parents = new int[inum];
		
		for(int i=0; i < parents.length; i++) {
            parents[i] = i;
        }
		
		//리스트에 저장한 다리의 양 끝을 노드로, 다리의 길이를 간선으로 취급해서 크루스칼 알고리즘을 사용 
		for (Bridge bridge : bridgeList) {            
            int a = find(bridge.island1);
            int b = find(bridge.island2);
            
            if(a==b) continue;
            
            union(bridge.island1, bridge.island2);
            result += bridge.length;
            count++;
		}
		
		//다리가 없거나 모든 섬을 연결하지 못한 경우 -1 출력
		if(result == 0 || count != islandCnt-1) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
		
	}
	
	//해당 좌표에서 다른섬까지의 다리를 만드는 메소드
	static void buildBridge(int y, int x) {
		//4방향으로 다리 설치 시도
		for (int i = 0; i < 4; i++) {
			int temp = 0;
			//새로 건설할 다리의 길이
			int len=0;
			//다리를 건설할 섬의 번호
			int endNum = 0; 
			
			int ny = y;
			int nx = x;
			
			//다음 위치에 갈 수 있을 동안 반복 
			while(canGo(ny, nx, i)) {
				ny += dy[i];
				nx += dx[i];
				//해당 위치가 0이 아니면 섬이기 떄문에 현재까지의 길이와 현재 위치의 섬의 번호를 저장하고 반복 종료
				if(map[ny][nx] != 0) {
					len=temp;
					endNum = map[ny][nx];
					break;
				}
				
				temp++;
			}
			
			//다리 길이가 1보다 크면 bridge 클래스 생성후 리스트에 저장
			if(len > 1) {
				bridgeList.add(new Bridge(map[y][x], endNum, len));
			}
		}
	}
	
	//섬의 번호를 부여하는 메소드. inum : 섬에 부여할 번호
	static void changeIsland(int y, int x, int inum) {
		map[y][x] = inum;
		isChanged[y][x] = true;
		
		//현재 위치에서 4방향을 탐색
		for (int i = 0; i < 4; i++) {
			if(canGo(y, x, i)) {
				int ny = y +dy[i];
				int nx = x +dx[i];
				//탐색하는 곳이 아직 번호가 변경되지 않은 곳이면 변경
				if(map[ny][nx] == 1 && !isChanged[ny][nx]) {
					map[ny][nx] = inum;
					changeIsland(ny, nx, inum);
				}
			}
		}
	}
	//탐색 가능한 범위인지 확인하는 메소드
	static boolean canGo(int y, int x, int dir) {
		int ny = y +dy[dir];
		int nx = x +dx[dir];
		if(ny < 0 || ny >= N || nx < 0 || nx >= M)
			return false;
		
		return true;
	}
	
	//크루스칼 알고리즘을 위한 메소드들
	public static int find(int a) {
        if(a == parents[a]) return a;
        parents[a] = find(parents[a]);
        return parents[a];
    }
    
    public static void union(int s,int e) {
        int aRoot = find(s);
        int bRoot = find(e);
        
        if(aRoot != bRoot) {
            parents[aRoot] = e;
        } else {
            return;
        }
    }
	
    //다리 정보를 저장하는 클래스
	static class Bridge{
		//연결된 섬의 번호
		int island1;
		int island2;
		//다리의 길이
		int length;
		
		public Bridge(int island1, int island2, int length) {
			super();
			this.island1 = island1;
			this.island2 = island2;
			this.length = length;
		}
	}
}
