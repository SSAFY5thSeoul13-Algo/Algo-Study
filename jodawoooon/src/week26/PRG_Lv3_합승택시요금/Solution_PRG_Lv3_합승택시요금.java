package week26.PRG_Lv3_합승택시요금;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : Programmers 합승택시요금
 * @Author : Daun JO
 * @Date : 2021. 9. 10. 
 * @Algorithm : 플로이드 워셜
 *
 */
public class Solution_PRG_Lv3_합승택시요금 {
	 static int INF = 200000000;
	    public int solution(int n, int s, int a, int b, int[][] fares) {
	        int answer = INF;
	        
	        int map[][] = new int[n+1][n+1];
	        
	        for(int i = 1 ; i <= n ; i++){
	            for(int j=1; j<=n; j++) {
	                if(i!=j) map[i][j] = INF;
	            }
	        }
	        
	        
	        for(int[] fare : fares){
	            int c = fare[0];
	            int d = fare[1];
	            int f = fare[2];
	            
	            map[c][d] = map[d][c] = f; //c지점과 d지점 사이의 예상 택시요금이 f원
	        }
	        
	        for (int k = 1; k <= n; k++) {
				for (int i = 1; i <= n; i++) {
					if(map[i][k]>INF) continue;
					
					if(i==k) continue;
					for (int j = 1; j <= n; j++) {
						if(map[k][j]>INF) continue;
						if(i==j||j==k) continue;
						
						map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					}
				}
			}
	        
	        for(int i=1; i<=n; i++){
	            answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
	        }
	        return answer;
	    }
}
