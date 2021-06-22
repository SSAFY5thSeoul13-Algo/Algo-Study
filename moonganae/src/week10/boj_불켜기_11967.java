package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_불켜기_11967 {

	/* 방의 길이, 스위치 수 */
	static int N, M;
	/* 방에 설정된 스위치저장 */
	static List<Integer>[][] roomSwitch;
	
	/* BFS 방문체크, 불이켜져있는 방인지 확인 */
	static boolean[][] vis, light;
	/* 불켜진 방의 수 */
	static int lightCnt = 0;
	/* delta array */
	static int dy[] = {1,-1,0,0};
	static int dx[] = {0,0,1,-1};
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		roomSwitch = new ArrayList[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				roomSwitch[i][j] = new ArrayList<Integer>();
			}
		}
		
		vis = new boolean[N+1][N+1];
		light = new boolean[N+1][N+1];
		
		/* 각방의 스위치 연결 */
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			roomSwitch[y][x].add(b);
			roomSwitch[y][x].add(a);	
		}
		
		bfs();
		System.out.println(lightCnt);
		
	}
	static void bfs() {
		
		Queue<Integer> q = new LinkedList<>();
		
		/* (1,1)번방 연결 */
		q.offer(1);
		q.offer(1);
		
		vis[1][1] = true;
		light[1][1] = true;
		lightCnt = 1;
		while(!q.isEmpty()) {
			
			int y = q.poll();
			int x = q.poll();

			/* (x,y)에 있는 스위치를 조작해 연결된 방의 불을 켠다 */
			for(int i=0; i<roomSwitch[y][x].size(); i+=2) {
				int ly = roomSwitch[y][x].get(i);
				int lx = roomSwitch[y][x].get(i+1);
				
				/* 막힌점 1 : 이미켜진걸 체크하지 않았음. */
				/* 이미 켜져있다면 넘어가라 */
				if(light[ly][lx]) continue;
				
				/* 방의 불을 켬 */
				lightCnt++;
				light[ly][lx] = true;
				
				/* 상하좌우를 확인하여 현재 불켜진방에 갈 수 있는지 확인 */
				for(int z=0; z<4; z++) {
					int zy = ly + dy[z];
					int zx = lx + dx[z];
					if(zx<1 || zy<1 || zx>N || zy>N) continue;
					
					/* 근처에 방문을 한 흔적이 있는경우는 이방도 방문할 수 있다. -> queue에 삽입 */
					if(vis[zy][zx]) {
						
						q.offer(ly);
						q.offer(lx);
						vis[ly][lx] = true;
						break;
					}
				}
			}
			
			/* Main BFS */
			for(int z=0; z<4; z++) {
				int zy = y + dy[z];
				int zx = x + dx[z];
				
				/* 범위내이며, 불이켜져있고, 방문을 하지 않은 방이라면 */
				if(zx<1 || zy<1 || zx>N || zy>N) continue;
				if(!light[zy][zx]) continue;
				if(vis[zy][zx]) continue;
				
				/* 가보자~ */
				vis[zy][zx] = true;
				/* 막힌점 2 : queue에 안넣었따... */
				q.offer(zy);
				q.offer(zx);
			}
		}
	}
}