
## Progrmmers LV3 네트워크
- 깊이/너비 우선 탐색(DFS/BFS)
- Level 3

<br>

### 문제설명

> 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 
예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 
컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 
따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

> 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.


### 풀이 및 과정
- Union & Find 자료구조를 사용하여 해결
- 먼저 computers 배열을 이용하여 Union시킨다.
- 그후 모든 배열에 대해 find : 즉, 부모를 찾아준다. (최종적으로 같은 네트워크인지 확인하기 위해)
- parent배열을 정렬시키고, 네트워크의 수를 센다.

### 소스코드
```java
import java.util.*;
class Solution {
    int[] parent;	// 부모배열
    public int solution(int n, int[][] computers) {
        int answer = 1;
        init(n);
        
        // Union 작업
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j) continue;
                if(computers[i][j] == 1)
                    union(i,j);
            }
        }
        // 최종적으로 같은 네트워크인지 판별
        for(int i=0; i<n; i++){
            find(i);
        }
        // 정렬
        Arrays.sort(parent);
        
        // 다른 숫자가 등장 == 다른 네트워크가 있다.
        int cur = parent[0];
        for(int i=1; i<n; i++){
            if(cur != parent[i]){
                answer++;
                cur = parent[i];
            }
        }
        
        return answer;
    }
    // 부모배열 초기화
    public void init(int n){
        parent = new int[n];
        for(int i=0; i<n; i++)
            parent[i] = i;
    }
    // 부모찾기
    public int find(int a){
        if(parent[a] == a){
            return a;
        } else{
            return parent[a] = find(parent[a]);
        }
    }
    // 결합
    public void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        
        parent[pb] = pa;
    }
}


```

### 결과
```
테스트 1 〉	통과 (0.50ms, 52.1MB)
테스트 2 〉	통과 (0.48ms, 52.1MB)
테스트 3 〉	통과 (0.42ms, 52.5MB)
테스트 4 〉	통과 (0.52ms, 53MB)
테스트 5 〉	통과 (0.44ms, 52.3MB)
테스트 6 〉	통과 (0.70ms, 52.3MB)
테스트 7 〉	통과 (0.43ms, 52.4MB)
테스트 8 〉	통과 (0.61ms, 52.7MB)
테스트 9 〉	통과 (1.26ms, 53.3MB)
테스트 10 〉	통과 (0.59ms, 52.1MB)
테스트 11 〉	통과 (1.67ms, 53.3MB)
테스트 12 〉	통과 (0.89ms, 52.9MB)
테스트 13 〉	통과 (0.70ms, 54.1MB)
```