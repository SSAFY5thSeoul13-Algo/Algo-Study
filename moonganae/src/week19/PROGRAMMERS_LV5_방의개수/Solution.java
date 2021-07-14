package week19.PROGRAMMERS_LV5_방의개수;

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
