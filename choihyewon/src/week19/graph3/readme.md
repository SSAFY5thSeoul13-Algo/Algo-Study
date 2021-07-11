## Programmers - 방의 개수 
- Graph , HashSet 
- Level3

🔗[방의 개수 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/49190)

## 풀이
저는 최대 10만번 이동이 가능하기 때문에 배열대신 HashSet을 사용해주었습니다.

~~~java
		// 방문한 정점 관리를 위한 set 
        Set<Node> node = new HashSet<>();
        // 방문한 간선 관리를 위한 set 
        Set<Edge> edge = new HashSet<>();
~~~
정점, 간선의 객체를 만들고 각 객체를 HashSet에 저장할때 같은 객체를 나타내는 조건을 재정의해주기 위해서 Equals, hashCode 메소드를 재정의해주었습니다.

~~~java
	class Node{
		int y;
		int x;
		public Node(int y,int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public boolean equals(Object o) {
			Node n = (Node) o;
			
			if(this.y==n.y && this.x==n.x)
				return true;
			return false;
			
		}
		@Override
		public int hashCode() {
			int prime = 31;
			int hashcode = 1;
			
			hashcode = prime * hashcode + this.y;
			hashcode = prime * hashcode + this.x;
			
			return hashcode;
		}
	}
	class Edge{
		int start_y;
		int start_x;
		int end_y;
		int end_x;
		int dir;
		public Edge(int start_y,int start_x, int end_y,int end_x,int dir) {
			this.start_y = start_y;
			this.start_x = start_x;
			this.end_y = end_y;
			this.end_x = end_x;
			this.dir = dir;
		}
		@Override
		public boolean equals(Object o) {
			Edge e = (Edge) o;
			
			if((this.start_y + this.end_y == e.start_y + e.end_y) && (this.start_x + this.end_x == e.start_x + e.end_x) && (Math.abs(e.dir-this.dir) == 4 || e.dir==this.dir)) {
				return true;
			}
			return false;
			
		}
		@Override
		public int hashCode() {
			int prime = 31;
			int hashcode = 1;
			
			hashcode = prime * hashcode + (this.start_y + this.end_y);
			hashcode = prime * hashcode + (this.start_x + this.end_x);
			hashcode = prime * hashcode + this.dir;
			
			return hashcode;
		}
		
	}
~~~

대각선으로 이동하는 경우 다른 간선과 겹치나 표시되지 않는 정점때문에 원하는 방의 개수를 구하지 못하므로 이동을 2번 반복해줍니다. 

~~~java
	for(int i=0; i<arrows.length; i++) {
        	// 현재 방향 
        	int d = arrows[i];
        	// 2번 이동 
        	for(int j=0; j<2; j++) {
        		int ny = y + dy[d];
        		int nx = x + dx[d];
        		
        		Node next_node = new Node(ny,nx);
        		Edge next_edge = new Edge(y,x,ny,nx,d);

        		// 방문한 정점이지만 들어온 방향이 다른 경우 방 count 
        		if(node.contains(next_node) && !edge.contains(next_edge)) {
        			answer++;
        		}
        		// 방문한 적이 없다면 정점set에 추가 
        		else {
        			node.add(next_node);
        		}
        		
        	
        		// 현재 방향의 간선과 반대 방향 간선 추가 
        		edge.add(next_edge);
        		edge.add(new Edge(ny,nx,y,x,(d+4)%8));
        		y = ny;
        		x = nx;
        		
        	}
        	
        }
~~~


## 막힌점 
문제 풀이 방법을 떠올리지 못하여 블로그 풀이를 참조하였습니다. 

## 소스코드
~~~java
import java.util.*;

public class 방의_개수 {
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	class Node{
		int y;
		int x;
		public Node(int y,int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public boolean equals(Object o) {
			Node n = (Node) o;
			
			if(this.y==n.y && this.x==n.x)
				return true;
			return false;
			
		}
		@Override
		public int hashCode() {
			int prime = 31;
			int hashcode = 1;
			
			hashcode = prime * hashcode + this.y;
			hashcode = prime * hashcode + this.x;
			
			return hashcode;
		}
	}
	class Edge{
		int start_y;
		int start_x;
		int end_y;
		int end_x;
		int dir;
		public Edge(int start_y,int start_x, int end_y,int end_x,int dir) {
			this.start_y = start_y;
			this.start_x = start_x;
			this.end_y = end_y;
			this.end_x = end_x;
			this.dir = dir;
		}
		@Override
		public boolean equals(Object o) {
			Edge e = (Edge) o;
			
			if((this.start_y + this.end_y == e.start_y + e.end_y) && (this.start_x + this.end_x == e.start_x + e.end_x) && (Math.abs(e.dir-this.dir) == 4 || e.dir==this.dir)) {
				return true;
			}
			return false;
			
		}
		@Override
		public int hashCode() {
			int prime = 31;
			int hashcode = 1;
			
			hashcode = prime * hashcode + (this.start_y + this.end_y);
			hashcode = prime * hashcode + (this.start_x + this.end_x);
			hashcode = prime * hashcode + this.dir;
			
			return hashcode;
		}
		
	}
	public int solution(int[] arrows) {
        int answer = 0;
        
        // 방문한 정점 관리를 위한 set 
        Set<Node> node = new HashSet<>();
        // 방문한 간선 관리를 위한 set 
        Set<Edge> edge = new HashSet<>();
        
        // (0,0)에서 시작 
        int y = 0;
        int x = 0;
        
        node.add(new Node(y,x));
        
        for(int i=0; i<arrows.length; i++) {
        	// 현재 방향 
        	int d = arrows[i];
        	// 2번 이동 
        	for(int j=0; j<2; j++) {
        		int ny = y + dy[d];
        		int nx = x + dx[d];
        		
        		Node next_node = new Node(ny,nx);
        		Edge next_edge = new Edge(y,x,ny,nx,d);

        		// 방문한 정점이지만 들어온 방향이 다른 경우 방 count 
        		if(node.contains(next_node) && !edge.contains(next_edge)) {
        			answer++;
        		}
        		// 방문한 적이 없다면 정점set에 추가 
        		else {
        			node.add(next_node);
        		}
        		
        	
        		// 현재 방향의 간선과 반대 방향 간선 추가 
        		edge.add(next_edge);
        		edge.add(new Edge(ny,nx,y,x,(d+4)%8));
        		y = ny;
        		x = nx;
        	}
        	
        }
        
        
        return answer;
    }

}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (1.21ms, 52.2MB)|
|테스트 2 |	통과 (2.81ms, 52.9MB)|
|테스트 3 |	통과 (3.41ms, 52.3MB)|
|테스트 4 |	통과 (6.17ms, 54.2MB)|
|테스트 5 |	통과 (43.49ms, 60.5MB)|
|테스트 6 |	통과 (282.65ms, 103MB)|
|테스트 7 |	통과 (20.72ms, 57.3MB)|
|테스트 8 |	통과 (249.02ms, 97.3MB)|
|테스트 9 |	통과 (307.92ms, 112MB)|