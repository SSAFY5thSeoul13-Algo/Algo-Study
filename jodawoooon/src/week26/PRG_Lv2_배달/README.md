## Programmers 배달


- 다익스트라
- Summer/Winter Coding(~2018)



<br><br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/12978
    
    
#### ✔ 문제

N개의 마을로 이루어진 나라가 있습니다. 이 나라의 각 마을에는 1부터 N까지의 번호가 각각 하나씩 부여되어 있습니다. 각 마을은 양방향으로 통행할 수 있는 도로로 연결되어 있는데, 서로 다른 마을 간에 이동할 때는 이 도로를 지나야 합니다. 도로를 지날 때 걸리는 시간은 도로별로 다릅니다. 현재 1번 마을에 있는 음식점에서 각 마을로 음식 배달을 하려고 합니다. 각 마을로부터 음식 주문을 받으려고 하는데, N개의 마을 중에서 K 시간 이하로 배달이 가능한 마을에서만 주문을 받으려고 합니다. 다음은 N = 5, K = 3인 경우의 예시입니다.
![](https://images.velog.io/images/jodawooooon/post/d5a1a55b-bef3-4fd1-9c5a-d7f19f0b66eb/image.png)
위 그림에서 1번 마을에 있는 음식점은 [1, 2, 4, 5] 번 마을까지는 3 이하의 시간에 배달할 수 있습니다. 그러나 3번 마을까지는 3시간 이내로 배달할 수 있는 경로가 없으므로 3번 마을에서는 주문을 받지 않습니다. 따라서 1번 마을에 있는 음식점이 배달 주문을 받을 수 있는 마을은 4개가 됩니다.
마을의 개수 N, 각 마을을 연결하는 도로의 정보 road, 음식 배달이 가능한 시간 K가 매개변수로 주어질 때, 음식 주문을 받을 수 있는 마을의 개수를 return 하도록 solution 함수를 완성해주세요.

#### ✔ 제한사항
마을의 개수 N은 1 이상 50 이하의 자연수입니다.
road의 길이(도로 정보의 개수)는 1 이상 2,000 이하입니다.
road의 각 원소는 마을을 연결하고 있는 각 도로의 정보를 나타냅니다.
road는 길이가 3인 배열이며, 순서대로 (a, b, c)를 나타냅니다.
- a, b(1 ≤ a, b ≤ N, a != b)는 도로가 연결하는 두 마을의 번호이며, c(1 ≤ c ≤ 10,000, c는 자연수)는 도로를 지나는데 걸리는 시간입니다.
- 두 마을 a, b를 연결하는 도로는 여러 개가 있을 수 있습니다.
한 도로의 정보가 여러 번 중복해서 주어지지 않습니다.

K는 음식 배달이 가능한 시간을 나타내며, 1 이상 500,000 이하입니다.
임의의 두 마을간에 항상 이동 가능한 경로가 존재합니다.
1번 마을에 있는 음식점이 K 이하의 시간에 배달이 가능한 마을의 개수를 return 하면 됩니다.
<br>



###  💡 풀이


1번마을에서 1~N번 마을로 음식을 배달할 때, K시간 이하로 배달이 가능한 마을의 개수를 찾는 문제였다.

다익스트라 알고리즘으로 풀었다.
일단 처음에 1번은 자기 자신이므로 answer=1로 시작해서
start 노드를 1번으로 두고, end노드를 2번부터 N번으로 하여 다익스트라 알고리즘을 이용하여 최단 경로를 구했다.
그리고 이 때의 최단경로가 k이하인 경우 answer++해서 개수를 구했다.

<br><br>

###  💬 소스코드


```java
import java.util.*;

class Solution {
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
```

<br><br>


### 💯 채점결과
채점을 시작합니다.
정확성  테스트
테스트 1 〉	통과 (0.60ms, 59.2MB)
테스트 2 〉	통과 (0.52ms, 70.1MB)
테스트 3 〉	통과 (0.86ms, 70.5MB)
테스트 4 〉	통과 (0.67ms, 68.9MB)
테스트 5 〉	통과 (0.90ms, 61.4MB)
테스트 6 〉	통과 (0.66ms, 71.8MB)
테스트 7 〉	통과 (0.54ms, 58.5MB)
테스트 8 〉	통과 (0.64ms, 72.4MB)
테스트 9 〉	통과 (0.75ms, 69.7MB)
테스트 10 〉	통과 (0.70ms, 68.9MB)
테스트 11 〉	통과 (0.93ms, 72.8MB)
테스트 12 〉	통과 (2.18ms, 70.3MB)
테스트 13 〉	통과 (3.39ms, 57.7MB)
테스트 14 〉	통과 (7.63ms, 58.7MB)
테스트 15 〉	통과 (11.58ms, 77MB)
테스트 16 〉	통과 (1.95ms, 73.4MB)
테스트 17 〉	통과 (2.20ms, 73.6MB)
테스트 18 〉	통과 (5.61ms, 72.9MB)
테스트 19 〉	통과 (13.32ms, 76.7MB)
테스트 20 〉	통과 (4.33ms, 70.3MB)
테스트 21 〉	통과 (11.60ms, 77.4MB)
테스트 22 〉	통과 (10.63ms, 61.6MB)
테스트 23 〉	통과 (13.34ms, 73.1MB)
테스트 24 〉	통과 (9.07ms, 75.3MB)
테스트 25 〉	통과 (13.06ms, 76.6MB)
테스트 26 〉	통과 (14.51ms, 61.4MB)
테스트 27 〉	통과 (18.61ms, 60.8MB)
테스트 28 〉	통과 (20.02ms, 76.5MB)
테스트 29 〉	통과 (13.04ms, 64.6MB)
테스트 30 〉	통과 (13.55ms, 77.8MB)
테스트 31 〉	통과 (2.41ms, 60.6MB)
테스트 32 〉	통과 (3.23ms, 70.2MB)
채점 결과
정확성: 100.0
합계: 100.0 / 100.0