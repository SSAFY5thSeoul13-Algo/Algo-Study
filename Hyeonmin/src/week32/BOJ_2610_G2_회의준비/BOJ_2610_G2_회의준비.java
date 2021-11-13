package week32.BOJ_2610_G2_회의준비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2610_G2_회의준비 {
	static int[][] map;
	static int N, M;
	static final int INF = Integer.MAX_VALUE/2;
	static boolean[] isVisited;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		isVisited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from][to] = map[to][from] = 1;
		}
		
		floydWashall();
		
		//아직 위원회에 속하지 않은 사람의 위원회 리더 선출
		for (int i = 1; i <= N; i++) {
			if(isVisited[i])	continue;
			
			selectLeader(i);
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(list.size()).append("\n");
		
		for (int num : list) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void floydWashall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j)	continue;
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
	}
	
	static void selectLeader(int idx) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(idx);
		
		//같은 위원회 사람을 큐에 저장
		for (int i = 1; i <= N; i++) {
			if(map[idx][i] != INF) {
				q.offer(i);
			}
		}
		
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		
		while(!q.isEmpty()) {
			int num = q.poll();
			
			isVisited[num] = true;
			
			int temp = 0;
			
			//해당 위원의 가장 큰 의사결정 거리 구하기
			for (int i = 1; i <= N; i++) {
				if(map[num][i] != INF) {
					temp = Math.max(temp, map[num][i]);
				}
			}
			
			if(min > temp) {
				min = temp;
				minIdx = num;
			}
			
		}
		
		list.add(minIdx);
	}
}