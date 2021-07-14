## Progrmmers LV3 가장 먼 노드
- 그래프
- Level 3

<br>

### 문제설명

> n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. <br>
1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. <br>
가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.<br>

> 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, <br>
1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.


### 제한사항
- 노드의 개수 n은 2 이상 20,000 이하입니다.
- 간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
- vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.

### 입출력
|n|vertex|return|
|---|-----------|---|
|6|[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]|	3|

### 풀이 및 과정
결국은 leaf 노드를 찾는 문제라고 생각하였고, 탐색은 BFS를 이용하여 해결하였습니다.

1. 먼저 입력을 이용해 양방향 그래프를 생성하였습니다.
2. 이후 size를 확인하는 BFS 탐색을 하며, 그래프를 순회하였습니다.
3. 정점을 방문할때마다 체크하였고, 모든 정점을 다 방문하였다면 leaf리스트에 있는 것이 진짜 leaf노드라고 판단!


### 소스코드
```java
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
```

### 결과
```
테스트 1 〉	통과 (0.22ms, 52.6MB)
테스트 2 〉	통과 (0.23ms, 53MB)
테스트 3 〉	통과 (0.30ms, 52.9MB)
테스트 4 〉	통과 (1.10ms, 52.4MB)
테스트 5 〉	통과 (3.97ms, 53.1MB)
테스트 6 〉	통과 (6.56ms, 57.3MB)
테스트 7 〉	통과 (37.20ms, 74.9MB)
테스트 8 〉	통과 (57.34ms, 75.2MB)
테스트 9 〉	통과 (45.29ms, 77.9MB)
```

