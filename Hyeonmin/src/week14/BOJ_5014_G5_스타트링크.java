package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5014_G5_스타트링크 {
	static int F,S,G,U,D, result = -1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		bfs();
		
		//해당 층에 도착하지 못하면 use the stairs 출력
		System.out.println(result == -1 ? "use the stairs" : result);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		
		//시작하는 층과 버튼을 누른 횟수를 0으로 큐에 넣음
		q.offer(new int[] {S, 0});
		
		boolean[] visited = new boolean[F+1];
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			//층을 벗어난 이동인 경우 제외
			if(p[0] < 1 || p[0] > F)
				continue;
			
			//이미 방문한 층인 경우 제외
			if(visited[p[0]])
				continue;
			
			//목표하는 층에 도착하면 해당 층에 도착할 때 까지 누른 버튼 수를 result에 저장하고 종료
			if(p[0] == G) {
				result = p[1];
				break;
			}
			
			//방문 체크
			visited[p[0]] = true;
			
			//해당 층에서 버튼을 눌러서 갈 수 있는 2곳을 큐에 넣음
			q.offer(new int[] {p[0]+U,p[1]+1});
			q.offer(new int[] {p[0]-D,p[1]+1});
			
		}
	}
}
