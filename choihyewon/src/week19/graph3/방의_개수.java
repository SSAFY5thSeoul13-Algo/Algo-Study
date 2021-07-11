package week19.graph3;
 
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
