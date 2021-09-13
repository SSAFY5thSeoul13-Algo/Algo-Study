package week26.Programmers_경주로건설;
import java.util.*;

public class Programmers_경주로건설 {
    static int[][][] map;
    static int[][] boardMap;
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
        
        boardMap = board;
        map = new int[4][N][N];
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0,0,-1));
        
        map[0][0][0] = 1;
        map[1][0][0] = 1;
        map[2][0][0] = 1;
        map[3][0][0] = 1;
        
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
                    
                    if(map[d][nx][ny] == 0 || map[d][nx][ny] >= sum){
                        map[d][nx][ny] = sum;
                        q.offer(new Node(nx,ny,sum,d));
                    }
                }
            }
        }
        
        return min;
    }
    
    static boolean canGo(int x, int y){
        if(x< 0 || y< 0 || x>=N || y>=N || boardMap[x][y] == 1)  return false;
        
        return true;
    }
}
