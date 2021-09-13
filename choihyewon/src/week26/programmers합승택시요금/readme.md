## Programmers - 합승택시요금 
- Dijkstra
- 2021 KAKAO BLIND RECRUITMENT
- Level 3
🔗[합승택시요금 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/72413)


## 풀이

어느 한 지점에서 다른 지점까지의 최소비용을 구하는 문제이기 때문에 다익스트라 알고리즘을 사용하여 풀었습니다.

지점 S에서 A,B로 가는 경우 S에서 합승하여 중간지점까지의 비용 + 중간지점에서 A로 가는 비용 + 중간지점에서 B로 가는 비용 의 최소비용을 구하였습니다.

~~~java
        int[] disS = dijkstra(s,n);
        int[] disA = dijkstra(a,n);
        int[] disB = dijkstra(b,n);
        
        for(int i=1; i<=n; i++){
            answer = Math.min(answer,disS[i]+disA[i]+disB[i]);
        }
~~~



## 소스코드
~~~java
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

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.71ms, 59.8MB)|
|테스트 2 |	통과 (0.62ms, 71.3MB)|
|테스트 3 |	통과 (0.61ms, 70.5MB)|
|테스트 4 |	통과 (0.94ms, 71.8MB)|
|테스트 5 |	통과 (0.94ms, 57.7MB)|
|테스트 6 |	통과 (0.75ms, 58.7MB)|
|테스트 7 |	통과 (0.96ms, 73.1MB)|
|테스트 8 |	통과 (0.91ms, 71.6MB)|
|테스트 9 |	통과 (1.05ms, 72MB)|
|테스트 10 |	통과 (0.88ms, 69.2MB)|


-------

|효율성 | 테스트 |
|---|---|
|테스트 1 |	통과 (3.56ms, 53.4MB|
|테스트 2 |	통과 (8.94ms, 53.8MB)|
|테스트 3 |	통과 (8.58ms, 52.4MB)|
|테스트 4 |	통과 (3.91ms, 52.3MB)|
|테스트 5 |	통과 (3.97ms, 53MB)|
|테스트 6 |	통과 (4.71ms, 53.3MB)|
|테스트 7 |	통과 (22.20ms, 64.8MB)|
|테스트 8 |	통과 (20.65ms, 69.2MB)|
|테스트 9 |	통과 (24.81ms, 65.8MB)|
|테스트 10 |	통과 (23.16ms, 66.3MB)|
|테스트 11 |	통과 (28.68ms, 66.3MB)|
|테스트 12 |	통과 (15.07ms, 59.9MB)|
|테스트 13 |	통과 (16.00ms, 60.6MB)|
|테스트 14 |	통과 (29.20ms, 63.4MB)|
|테스트 15 |	통과 (17.19ms, 60.7MB)|
|테스트 16 |	통과 (3.92ms, 54MB)|
|테스트 17 |	통과 (3.37ms, 52.6MB)|
|테스트 18 |	통과 (3.35ms, 52.8MB)|
|테스트 19 |	통과 (6.40ms, 53.7MB)|
|테스트 20 |	통과 (8.78ms, 53.3MB)|
|테스트 21 |	통과 (13.18ms, 53.6MB)|
|테스트 22 |	통과 (22.89ms, 58.6MB)|
|테스트 23 |	통과 (20.71ms, 61.4MB)|
|테스트 24 |	통과 (13.33ms, 60.9MB)|
|테스트 25 |	통과 (2.87ms, 52.8MB)|
|테스트 26 |	통과 (2.38ms, 52.9MB)|
|테스트 27 |	통과 (7.73ms, 54.5MB)|
|테스트 28 |	통과 (12.48ms, 54MB)|
|테스트 29 |	통과 (2.37ms, 52.2MB)|
|테스트 30 |	통과 (2.31ms, 52MB)|