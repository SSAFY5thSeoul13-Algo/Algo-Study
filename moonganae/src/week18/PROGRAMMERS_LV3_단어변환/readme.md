## Progrmmers LV3 단어변환
- 깊이/너비 우선 탐색(DFS/BFS)
- Level 3

<br>

### 문제설명

> 두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
```
1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
2. words에 있는 단어로만 변환할 수 있습니다.
```

> 예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

> 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.

### 제한사항
- 각 단어는 알파벳 소문자로만 이루어져 있습니다.
- 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
- words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
- begin과 target은 같지 않습니다.
- 변환할 수 없는 경우에는 0를 return 합니다.

### 풀이 및 과정
DFS를 이용하여 접근하였다.

1. 현재 진행단어 cur과 한 글자가 다른 경우 DFS진행 : cur업데이트, stage+1
2. 타겟단어를 찾은경우 현재 stage와 기존에 찾은 stage들을 비교한다.


### 소스코드
```java
class Solution {
    int answer = 51;    // words의 범위가 50개 이하이므로 범위를 넘어서는 값은 51
    public int solution(String begin, String target, String[] words) {
        
        dfs(0, 0, begin, target, words, 0);
        if(answer == 51) answer = 0;    // 51이라는 것은 target을 찾지 못했다!
        return answer;
    }
    /**
     * num : 확인한 단어의 수
     * select : 선택한 단어 bitmask
     * target : target 단어
     * words : 단어의 집합
     * stage : 현재 단계
     */
    public void dfs(int num, int select, String cur, String target, String[] words,int stage){
        // 원하는 단어를 찾았다면
        if(cur.equals(target)){
            // 최소 단계업데이트
            answer = Math.min(answer, stage);
            return;
        }
        // 기저조건 : 끝까지 단어를 뒤졌는가?
        if(num == words.length){
            return;
        }
        
        for(int i=0; i<words.length; i++){
            // 선택한 단어 제외
            if( (select & 1<<i) != 0) continue;
            // 만약 한글자 바꿀수 있다면?
            if(diff(cur, words[i])){
                dfs(i+1, select|1<<i, words[i], target, words, stage+1);
            }
        }
    }
    // 한글자 변경이 가능한지 확인
    public boolean diff(String a, String b){
        int cnt = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                cnt++;
                if(cnt>1) return false;
            }
        }
        return true;
    }
}
```

### 결과
```
테스트 1 〉	통과 (0.04ms, 52.2MB)
테스트 2 〉	통과 (0.07ms, 51.7MB)
테스트 3 〉	통과 (0.19ms, 52.4MB)
테스트 4 〉	통과 (0.04ms, 52.7MB)
테스트 5 〉	통과 (0.03ms, 53.1MB)

```