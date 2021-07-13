
## Programmers Lv3 가장 먼 노드
- 그래프
- BFS
- level3



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/49189

n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

#### 제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.  
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.  
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.  

#### 입출력 예
n = 6
vertex = [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	

return = 3

예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.  
![](https://images.velog.io/images/jodawooooon/post/e298d71b-1a54-41f7-b2e1-30cf877eee82/image.png)

<br><br>

###  💡 풀이

BFS로 풀었습니다.  
1번 노드에서 가장 멀리 떨어진 노드의 **갯수**를 구해야 하므로  
BFS를 이동해 최단경로로 그래프 탐색을 수행했습니다.  

```
static class Node{
      int curr;
      int moveCnt;
      public Node(int curr, int moveCnt){
          this.curr = curr;
          this.moveCnt = moveCnt;
      }
  }
```
위와 같은 클래스 형태로 각 노드를 구현했습니다.  
여기서 `curr`은 인덱스 번호를 의미하고,  
`moveCnt`는 현 노드에 방문하기까지 이동한 노드의 갯수를 의미합니다.  
<br>

각 노드를 이동하면서 moveCnt와 전역변수 `max`를 비교하며 현 노드가 가장 멀리 떨어진 노드인지 체크하고 max값과 동일할 경우 동일한 최대거리를 가진 노드가 여러 개이므로 `answer++`합니다.

```
if(moveCnt>max){ //유일하게 가장 멀리 떨어진 노드일경우
      max = moveCnt; //이동거리를 max 전역변수에 갱신
      answer=1; //갯수는 1로 초기화
            	
} else if(moveCnt==max){ //동일 이동거리의 노드가 여러개일경우
	answer++; //갯수 증가
}
```
<br>

다음 노드으로의 이동은 `edge`배열을 이동하여 연결이 되어있을 경우에만 이동합니다.  
(이 때 각 노드는 양방향 연결)

```
for(int[] v : edge){
	int n1 = v[0];
	int n2 = v[1];
            	
	//연결 노드 찾아서 이동
            	
	if(curr==n1) {
		if(visited[n2]) continue;
		visited[n2]=true;
		queue.add(new Node(n2, moveCnt+1));
                	
	}else if(curr==n2) {
		if(visited[n1]) continue;
		visited[n1]=true;
		queue.add(new Node(n1, moveCnt+1));
	}
                
} 
```



<br><br>

###  💡 소스코드
```
import java.util.*;

class Solution {
    static int answer;
    static int max;
    public int solution(int n, int[][] edge) {
        // 1번 노드에서 가장 멀리 떨어진 노드의 갯수

        bfs(n, edge);
        
        return answer;
    }
    
    private static void bfs(int n, int[][] edge){
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        //1번부터
        queue.add(new Node(1,1));
        visited[1] = true;
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int curr = node.curr;
            int moveCnt = node.moveCnt;
            
           
            if(moveCnt>max){ //유일하게 가장 멀리 떨어진 노드일경우
            	max = moveCnt; //이동거리를 max 전역변수에 갱신
            	answer=1; //갯수는 1로 초기화
            	
            }else if(moveCnt==max){ //동일 이동거리의 노드가 여러개일경우
                answer++; //갯수 증가
            }
            
            for(int[] v : edge){
            	int n1 = v[0];
            	int n2 = v[1];
            	
            	//연결 노드 찾아서 이동
            	
                if(curr==n1) {
                	if(visited[n2]) continue;
                	visited[n2]=true;
                	queue.add(new Node(n2, moveCnt+1));
                	
                }else if(curr==n2) {
                	if(visited[n1]) continue;
                	visited[n1]=true;
                	queue.add(new Node(n1, moveCnt+1));
                }
                
            } 
        }
    }
    
    static class Node{
        int curr;
        int moveCnt;
        public Node(int curr, int moveCnt){
            this.curr = curr;
            this.moveCnt = moveCnt;
        }
    }
}
```

<br>

### 결과
테스트 1 〉	통과 (0.42ms, 52.5MB)  
테스트 2 〉	통과 (0.41ms, 51.6MB)  
테스트 3 〉	통과 (0.43ms, 51.7MB)  
테스트 4 〉	통과 (2.84ms, 52.4MB)  
테스트 5 〉	통과 (16.61ms, 53MB)  
테스트 6 〉	통과 (32.11ms, 54.9MB)  
테스트 7 〉	통과 (2718.53ms, 69.6MB)  
테스트 8 〉	통과 (3627.00ms, 74.2MB)  
테스트 9 〉	통과 (6041.17ms, 71.6MB)  