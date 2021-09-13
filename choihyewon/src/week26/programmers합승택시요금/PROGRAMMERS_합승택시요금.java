package week26.programmers합승택시요금;

import java.util.*;

public class PROGRAMMERS_합승택시요금 {
	static final int INF = 100_000_000;
    static List<Node>[] list;
    static class Node implements Comparable<Node>{
        int end;
        int weight;
        public Node(int end,int weight){
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;;
        list = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<fares.length; i++){
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];
            
            list[c].add(new Node(d,f));
            list[d].add(new Node(c,f));
        }
        
        int[] disS = dijkstra(s,n);
        int[] disA = dijkstra(a,n);
        int[] disB = dijkstra(b,n);
        
        for(int i=1; i<=n; i++){
            answer = Math.min(answer,disS[i]+disA[i]+disB[i]);
        }
        
        return answer;
    }
    public static int[] dijkstra(int start, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist,INF);
        boolean[] visited = new boolean[n+1];
        pq.add(new Node(start,0));
        dist[start] = 0;
        
        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.end;
            if(visited[cur])    continue;
            
            visited[cur] = true;
            
            for(Node node : list[cur]){
                if(dist[node.end] > dist[cur] + node.weight){
                    dist[node.end] = dist[cur] + node.weight;
                    pq.add(new Node(node.end,dist[node.end]));
                }
            }
        }
        return dist;
    }

}
