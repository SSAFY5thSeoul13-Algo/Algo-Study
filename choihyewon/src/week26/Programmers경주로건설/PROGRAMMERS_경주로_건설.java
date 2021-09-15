package week26.Programmers경주로건설;

import java.util.*;

class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int N;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static class Node{
        int r;
        int c;
        int dir;
        int cost;
        public Node(int r,int c,int dir,int cost){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        visited = new boolean[N][N];
        bfs(board);
        answer = min;
        return answer;
    }
    
    public void bfs(int[][] board){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,-1,0));
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int curDir = node.dir;
            
            // 도착점에 도달했을 때 
            if(node.r==N-1 && node.c==N-1){
                min = Math.min(min,node.cost);
            }
            
            for(int d=0; d<4; d++){
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                int cost = node.cost;
                
                // 칸이 벽으로 채워져있지 않거나 위치가 범위내에 있을 때 
                if(nr>=0 && nc>=0 && nr<N && nc<N && board[nr][nc]!=1){
                    int newCost = 0;
                    // 직선도로를 만드는 경우 
                    if(curDir == -1 || curDir == d){
                        newCost = node.cost +  100;
                    }
                    // 코너를 만드는 경우 
                    else{
                        newCost = node.cost + 600;
                    }
                    
                    // 현재 가격과 비교하여 낮은 가격으로 갱신 
                    if(!visited[nr][nc] || board[nr][nc] >= newCost){
                        visited[nr][nc] = true;
                        board[nr][nc] = newCost;
                        queue.add(new Node(nr,nc,d,newCost));
                    }
                }
            }
        }
    }
}
