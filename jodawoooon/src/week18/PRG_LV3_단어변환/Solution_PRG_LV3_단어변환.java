package week18.PRG_LV3_단어변환;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_PRG_LV3_단어변환 {
	public int solution(String begin, String target, String[] words) {
        
        int answer = bfs(begin, target, words);
        
        return answer;

    }
    
    private static int bfs(String begin, String target, String[] words){
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
                                        
        queue.add(new Node(begin, 0)); //begin을 집어넣음
        
        while(!queue.isEmpty()){
            Node n = queue.poll();
            
            String word = n.word;
            int cnt = n.cnt;
            
            if(word.equals(target)) return cnt; 
            //target과 같으면 그 때의 변환횟수 return
            
            int difCnt = 0;
            
            //words에 있는 단어로만 변환할 수 있다
            for(int i = 0; i<words.length ; i++){
                if(visited[i]) continue;
                
                if(diffCnt(word, words[i])!=1) continue;
                
                //한 단어만 다르다면 ? 변환 가능 => queue에 넣는다
                visited[i] = true;
                queue.add(new Node(words[i], cnt+1));
            }
        }
                                       
        return 0;                        
    }
                                        
    private static int diffCnt(String word1, String word2){
        //word1과 word2의 다른 알파벳 개수 찾기
        
        int cnt = 0;
        
        for(int i = 0 ; i < word1.length() ; i++){
            if(word1.charAt(i)!=word2.charAt(i)) cnt++;
        }
        
        return cnt;
    }                            
    
    static class Node {
        String word;
        int cnt;
        
        public Node(String word, int cnt ){
            this.word = word;
            this.cnt = cnt;
        }
    }
}
