package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 문제명 : 성곽
 * 번호 : 2234
 * 난이도 : 골4
 * 풀이시간 : 1시간 30분
 * 사용 알고리즘 : BFS
 */
public class boj_성곽_2234 {

	/* 공간의 가로, 세로길이 */
	static int N, M;
	/* 벽, 방번호*/
	static int[][] wall, room;
	/* BFS 방문관리 */
	static boolean[][] vis;
	
	/* 방의 수, 가장 넓은 방의 넓이, 벽하나를 뚫었을때 열 수 있는 가장 넓의 방의 크기 */
	static int rNum = 0, rMax = 0, linkMax = 0;
	/* 각방의 넓이리스트 */
	static List<Integer> widthList = new ArrayList<>();
	
	/* 0:좌 , 1:상, 2:우, 3:하 */
	static int[] dy= {0,-1,0,1};
	static int[] dx = {-1,0,1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		wall = new int[M][N];
		room = new int[M][N];
		vis = new boolean[M][N];
		
		/* 공간의 벽을 설정 */
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				wall[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/* 각 방의 존재와 각 방의 크기를 찾자 */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// 방문했음 == 이미 방번호가 지정됨
				if(vis[i][j]) continue;
				/* 방의 넓이를 찾기 */
				int width = findRoom(i,j);
				// 넓이추가
				widthList.add(width);
				rNum++;	// 방 갯수 증가
				
				/* 가장 넓은 방 찾기 */
				rMax = Math.max(rMax, width);
			}
		}
		
		/* 각방의 연결성을 찾는다. */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int one = room[i][j];
				/* 모든점에서 좌, 상만 비교한다. */
				for (int dir = 0; dir < 2; dir++) {
					int zy = i + dy[dir];
					int zx = j + dx[dir];
					
					if(zy<0 || zx<0 || zy>=M || zx>=N) continue;
					
					/* 방번호가 다르다 == 벽뚫으면 연결되어 있음 */
					if(one != room[zy][zx]) {
						/* 벽 뚫고 최고넓이 구하기 */
						linkMax = Math.max(linkMax,widthList.get(one) + widthList.get(room[zy][zx]));
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(rNum + "\n");
		sb.append(rMax + "\n");
		sb.append(linkMax);
		System.out.println(sb.toString());
	}
	
	/* 기준점 (i,j)와 같은 방 찾기 : BFS */
	private static int findRoom(int i, int j) {
		
		Queue<Integer> q = new LinkedList<>();
		
		vis[i][j] = true;
		room[i][j] = rNum;
		q.offer(i);
		q.offer(j);
		int width = 1;
		while(!q.isEmpty()) {
			int y = q.poll();
			int x = q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				if((wall[y][x] & 1<<dir) != 0) continue;
				
				int zy = y + dy[dir];
				int zx = x + dx[dir];
			
				if(zy<0 || zx<0 || zy>=M || zx>=N) continue;
				if(vis[zy][zx]) continue;
				
				vis[zy][zx] = true;
				room[zy][zx] = rNum;
				q.offer(zy);
				q.offer(zx);
				width++;
			}
			
		}
		return width;
	}
}
