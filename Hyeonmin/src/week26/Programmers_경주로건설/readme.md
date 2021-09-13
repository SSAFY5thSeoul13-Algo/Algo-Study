## Programmers 경주로 건설
- BFS

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/67259

설계 도면에는 각 격자의 칸은 0 또는 1 로 채워져 있으며, 0은 칸이 비어 있음을 1은 해당 칸이 벽으로 채워져 있음을 나타냅니다.

인접한 두 빈 칸을 상하 또는 좌우로 연결한 경주로를 직선 도로 라고 합니다.
또한 두 직선 도로가 서로 직각으로 만나는 지점을 코너 라고 부릅니다.
건설 비용을 계산해 보니 직선 도로 하나를 만들 때는 100원이 소요되며, 코너를 하나 만들 때는 500원이 추가로 듭니다.

도면의 상태(0은 비어 있음, 1은 벽)을 나타내는 2차원 배열 board가 매개변수로 주어질 때, 경주로를 건설하는데 필요한 최소 비용을 return 하도록 solution 함수를 완성해주세요.


#### 제한사항
board는 2차원 정사각 배열로 배열의 크기는 3 이상 25 이하입니다.
board 배열의 각 원소의 값은 0 또는 1 입니다.
도면의 가장 왼쪽 상단 좌표는 (0, 0)이며, 가장 우측 하단 좌표는 (N-1, N-1) 입니다.
원소의 값 0은 칸이 비어 있어 도로 연결이 가능함을 1은 칸이 벽으로 채워져 있어 도로 연결이 불가능함을 나타냅니다.
board는 항상 출발점에서 도착점까지 경주로를 건설할 수 있는 형태로 주어집니다.
출발점과 도착점 칸의 원소의 값은 항상 0으로 주어집니다.

###  💡 풀이

bfs로 풀었습니다. 3차원 배열 `map`을 사용해서 각 좌표와 그 좌표에 이동할 때의 방향을 인덱스로한 3차원 배열에서 bfs를 사용하여 각 위치에 도로를 건설할 때의 최소 비용을 구했습니다

<br><br>

###  💡 소스코드
```java
import java.util.*;

public class Programmers_경주로건설 {
	    static int[][] map;
	    static int[] dx = {0,0,-1,1};
	    static int[] dy = {-1,1,0,0};
	    static int min = Integer.MAX_VALUE, N;
	    
	    static class Node{
	        int x, y, price, d;
	        
	        public Node(int x, int y, int price, int d){
	            this.x = x;
	            this.y = y;
	            this.price = price;
	            this.d = d;
	        }
	    }
	    
	    public int solution(int[][] board) {
	        N = board.length;
	        
	        map = board;
	        
	        Queue<Node> q = new LinkedList<>();
	        q.offer(new Node(0,0,0,-1));
	        
	        map[0][0] = 1;
	        
	        while(!q.isEmpty()){
	            Node node = q.poll();
	            
	            if(node.x == N-1 && node.y == N-1){
	                min = Math.min(min, node.price);
	                continue;
	            }
	            
	            for(int d = 0; d < 4; d++){
	                int nx = node.x + dx[d];
	                int ny = node.y + dy[d];
	                
	                if(canGo(nx, ny)){
	                    int sum = node.price;
	                    
	                    if(node.d == -1 || node.d == d){
	                        sum += 100;
	                    }
	                    else{
	                        sum += 600;
	                    }
	                    
	                    if(map[nx][ny] == 0 || map[nx][ny] >= sum){
	                        map[nx][ny] = sum;
	                        q.offer(new Node(nx,ny,sum,d));
	                    }
	                }
	            }
	        }
	        
	        return min;
	    }
	    
	    static boolean canGo(int x, int y){
	        if(x< 0 || y< 0 || x>=N || y>=N || map[x][y] == 1)  return false;
	        
	        return true;
	    }
}





```


<br>



메모리|시간
--|--
테스트 1 |	통과 (0.35ms, 73.2MB)
테스트 2 |	통과 (0.31ms, 59.3MB)
테스트 3 |	통과 (0.32ms, 67.1MB)
테스트 4 |	통과 (0.29ms, 60.5MB)
테스트 5 |	통과 (0.46ms, 74MB)
테스트 6 |	통과 (2.54ms, 75.5MB)
테스트 7 |	통과 (2.30ms, 69.4MB)
테스트 8 |	통과 (3.45ms, 71.9MB)
테스트 9 |	통과 (1.02ms, 73.2MB)
테스트 10 |	통과 (2.62ms, 58.3MB)
테스트 11 |	통과 (1786.07ms, 691MB)
테스트 12 |	통과 (8.75ms, 59.3MB)
테스트 13 |	통과 (0.81ms, 70.9MB)
테스트 14 |	통과 (1.50ms, 69.9MB)
테스트 15 |	통과 (2.70ms, 72.7MB)
테스트 16 |	통과 (8.46ms, 61.7MB)
테스트 17 |	통과 (18.95ms, 76.7MB)
테스트 18 |	통과 (36.12ms, 64.4MB)
테스트 19 |	통과 (179.14ms, 125MB)
테스트 20 |	통과 (1.51ms, 59.7MB)
테스트 21 |	통과 (1.29ms, 59.4MB)
테스트 22 |	통과 (0.54ms, 61.2MB)
테스트 23 |	통과 (0.54ms, 68.4MB)
테스트 24 |	통과 (0.35ms, 58.2MB)
테스트 25 |	통과 (0.31ms, 70.7MB)