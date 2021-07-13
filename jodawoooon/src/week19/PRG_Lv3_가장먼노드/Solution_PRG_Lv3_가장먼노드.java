package week19.PRG_Lv3_가장먼노드;

import java.util.*;

public class Solution_PRG_Lv3_가장먼노드 {
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
