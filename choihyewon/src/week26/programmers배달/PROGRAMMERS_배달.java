package week26.programmers배달;

import java.util.*;

public class PROGRAMMERS_배달 {
	static List<Node>[] list;
    static int[] dist;
    static boolean[] visited;
    static final int INF = 100_000_000;
    static class Node implements Comparable<Node>{
        int end;
        int dis;
        public Node(int end, int dis){
            this.end = end;
            this.dis = dis;
        }
        @Override
        public int compareTo(Node o){
            return this.dis - o.dis;
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        list = new ArrayList[N+1];
        dist = new int[N+1];
        visited = new boolean[N+1];
        
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        
        Arrays.fill(dist,INF);
        
        for(int i=0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            // 양방향으로 통행 가능 
            list[a].add(new Node(b,c));
            list[b].add(new Node(a,c));
        }
        
        dijkstra();
        
        for(int i=1; i<=N; i++){
            if(dist[i]>K)   continue;
            answer++;
        }
        return answer;
    }
    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 1번 마을에 음식점이 있어서 1번마을에서 배달 시작 
        pq.add(new Node(1,0));
        dist[1] = 0;
        
        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int current = curNode.end;
            if(visited[current])    continue;
            // 방문처리
            visited[current] = true;
            
            for(Node node : list[current]){
                if(dist[node.end] >= dist[current] + node.dis){
                    dist[node.end] = dist[current] + node.dis;
                    pq.add(new Node(node.end,dist[node.end]));
                }
            }
        }
    }
	

}
