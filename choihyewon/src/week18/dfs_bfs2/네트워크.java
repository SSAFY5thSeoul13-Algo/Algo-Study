package week18.dfs_bfs2;


public class 네트워크 {
	public int solution(int n, int[][] computers) {
        int answer = 0;
        // 연결이 되었는지 확인하기 위해 2차원 boolean 배열 생성 
        boolean[] visited = new boolean[n];
        
        for(int i=0; i<n; i++) {
        	if(!visited[i]) {
        		answer++;
        		dfs(n,i,visited,computers);
        	}
        }
        return answer;
    }
	static void dfs(int n,int index, boolean[] visited,int[][] computers) {
		// 방문 확인 
		visited[index] = true;
		
		for(int i=0; i<n; i++) {
			// 연결이 되어있다면 재귀 호출 
			if(computers[index][i]==1 && computers[i][index]==1 && !visited[i]) {
				dfs(n,i,visited,computers);
			}
		}
	}

}
