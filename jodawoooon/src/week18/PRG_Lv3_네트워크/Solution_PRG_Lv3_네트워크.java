package week18.PRG_Lv3_네트워크;

public class Solution_PRG_Lv3_네트워크 {
	static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i = 0 ; i < n ; i++){
            
            if(visited[i]) continue; //방문한 네트워크면 continue
            
            dfs(i,n,computers);
            answer++;
            
        }
        

        return answer;
    }
    
    private static void dfs(int idx, int n, int[][] computers){
        visited[idx] = true; //방문체크
        
        //연결된 노드는 모두 방문
        for(int i=0 ; i<n; i++){
            
            if(i==idx) continue; //자기 자신
            if(visited[i]) continue; //이미 방문한 곳이면 continue
            
            //computers[i][j]=1이면 i번 컴퓨터와 j번 컴퓨터가 연결됨
            if(computers[idx][i]==1) 
                dfs(i,n,computers);
        }
    }
}
