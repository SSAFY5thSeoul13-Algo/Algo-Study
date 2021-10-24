package week31.BOJ_17140_G4_이차원배열과연산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17140_G4_이차원배열과연산 {
	static int N, M, K, time, maxYIdx = 2, maxXIdx = 2;
	static int[][] map = new int[200][200];
	
	static class Node implements Comparable<Node>{
		int idx;
		int num;
		
		public Node(int idx, int num) {
			super();
			this.idx = idx;
			this.num = num;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.num, o.num);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken())-1;
		M = Integer.parseInt(st.nextToken())-1;
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			if(map[N][M] == K)	break;
			
			if(time > 100) break;
			
			if(maxXIdx <= maxYIdx) {
				rowCalc();
			}
			else {
				colCalc();
			}
			
			time++;
		}
		
		System.out.println(time > 100 ? -1 : time);
	}
	
	static void rowCalc() {
		int maxLength = 0;
		
		for (int y = 0; y <= maxYIdx; y++) {
			int[] arr = new int[200];
			List<Node> list = new ArrayList<>();
			
			for (int x = 0; x <= maxXIdx; x++) {
				int num = map[y][x];
				if(num == 0)	continue;
				
				map[y][x] = 0;
				
				arr[num]++;
			}
			
			for (int idx = 1; idx < 200; idx++) {
				if(arr[idx] == 0)	continue;
				
				list.add(new Node(idx, arr[idx]));
			}
			
			Collections.sort(list);
			int size = (list.size()-1)*2 + 1 ;
			
			for (int idx = 0; idx < list.size(); idx++) {
				map[y][idx*2] = list.get(idx).idx;
				map[y][idx*2+1] = list.get(idx).num;
			}
			
			maxLength = Math.max(maxLength, size);
		}
		
		maxXIdx = maxLength;
	}
	
	static void colCalc() {
		int maxLength = 0;
		
		for (int x = 0; x <= maxXIdx; x++) {
			int[] arr = new int[200];
			List<Node> list = new ArrayList<>();
			for (int y = 0; y <= maxYIdx; y++) {
				int num = map[y][x];
				if(num == 0)	continue;
				
				map[y][x] = 0;
				
				arr[num]++;
			}
			
			for (int idx = 1; idx < 200; idx++) {
				if(arr[idx] == 0)	continue;
				
				list.add(new Node(idx, arr[idx]));
			}
			
			Collections.sort(list);
			int size = (list.size()-1)*2+1;
			
			for (int idx = 0; idx < list.size(); idx++) {
				map[idx*2][x] = list.get(idx).idx;
				map[idx*2+1][x] = list.get(idx).num;
			}
			
			maxLength = Math.max(maxLength, size);
		}
		maxYIdx = maxLength;
	}
}
