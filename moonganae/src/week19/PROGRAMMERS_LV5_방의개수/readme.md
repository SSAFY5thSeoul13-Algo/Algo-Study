## Progrmmers LV5 방의 개수
- 그래프
- Level 5
- https://programmers.co.kr/learn/courses/30/lessons/49190
<br>

### 문제설명

> 원점(0,0)에서 시작해서 아래처럼 숫자가 적힌 방향으로 이동하며 선을 긋습니다.

```
	7 0 1
	6 . 2
	5 4 3
```

> ex) 1일때는 오른쪽 위로 이동
그림을 그릴 때, 사방이 막히면 방하나로 샙니다.
이동하는 방향이 담긴 배열 arrows가 매개변수로 주어질 때, 방의 갯수를 return 하도록 solution 함수를 작성하세요.


### 제한사항
- 배열 arrows의 크기는 1 이상 100,000 이하 입니다.
- arrows의 원소는 0 이상 7 이하 입니다.
- 방은 다른 방으로 둘러 싸여질 수 있습니다.

### 입출력 예
|arrows|	return|
|-------|----|
|[6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0]|	3|



#### 입출력 예 설명
(0,0) 부터 시작해서 6(왼쪽) 으로 3번 이동합니다. 그 이후 주어진 arrows 를 따라 그립니다.
삼각형 (1), 큰 사각형(1), 평행사변형(1) = 3

### 풀이 및 과정
문제를 처음 봤을때 접근 방법조차 떠올리기 힘들었기 때문에, 블로그를 참고하였습니다.

방을 찾는 조건은 다음과 같습니다

#### (1) : 방문한 정점을 다시 방문했을 경우
이 방법의 반례는 2(->) , 6(<-) 일때 아직 방을 못찾았지만, 이 로직으로는 방을 찾은것처럼 된다.

#### (2) : (1) + 간선까지 확인하기
방문한 정점이면서, 처음 방문한 간선이라면 위의 반례를 해결할 수 있습니다.<br>
하지만, 이 경우에도 모래시계 반례가 존재한다.

	O--O
	 \/
	 /\
	O--O

이 경우는 겹치는 부분이 정점이 좌표로 표현할 수 없기 때문에 발생한다.

#### (3) : (2) + 두칸씩 이동하기
모래시계 반례를 해결하기 위해 방향에 따라 이동할때 2칸씩 이동하게되면 해당 반례를 해결할 수 있게 된다.

자료구조는 크게, HashSet을 2개(정점방문, 간선방문)을 사용하며, delta Array를 미리 선언하여 좌표이동을 수행한다.

 그 후, 방향에 따라 2칸씩 이동하면서 정점과 간선 방문여부를 갱신하면서 로직을 수행한다.

#### 막힌점
HashSet을 사용법에서 막혔습니다.<br>
객체비교를 위해 equals 메소드를 override 했지만, 답은 여전히 구해지지 않았고,<br>
구글링하여 hashCode 메소드 역시 override 해야지만, 정상적으로 contain 함수가 동작함을 알 수 있었습니다.

### 소스코드
```java
import java.util.*;
class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        
        // 정점 방문체크
        Set<Vertex> vertVis = new HashSet<>();
        // 간선 방문체크
        Set<Edge> edgeVis = new HashSet<>();
        
        // 방향 delta Array
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        
        // 초기 위치는 0,0
        int y=0, x=0;
        vertVis.add(new Vertex(y,x));
        
        for(int dir : arrows){
            // 모래시계 예외를 위한 같은방향 2번이동
            for(int i=0; i<2; i++){
                int ty = y + dy[dir];
                int tx = x + dx[dir];

                Vertex next = new Vertex(ty,tx);
                Edge from = new Edge(y,x, dir);
                
                // 방문한정점이면서 해당 간선은 처음 사용했다면
                if(vertVis.contains(next) && !edgeVis.contains(from)){
                   answer++; // 방을 찾음!
                }else{
                    vertVis.add(next); // 방문하지 않았을경우 방문체크
                }
                
                // 간선 방문추가
                edgeVis.add(from);
                // (dir+4) % 8 : dir의 반대방향 idx
                edgeVis.add(new Edge(ty,tx, (dir+4)%8));

                // 좌표 이동
                y = ty;
                x = tx;    
            }
        }
        return answer;
    }
    // 정점 객체
    public class Vertex {
        int y, x;
        public Vertex(int y, int x){
            this.y = y;
            this.x = x;
        }
        @Override
        public boolean equals(Object obj) {
        	Vertex v = (Vertex) obj;
        	if(this.y == v.y && this.x == v.x)
        		return true;
        	return false;
        }
        @Override
        public int hashCode() {
        	int prime = 31;
        	int hashcode = 1;
        	
        	hashcode = prime*hashcode + this.y;
        	hashcode = prime*hashcode + this.x;
        	
        	return hashcode;
        }
    }
    // 간선 객체
    public class Edge {
        int y, x, dir;
        public Edge(int y, int x, int dir){
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
        @Override
        public boolean equals(Object obj) {
        	Edge e = (Edge) obj;
        	
        	if(this.y == e.y && this.x == e.x && this.dir == e.dir)
        		return true;
        	return false;
        }
        @Override
        public int hashCode() {
        	int prime = 31;
        	int hashcode = 1;
        	
        	hashcode = prime*hashcode + this.y;
        	hashcode = prime*hashcode + this.x;
        	hashcode = prime*hashcode + this.dir;
        	
        	return hashcode;
        }
    }
}
```

### 결과
```
테스트 1 〉	통과 (2.48ms, 52.6MB)
테스트 2 〉	통과 (3.70ms, 53.3MB)
테스트 3 〉	통과 (5.71ms, 53.1MB)
테스트 4 〉	통과 (8.27ms, 54.3MB)
테스트 5 〉	통과 (41.44ms, 59.2MB)
테스트 6 〉	통과 (310.10ms, 88.3MB)
테스트 7 〉	통과 (23.19ms, 57.3MB)
테스트 8 〉	통과 (206.89ms, 91.1MB)
테스트 9 〉	통과 (200.95ms, 109MB)
```

