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
<br><br>

###  💡 풀이

변수
`boolean[][] matchResult` : 각 선수의 경기 결과를 저장하는 배열. [A][B]는 A선수가 B선수를 이긴지 여부
`boolean[] checked` : dfs 실행 시 방문 여부를 저장하는 boolean 배열 
`count` : 순위를 매길 수 있는 선수의 수

<br>

DFS로 풀었습니다. 

우선 results 배열에 담긴 데이터를 통해서 matchResult 배열에 각 선수의 승리여부를 저장합니다.

```
		for (int i = 0; i < results.length; i++) {
			int win = results[i][0];
			int lose = results[i][1];
			
			matchResult[win][lose] = true;
		}
```

for문을 통해서 각 선수들마다 `checkRank`메소드를 통해서 DFS를 수행합니다.이 때 이 선수보다 약한 선수의 수를 구하는 `calcWinCount`메소드와 더 강한 선수를 구하는 `calcLoseCount`메소드를 실행하여 그 합을 결과값으로 리턴합니다. 

```
	static int checkRank(int idx) {
		int win = 0;
		int lose = 0;
		checked[idx] = true;
		test = idx;
		
		for (int i = 1; i < matchResult.length; i++) {
			if(!checked[i] && i != idx ) {
				if(matchResult[idx][i]) {
					win += calcWinCount(i, 0);
				}
				else if(matchResult[i][idx]) {
					lose += calcLoseCount(i, 0);
				}
			}
		}
		
		return win+lose;
	}
```

이 때 `checkRank`메소드의 결과를 `num`변수에 저장하여 `num`의 값이 전체 선수의 수보다 1작은 `n-1`인 경우는 순위를 매길 수 있는 경우이므로 `count`의 값을 증가시킵니다.

```
		for (int i = 1; i <= n; i++) {
			checked = new boolean[n+1];
			int num = checkRank(i);
			
			if(num == n-1) {
				count++;
			}
		}
```


모든 선수들에 대해서 `checkRank`메소드를 수행했으면 `count`값을 최종 결과로 리턴합니다.


<br><br>

###  💡 소스코드
```
public class Programmers_LV3_순위 {
	static boolean[][] matchResult;
	static boolean[] checked;
	static int count;
	
	public static void main(String[] args) {
		int n = 5;
		int[][] results = {
				{1, 4},
				{4, 2},
				{2, 5},
				{5, 3}
		};
		
		int result = solution(n, results);
		
		System.out.println(result);
	}
	
	static int solution(int n, int[][] results) {
		matchResult = new boolean[n+1][n+1];
		
		for (int i = 0; i < results.length; i++) {
			int win = results[i][0];
			int lose = results[i][1];
			
			matchResult[win][lose] = true;
		}
		
		//각 선수들에 대해서 순위를 매길 수 있는지 여부 확인
		for (int i = 1; i <= n; i++) {
			checked = new boolean[n+1];
			int num = checkRank(i);

			//순위를 매길 수 있는 경우
			if(num == n-1) {
				count++;
			}
		}
		
		return count;
		
	}
	
	static int checkRank(int idx) {
		//이 선수가 이길 수 있는 선수 수
		int win = 0;
		//이 선수가 이길 수 없는 선수 수
		int lose = 0;
		checked[idx] = true;
		
		for (int i = 1; i < matchResult.length; i++) {
			//자신을 제외
			if(!checked[i] && i != idx ) {
				//자신이 이기는 경우
				if(matchResult[idx][i]) {
					win += calcWinCount(i, 0);
				}
				//자신이 지는 경우
				else if(matchResult[i][idx]) {
					lose += calcLoseCount(i, 0);
				}
			}
		}
		
		return win+lose;
	}
	
	static int calcWinCount(int idx, int num) {
		num++;
		checked[idx] = true;
		
		//아직 확인하지 않은 선수들 중에서 이길 수 있는 선수가 있는 경우 를 확인
		for (int i = 1; i < matchResult.length; i++) {
			if(!checked[i] && i != idx && matchResult[idx][i]) {
				num += calcWinCount(i, 0);
			}
		}
		
		return num;
	}
	
	static int calcLoseCount(int idx, int num) {
		num++;
		checked[idx] = true;
		
		//아직 확인하지 않은 선수들 중에서 자신을 이기는 선수가 있는 지 확인
		for (int i = 1; i < matchResult.length; i++) {
			if(!checked[i] && i != idx && matchResult[i][idx]) {
				num += calcLoseCount(i, 0);
			}
		}
		
		return num;
	}

}


```


<br>