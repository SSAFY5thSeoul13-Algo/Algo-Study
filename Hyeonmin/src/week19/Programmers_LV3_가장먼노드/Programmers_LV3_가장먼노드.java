package week19.Programmers_LV3_가장먼노드;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_LV3_가장먼노드 {
	static boolean[][] map;
	static boolean[] visited;
	
	public static void main(String[] args) {
		int n = 6;
		int[][] edge = {
				{3,6},
				{4,3},
				{3,2},
				{1,3},
				{1,2},
				{2,4},
				{5,2}
		};
		
		int result = solution(n, edge);
		
		System.out.println(result);
	}
	
	static int solution(int n, int[][] edge) {
		//각 노드의 연결 여부를 저장할 2차원 배열
		map = new boolean[n+1][n+1];
		//bfs에서 방문 여부를 저장할 2차원 배열
		visited = new boolean[n+1];
		
		//edge에 저장된 간선 정보로 map배열에 노드간의 연결 표시
		for (int i = 0; i < edge.length; i++) {
			int from = edge[i][0];
			int to = edge[i][1];
			
			map[from][to] = true;
			map[to][from] = true;
		}
		
		return bfs();
	}
	
	//1번 노드에서 가장 멀리 떨어진 노드의 수를 리턴하는 메소드
	static int bfs() {
		//bfs실행을 위해 노드의 번호를 저장할 큐
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//1번 노드를 큐에 저장
		queue.offer(1);
		//1번 노드 방문 체크
		visited[1] = true;
		
		//현재 1번 노드에서 가장 멀리 떨어져 있는 노드의 수
		int count = 0;
		
		while(!queue.isEmpty()) {
			//현재 큐에 들어있는 노드들은 1부터의 거리가 같은 노드들 이므로 이떄의 큐 사이즈가 1부터 가장 멀리있는 노드들의 수이다
			count = queue.size();
			
			for (int i = 0; i < count; i++) {
				//큐에 있는 노드 번호
				int nodeNum = queue.poll();
				
				for (int j = 1; j < map.length; j++) {
					//자기 자신이거나 연결이 안된 노드인 경우는 스킵
					if(nodeNum == j || !map[nodeNum][j])	continue;
					//이미 방문한 노드인 경우는 스킵
					if(visited[j]) continue; 
					
					//방문 체크
					visited[j] = true;
					
					//큐에 저장
					queue.offer(j);
				}
			}
		}
		
		return count;
	}
}
