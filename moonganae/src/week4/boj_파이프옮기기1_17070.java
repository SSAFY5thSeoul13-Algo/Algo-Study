package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_파이프옮기기1_17070 {

	static int N, ans;
	static int RIGHT = 0, DOWN = 1, DIAG = 2;
	static int[][] map;
	
	static List<ArrayList<Integer>> delta = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st; 
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		DFS(0,1,RIGHT);
		
		System.out.println(ans);
		

	}
	static void DFS(int y, int x, int dir) {
		
		if(isPromise(y, x, dir) == false) {
			return;
		} else if(y==N-1 && x==N-1) {
			ans++;
			return;
		}
		
		
		if(dir == RIGHT) {
			// RIGHT
			DFS(y, x+1, RIGHT);
		} else if(dir == DOWN) {
			// DOWN
			DFS(y+1, x, DOWN);
		} else {
			// RIGHT + DOWN
			DFS(y, x+1, RIGHT);
			DFS(y+1, x, DOWN);
		}
		
		// DIAG
		DFS(y+1, x+1, DIAG);
	}
	static boolean isPromise(int y, int x, int dir) {
		
		if(y<0 || x<0 || y>=N || x>=N) return false;
		if(map[y][x] == 1) return false;
		if(dir==DIAG && (map[y-1][x] == 1 || map[y][x-1] == 1)) return false;
		
		return true;
	}
}
