package week19.PROGRAMMERS_LV3_가장먼노드;

import java.util.*;
class Solution {
    List<Integer>[] graph;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        graph = new ArrayList[n+1];
        
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<Integer>();
        }
        /* 그래프 연결 */
        for(int i=0; i<edge.length; i++){ 
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        
        List<Integer> sol = bfs(n);
        answer = sol.size();
        
        return answer;
    }
    /* BFS 탐색 */
    public List<Integer> bfs(int n){
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n+1];
        
        q.offer(1);
        vis[1] = true;
        
        List<Integer> leaf = new ArrayList<>();
        int cnt = 1;
        while(!q.isEmpty()){
            
            int size = q.size();
            
            while(size-- > 0){
                int cur = q.poll();
                
                for(int num : graph[cur]) {
                	if(!vis[num]) {
                        q.offer(num);
                        leaf.add(num);
                        vis[num] = true;
                        cnt++;
                	}
                    
                }
            }
            // 모든 노드를 방문하지 않았다면
            if(cnt != n){
                // 리프노드가 아니다.
                leaf.clear();
            }else{
                break;
            }
        }
        
        return leaf;
    }
}