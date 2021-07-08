## Progrmmers LV3 단어변환
- 깊이/너비 우선 탐색(DFS/BFS)
- Level 3

<br>

### 문제설명

> 주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다. <br>
항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

### 제한사항
- 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
- 주어진 공항 수는 3개 이상 10,000개 이하입니다.
- tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
- 주어진 항공권은 모두 사용해야 합니다.
- 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
- 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.

### 풀이 및 과정
DFS를 이용하여 접근하였다.

1. 현재 여행지 cur에 맞는 티겟인지 확인한뒤, 갈 수 있다면 여행을 일단 떠난다~
2. 모든 티켓을 사용하였다면, 기존의 답(path)와 비교하여 사전순으로 앞서는 경우만 path update


### 소스코드
```java
import java.util.*;
class Solution {
    List<String> path;
    List<String> tmp = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        // ICN부터 여행 시작
        tmp.add("ICN");
        
        dfs(0,0,"ICN", tickets);
        
        answer = path.toArray(new String[path.size()]);
        return answer;
    }
    /**
     * 
     * @param num : 고른 티켓의 수
     * @param select : 현재 고른 티켓의 bit mask
     * @param cur : 현재 시작경로
     * @param tickets : 티겟정보
     */
    public void dfs(int num,int select,String cur, String[][] tickets){
        // 모든 티겟을 사용하였다면
        if(num == tickets.length){
            if(path == null || check()){
                path = new ArrayList<>(tmp);
            }
            return;
        }
        
        for(int i=0; i<tickets.length; i++){
            if( (select & 1<<i) != 0) continue;
            // 현재 시작경로가 맞다면
            if( tickets[i][0].equals(cur) ){
                tmp.add(tickets[i][1]);
                dfs(num+1, select|1<<i, tickets[i][1], tickets);
                tmp.remove(tmp.size()-1);
            }
        }
    }
    /**
     * 
     * @return tmp와 path중 알파벳 순서가 앞서는 경로는?
     * true : tmp가 앞섬 -> path = tmp
     * false : path가 앞섬 -> continue;
     */
    public boolean check(){
        int limit = Math.min(path.size(), tmp.size());
        
        for(int i=0; i<limit; i++){
            int result = tmp.get(i).compareTo(path.get(i));
            if(result == 0 ) continue;
            else if(result > 0){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}

```

### 결과
```
테스트 1 〉	통과 (65.93ms, 55.8MB)
테스트 2 〉	통과 (0.05ms, 51.4MB)
테스트 3 〉	통과 (0.05ms, 52.4MB)
테스트 4 〉	통과 (0.06ms, 53.9MB)

```