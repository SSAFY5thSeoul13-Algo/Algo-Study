package week19.graph2;

import java.util.*;

public class 순위 {
	static final int INF = 9999999;
	static int[][] matrix;
	public int solution(int n, int[][] results) {
		int answer = 0;
        // 인접행렬 
        matrix = new int[n+1][n+1];
        // INF로 행렬을 다 채운다. (인접하지 않으면 INF)
        for (int[] arr : matrix) {
        	Arrays.fill(arr, INF);
		}
        // 인접한 행렬은 1로 채우기! 승패가 있으므로 단뱡향 그래프 
        for (int[] arr : results) {
			matrix[arr[0]][arr[1]] = 1;
		}
        // 경유지 
        for(int k=1; k<=n; k++) {
        	// 출발지 
        	for(int i=1; i<=n; i++) {
        		// 출발지와 경유지가 같다면 다음 출발지 
        		if(i==k)	continue;
        		// 도착지 
        		for(int j=1; j<=n; j++) {
        			// 도착지가 경유지나 출발지와 같다면 continue
        			if(j==k || j==i)	continue;
        			if(matrix[i][j]>matrix[i][k] + matrix[k][j]) {
        				matrix[i][j] = matrix[i][k] + matrix[k][j];
        			}
        		}
        	}
        }
        
        
        for(int i=1; i<=n; i++) {
        	boolean check = true;
        	for(int j=1; j<=n; j++) {
        		// 자기 자신이 아닌 경우 
        		if(i==j)	continue;
        		// 어느 한쪽이라도 연결이 안되어있는 경우 false 
        		if(matrix[i][j]==INF && matrix[j][i]==INF) {
        			check = false;
        		}
        	}
        	if(check) {
        		answer++;
        	}
        }
        

        return answer;
        
	      
    }

}
