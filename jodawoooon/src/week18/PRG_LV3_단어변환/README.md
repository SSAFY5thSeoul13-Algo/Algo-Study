
- 깊이/너비 우선 탐색(DFS/BFS)
- level3



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/43163

두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 **가장 짧은 변환 과정**을 찾으려고 합니다.

1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
2. words에 있는 단어로만 변환할 수 있습니다.
예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.


#### 제한사항
각 단어는 알파벳 소문자로만 이루어져 있습니다.
각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
begin과 target은 같지 않습니다.
변환할 수 없는 경우에는 0를 return 합니다.
<br><br>

###  💡 풀이

BFS로 간단하게 접근했다.

words에 있는 단어로만 변환이 가능하고, 한 번에 한 알파벳만 변환 가능 하므로
이 두 조건을 모두 반영하기 위해서는
words 배열에서 비교 단어와 단 한 개의 알파벳만 다른 알파벳을 찾아 나가면서 변화 시키면 된다.

즉 풀이 과정은 다음과 같다

1. Queue에서 단어를 하나 꺼낸다.
2. 이를 단어의 집합 `words`와 비교 한 후, 한 글자만 다른 경우 Queue에 집어 넣는다.
3. 이를 반복해서 Queue에서 꺼낸 단어가 target이면 종료!
<br><br>

###  💡 소스코드
```
import java.util.*;

class Solution {
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
```


<br>