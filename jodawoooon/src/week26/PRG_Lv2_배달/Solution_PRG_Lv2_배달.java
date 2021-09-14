package week26.PRG_Lv2_배달;

import java.util.*;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : Programmers 배달
 * @Author : Daun JO
 * @Date : 2021. 9. 14. 
 * @Algorithm : 다익스트라
 *
 */
public class Solution_PRG_Lv2_배달 {
	static class Edge implements Comparable<Edge> {
        int v, cost;
        
        public Edge(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
        
        @Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
    }
    
    static int[] cost;
    static int INF = 25000000;
    static ArrayList<Edge>[] edgeList;
    public int solution(int N, int[][] road, int K) {
        int answer = 1; //자기자신 포함

        //1번 마을에 있는 음식점에서 각 마을로 음식 배달
        // 1->각 마을 최단경로가 K이하인 경우 count하면 될듯
        
        cost = new int[N+1];
        edgeList = new ArrayList[N+1];
        for(int i = 1; i <=N ; i++){
            edgeList[i] = new ArrayList<>();
        }
        
        for(int[] r : road){
            int a = r[0];
            int b = r[1];
            int c = r[2];
            
            edgeList[a].add(new Edge(b,c));
            edgeList[b].add(new Edge(a,c));
        }
        
        
        for(int i = 2 ; i <= N ; i++){
            
            for (int j = 1; j <= N; j++) {
			    cost[j] = INF;
		    }
            
            int res = getMinDist(1, i);
            if(res>K) continue;
            answer++;
        }

        return answer;
    }
    
    static int getMinDist(int start, int end){
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        cost[start] = 0;
        pq.add(new Edge(start, 0));
        
        while(!pq.isEmpty()){
            Edge e  = pq.poll();
            int curV = e.v;
            int curCost = e.cost;
            
            if(curCost > cost[curV]) continue;
            
            for(Edge n : edgeList[curV]){
                int nextV = n.v;
                int nextCost = n.cost + cost[curV];
                
                if(cost[nextV] > nextCost){
                    cost[nextV] = nextCost;
                    pq.add(new Edge(nextV, nextCost));
                }
            }
        }
        
        return cost[end];
    }
}
