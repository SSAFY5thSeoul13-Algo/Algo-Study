package week32.boj1956;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1956_G4_운동 {
	static final int INF = 9999999;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] matrix = new int[V+1][V+1];
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i != j)	matrix[i][j] = INF;
			}
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			matrix[start][end] = len;
		}
		
		
		for(int j=1; j<=V; j++) {
			for(int i=1; i<=V; i++) {
				// 경유지와 출발지가 같으면 continue 
				if(j==i)	continue;
				for(int k=1; k<=V; k++) {
					if(j==k || i==k)	continue;
					// 출발지 - 도착지 거리와 출발지 - 경유지 - 도착지 거리를 비교하여 최소값으로 갱신 
					if(matrix[i][k] > matrix[i][j] + matrix[j][k]) {
						matrix[i][k] = matrix[i][j] + matrix[j][k];
						//System.out.println("hgg" + matrix[i][k] + " " +  matrix[i][j] + " " +  matrix[j][k]);
					}
				}

			}
		}
		
	
		
		int answer = Integer.MAX_VALUE;
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j)	continue;
				if(matrix[i][j]!=INF && matrix[j][i]!=INF) {
					int sum = matrix[i][j] + matrix[j][i];
					answer = Math.min(answer, sum);
				}
			}
		}
		
		if(answer != Integer.MAX_VALUE) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}
		

	}

}
