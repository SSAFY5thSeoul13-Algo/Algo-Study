package week19.graph1;

import java.util.*;

public class 가장_먼_노드 {
    static ArrayList<ArrayList<Integer>> list;
    static int[] visited;
	class Solution {
	    public int solution(int n, int[][] edge) {
	        int answer = 0;
	        // 인접 리스트 
	        list = new ArrayList<ArrayList<Integer>>();
	        visited = new int[n+1];
	        
	        for(int i=0; i<=n; i++) {
	        	list.add(new ArrayList<Integer>());
	        }
	        
	        for(int i=0; i<edge.length; i++) {
	        	list.get(edge[i][0]).add(edge[i][1]);
	        	list.get(edge[i][1]).add(edge[i][0]);
	        }
	        
	        answer = bfs(n);
	        
	        return answer;
	    }

		private int bfs(int n) {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(1);
			visited[1] = 1;
			int max = 0;
			while(!queue.isEmpty()) {
				int num = queue.poll();
				for (Integer i : list.get(num)) {
					if(visited[i]==0) {
						visited[i] = visited[num]+1;
						queue.add(i);
						max = Math.max(max, visited[i]);
					}
				}

			}
			int cnt = 0;
			for(int i=1; i<=n; i++) {
				if(visited[i]==max) {
					cnt++;
				}
			}
			
			return cnt;
			
		}
	}

}
