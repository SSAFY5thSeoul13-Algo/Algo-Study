package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_스타트링크_5014 {
	// 건물높이, 시작위치, 목표위치, Up, Down
	static int F,S,G,U,D;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		boolean[] vis = new boolean[F+1];
		
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(S);
		vis[S] = true;
		
		int time = 0;
	
		// 이미 도착했다면
		if(S==G) {
			System.out.println(time);
			return;
		}
		
		while(!q.isEmpty()) {
			
			time++;
			
			int size = q.size();
			while(size-- > 0) {
				int cur = q.poll();
				
				// 위로 갈수잇다면
				int up = cur + U;
				if(up <=F && !vis[up]) {
					q.offer(up);
					vis[up] = true;
				}
				
				// 아래로 갈수 있다면
				int down = cur - D;
				if( 0 < down  && !vis[down]) {
					q.offer(down);
					vis[down] = true;
				}
				// 도착했니?
				if(vis[G]) {
					System.out.println(time);
					return;
				}
			}
			
			
		}
		
		System.out.println("use the stairs");
	}

}
