## Programmers Lv3 순위
- 그래프
- level3



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/49191

n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다. 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다. 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다. 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.

선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.

#### 제한사항
선수의 수는 1명 이상 100명 이하입니다.  
경기 결과는 1개 이상 4,500개 이하입니다.  
results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.  
모든 경기 결과에는 모순이 없습니다.  
 
#### 입출력 예
n = 5
results = [[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]	

return = 2

2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.

<br><br>

###  💡 풀이


- `int answer` : 정확하게 순위를 매길 수 있는 선수의 수   
- `ArrayList<Integer>[] winList` : 1~n번  선수가 이긴 선수 리스트  
- `ArrayList<Integer>[] loseList` : 1~n번 선수가 진 선수 리스트
- `int getWinCnt(int idx,int n)` : idx 선수가 이긴 선수의 수를 구하는 bfs 메소드
- `int getLoseCnt(int idx,int n)` : idx 선수가 진 선수의 수를 구하는 bfs 메소드

<br><br>

BFS로 풀었습니다.  
이 문제에서 구해야 하는 것은 **정확하게 순위를 매길 수 있는 선수의 수** 입니다.  
i번 선수의 순위를 구하려면, 모든 선수에 대해 i번 선수가 이기는지, 지는지 알 수 있어야 합니다.  
이는 i번 선수가 n-1번의 결투를 해야 한다는 뜻입니다.  

즉 **n명의 선수가 있을 때, i번 선수의 `winCnt(이긴 횟수)`, `loseCnt(진 횟수)`의 합이 n-1이어야 합니다.**

<br><br>

먼저 경기 결과가 저장된 results 배열을 탐색하여   
각 선수의 승,패 결과를 `winList`, `loseList`에 저장했습니다.  
```
for (int i = 0; i < results.length; i++) {
	int A = results[i][0];
	int B = results[i][1];
			
	winList[A].add(B); //A가 B선수를 이겼다.
	loseList[B].add(A); //B가 A선수에게 졌다.
}
```
<br>

그리고 for문으로 idx=1부터 idx=n번 선수에 대해 winCnt와 loseCnt를 구했습니다.  
각각의 cnt는 bfs와 위에서 구한 winList, loseList를 이용하여 구할 수 있었습니다.  

구한 `winCnt+loseCnt`의 합이 `n-1`이라면 `answer`를 하나 증가시킵니다.  
<br><br>

###  💡 소스코드
```
import java.util.*;

class Solution {
    static ArrayList<Integer>[] winList;
	static ArrayList<Integer>[] loseList;
    
    public int solution(int n, int[][] results) {
		int answer = 0;  //정확하게 순위를 매길 수 있는 선수
		
        winList = new ArrayList[n+1]; //1~n번  선수가 이긴 선수 리스트
        loseList = new ArrayList[n+1]; //1~n번 선수가 진 선수 리스트
        
        for (int i = 0; i <= n; i++) {
			winList[i] = new ArrayList<>();
			loseList[i] = new ArrayList<>();
		}
        
        //경기 결과 승패 저장
        for (int i = 0; i < results.length; i++) {
			int A = results[i][0];
			int B = results[i][1];
			
			winList[A].add(B); //A가 B선수를 이겼다.
			loseList[B].add(A); //B가 A선수에게 졌다.
		}
        
        
        for (int idx = 1; idx <= n; idx++) {
        	int winCnt = getWinCnt(idx, n);
        	int loseCnt = getLoseCnt(idx, n);

        	if(winCnt + loseCnt == n-1) answer++; 
            //이긴선수 + 진 선수 = n-1이면 모든 경기의 결과를 알수 있으므로 등수를 구할 수 있다.
		}

        return answer;
    }
	
    //idx 선수가 이긴 선수의 수를 구하는 bfs 메소드
	private static int getWinCnt(int idx,int n) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		int winCnt = 0;
		
		queue.add(idx);
		visited[idx]=true;
		
		while(!queue.isEmpty()) {
			int playerNum = queue.poll();
			
			for (int i = 0; i < winList[playerNum].size(); i++) {
				int tgtNum = winList[playerNum].get(i);
				if(visited[tgtNum]) continue;
				visited[tgtNum] = true;
				queue.add(tgtNum);
				winCnt++;
			}
		}
		return winCnt;
	}
	
    //idx 선수가 진 선수의 수를 구하는 bfs 메소드
	private static int getLoseCnt(int idx,int n) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		int loseCnt = 0;
		
		queue.add(idx);
		visited[idx]=true;
		
		while(!queue.isEmpty()) {
			int playerNum = queue.poll();
			
			for (int i = 0; i < loseList[playerNum].size(); i++) {
				int tgtNum = loseList[playerNum].get(i);
				if(visited[tgtNum]) continue;
				visited[tgtNum] = true;
				queue.add(tgtNum);
				loseCnt++;
			}
		}
		return loseCnt;
	}
}
```

<br>

### 결과
정확성  테스트  
테스트 1 〉	통과 (0.25ms, 52.7MB)  
테스트 2 〉	통과 (0.27ms, 52.2MB)  
테스트 3 〉	통과 (0.62ms, 53.4MB)  
테스트 4 〉	통과 (0.30ms, 52.1MB)  
테스트 5 〉	통과 (2.84ms, 52.6MB)  
테스트 6 〉	통과 (5.42ms, 53.7MB)  
테스트 7 〉	통과 (12.77ms, 53.7MB)  
테스트 8 〉	통과 (27.11ms, 57.2MB)  
테스트 9 〉	통과 (28.51ms, 54.7MB)  
테스트 10 〉	통과 (29.21ms, 55.3MB)  

채점 결과  
정확성: 100.0  
합계: 100.0 / 100.0  