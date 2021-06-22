package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_5014_G5_스타트링크 {

	//1층~F층
	//스타트링크 위치는  G층
	//강호가 지금 있는 곳은 S층이고 엘리베이터를 타고 G층으로 이동한다
	//U 혹은 D버튼
	//G층에 도착하려면 버튼을 몇번눌러야되는가
	
	//F, S, G, U, D
	//10 1  10 2  1
	//- 결과
	//59824	160
	
	static int F, S, G, U, D;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		bfs();
	}
	
	
	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[F+1];
		
		queue.add(new Node(S,0)); //현재 위치 S층
		visited[S]=true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			int floor = n.floor;
			int cnt = n.cnt;
			
			if(floor==G){ //G층이면
				System.out.println(cnt);
				return;
			}
			
			if(floor+U<=F&&!visited[floor+U]) {
				visited[floor+U]=true;
				queue.add(new Node(floor+U,cnt+1));
			}
			
			if(floor-D>0&&!visited[floor-D]) {
				visited[floor-D]=true;
				queue.add(new Node(floor-D,cnt+1));
			}
			
		}
		
		System.out.println("use the stairs");
	}
	
	static class Node{
		int floor,cnt;

		public Node(int floor, int cnt) {
			super();
			this.floor = floor;
			this.cnt = cnt;
		}
		
		
	}
	
	
}
