package week15.BOJ_7568_S5_덩치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_7568_S5_덩치 {
	static class Node{
		int h,w;

		public Node(int h, int w) {
			super();
			this.h = h;
			this.w = w;
		}
		
	}
	static int N;
	static Node[] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
	
		map = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[i] = new Node(x,y); //덩치 (x,y)
		}
		
		//전체 경우의 수를 비교
		//완탐
		for (int i = 0; i < N; i++) {
			int rank = 1;

			for (int j = 0; j < N; j++) {
				if(i==j) continue;
				
				if(map[i].h < map[j].h && map[i].w  < map[j].w) {
					rank++;
				}
			}
			System.out.print(rank+" ");
		}
	}
}
