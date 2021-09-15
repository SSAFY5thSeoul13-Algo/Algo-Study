## Programmers - 배달 
- Dijkstra
- Level 2
- Summer/Winter Coding(~2018) 

🔗[배달 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/12978)

## 풀이

한 정점에서 최단경로를 구하는 문제이기 때문에 다익스트라 알고리즘으로 풀었습니다.
기존 다익스트라 알고리즘을 적용한 뒤 dist(최단 경로가 들어있는 배열)에서 K이하인 값만 count 해주었습니다. 


## 소스코드
~~~java
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
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.51ms, 70.6MB)|
|테스트 2 |	통과 (0.47ms, 60.2MB)|
|테스트 3 |	통과 (0.51ms, 66.9MB)|
|테스트 4 |	통과 (0.56ms, 58.2MB)|
|테스트 5 |	통과 (0.53ms, 73.4MB)|
|테스트 6 |	통과 (0.51ms, 74.5MB)|
|테스트 7 |	통과 (0.52ms, 73MB)|
|테스트 8 |	통과 (0.48ms, 58.8MB)|
|테스트 9 |	통과 (0.50ms, 59.3MB)|
|테스트 10 |	통과 (0.55ms, 71.6MB)|
|테스트 11 |	통과 (0.53ms, 59.7MB)|
|테스트 12 |	통과 (0.60ms, 60.6MB)|
|테스트 13 |	통과 (0.59ms, 72.1MB)|
|테스트 14 |	통과 (1.58ms, 60.4MB)|
|테스트 15 |	통과 (1.86ms, 61MB)|
|테스트 16 |	통과 (0.68ms, 59.5MB)|
|테스트 17 |	통과 (0.54ms, 69.7MB)|
|테스트 18 |	통과 (1.05ms, 72.1MB)|
|테스트 19 |	통과 (1.98ms, 77MB)|
|테스트 20 |	통과 (1.09ms, 71.5MB)|
|테스트 21 |	통과 (2.07ms, 59.9MB)|
|테스트 22 |	통과 (1.31ms, 72.3MB)|
|테스트 23 |	통과 (2.16ms, 72.2MB)|
|테스트 24 |	통과 (1.60ms, 60.4MB)|
|테스트 25 |	통과 (2.09ms, 61MB)|
|테스트 26 |	통과 (3.11ms, 62.7MB)|
|테스트 27 |	통과 (2.10ms, 74.7MB)|
|테스트 28 |	통과 (2.26ms, 77MB)|
|테스트 29 |	통과 (2.55ms, 73.2MB)|
|테스트 30 |	통과 (2.62ms, 74.1MB)|
|테스트 31 |	통과 (0.72ms, 73.5MB)|
|테스트 32 |	통과 (0.71ms, 59.4MB)|
