## Programmers Lv3 정수 삼각형
- DP
- level3

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/43105

삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.

삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.


#### 제한사항
삼각형의 높이는 1 이상 500 이하입니다.
삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
<br><br>

###  💡 풀이

변수
`List<List<Integer>> list` : 삼각형의 각 높이별 숫자를 저장할 자료형
`int[][] triangle` : 정수 삼각형 각 높이의 숫자를 배열로 저장한 데이터


<br>

삼각형의 가장 아래에 있는 위치에서 부터 대각선 위로 올라오면서 다음 높이의 각 위치에 있던 숫자와 그 위치에 올 수 있는 숫자들의 합을 구해서 그 중 제일 큰 숫자를 저장하고 그 다음 높이에서 동일하게 진행한다

삼각형의 높이만큼 각 위치의 숫자들을 저장할 2중 list를 만들고 할당한다

```java
//삼각형의 높이
		int size = triangle.length;
		//각 높이별 숫자를 저장할 자료형
		List<List<Integer>> list = new ArrayList<>();
		
		//각 높이마다 리스트 추가
		for (int i = 0; i < size; i++) {
			list.add(new ArrayList<Integer>());
		}
```

`list`의 맨 아래 리스트에는 `triangle`의 최하단에 있는 숫자들을 저장한다

```java
		//맨 아래 리스트에 삼각형 맨 아래에 있는 숫자 저장
		for (int i = 0; i < triangle[size-1].length; i++) {
			list.get(size-1).add(triangle[size-1][i]);
		}
```

각 높이별로 그 높이에 있는 숫자들 만큼 반복을 한다

```java
		//각 높이별로 그 바로 위 높이의 각 위치에 있는 숫자의 합을 구해 더 큰 수를 해당 위치에 저장
		for (int i = size-1; i > 0; i--) {
			//i높이의 리스트에 있는 수자의 수만큼 반복
			for (int j = 0; j < list.get(i).size(); j++) {
```

`i`높이`j`번째에 저장된 숫자들 에서 왼쪽 대각선 위, 오른쪽 대각선 위로 이동하는 경우 `i-1`높이의 `idx`번째 위치에 가능한 가장 큰 수를 저장한다

```java
//i높이 j번쨰 숫자
				int num = list.get(i).get(j);
				
				//왼쪽 대각선 위로 올라가는 경우
				int idx = j-1;
				
				if(idx > -1) {
					
					//왼쪽 대각선 위에 아직 숫자가 저장되지 않은 경우 그 위치에 있는 triangle배열의 숫자와의 합을 저장
					if(list.get(i-1).size() <= idx) {
						list.get(i-1).add(num + triangle[i-1][idx]);
					}
					//왼쪽 대각선 위에 숫자가 저장되어 있는 경우 그 위치에 있는 숫자와 비교해 더 큰것을 저장
					else {
						int temp = list.get(i-1).get(idx);
						
						if(temp < num + triangle[i-1][idx]) {
							list.get(i-1).set(idx, num + triangle[i-1][idx]);
						}
					}
				}
				
				//오른쪽 대각선 위로 올라가는 경우
				idx = j;
				
				if(idx < triangle[i-1].length) {
					
					//오른쪽 대각선 위에 아직 숫자가 저장되지 않은 경우 그 위치에 있는 triangle배열의 숫자와의 합을 저장
					if(list.get(i-1).size() <= idx) {
						list.get(i-1).add(num + triangle[i-1][idx]);
					}
					//오른쪽 대각선 위에 숫자가 저장되어 있는 경우 그 위치에 있는 숫자와 비교해 더 큰것을 저장
					else {
						int temp = list.get(i-1).get(idx);
						
						if(temp < num + triangle[i-1][idx]) {
							list.get(i-1).set(idx, num + triangle[i-1][idx]);
						}
					}
				}
```

최상단에 위치한 숫자를 반환한다

```java
			//삼각형 맨위에 저장된 숫자 리턴
		return list.get(0).get(0);
```


<br><br>

###  💡 소스코드
```java
import java.util.ArrayList;
import java.util.List;

public class Programmers_LV3_정수삼각형 {
	public static void main(String[] args) {
		int[][] triangle = {
				{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}
		};
		
		System.out.println(solution(triangle));
	}
	
	static int solution(int[][] triangle) {
		//삼각형의 높이
		int size = triangle.length;
		//각 높이별 숫자를 저장할 자료형
		List<List<Integer>> list = new ArrayList<>();
		
		//각 높이마다 리스트 추가
		for (int i = 0; i < size; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		//맨 아래 리스트에 삼각형 맨 아래에 있는 숫자 저장
		for (int i = 0; i < triangle[size-1].length; i++) {
			list.get(size-1).add(triangle[size-1][i]);
		}
		
		//각 높이별로 그 바로 위 높이의 각 위치에 있는 숫자의 합을 구해 더 큰 수를 해당 위치에 저장
		for (int i = size-1; i > 0; i--) {
			//i높이의 리스트에 있는 수자의 수만큼 반복
			for (int j = 0; j < list.get(i).size(); j++) {
				//i높이 j번쨰 숫자
				int num = list.get(i).get(j);
				
				//왼쪽 대각선 위로 올라가는 경우
				int idx = j-1;
				
				if(idx > -1) {
					
					//왼쪽 대각선 위에 아직 숫자가 저장되지 않은 경우 그 위치에 있는 triangle배열의 숫자와의 합을 저장
					if(list.get(i-1).size() <= idx) {
						list.get(i-1).add(num + triangle[i-1][idx]);
					}
					//왼쪽 대각선 위에 숫자가 저장되어 있는 경우 그 위치에 있는 숫자와 비교해 더 큰것을 저장
					else {
						int temp = list.get(i-1).get(idx);
						
						if(temp < num + triangle[i-1][idx]) {
							list.get(i-1).set(idx, num + triangle[i-1][idx]);
						}
					}
				}
				
				//오른쪽 대각선 위로 올라가는 경우
				idx = j;
				
				if(idx < triangle[i-1].length) {
					
					//오른쪽 대각선 위에 아직 숫자가 저장되지 않은 경우 그 위치에 있는 triangle배열의 숫자와의 합을 저장
					if(list.get(i-1).size() <= idx) {
						list.get(i-1).add(num + triangle[i-1][idx]);
					}
					//오른쪽 대각선 위에 숫자가 저장되어 있는 경우 그 위치에 있는 숫자와 비교해 더 큰것을 저장
					else {
						int temp = list.get(i-1).get(idx);
						
						if(temp < num + triangle[i-1][idx]) {
							list.get(i-1).set(idx, num + triangle[i-1][idx]);
						}
					}
				}
			}
		}
		
		//삼각형 맨위에 저장된 숫자 리턴
		return list.get(0).get(0);
	}
}



```


<br>


정확성  테스트
테스트 1 〉	통과 (0.12ms, 52.9MB)
테스트 2 〉	통과 (0.11ms, 52.3MB)
테스트 3 〉	통과 (0.53ms, 52.1MB)
테스트 4 〉	통과 (0.73ms, 52MB)
테스트 5 〉	통과 (3.07ms, 52.5MB)
테스트 6 〉	통과 (3.07ms, 53.3MB)
테스트 7 〉	통과 (3.19ms, 52.6MB)
테스트 8 〉	통과 (0.97ms, 52.8MB)
테스트 9 〉	통과 (0.08ms, 52.5MB)
테스트 10 〉	통과 (0.59ms, 52.4MB)

효율성  테스트
테스트 1 〉	통과 (46.49ms, 63.4MB)
테스트 2 〉	통과 (55.48ms, 62.2MB)
테스트 3 〉	통과 (60.38ms, 65.2MB)
테스트 4 〉	통과 (53.86ms, 64.5MB)
테스트 5 〉	통과 (43.40ms, 63.5MB)
테스트 6 〉	통과 (54.51ms, 65.8MB)
테스트 7 〉	통과 (49.54ms, 64.8MB)
테스트 8 〉	통과 (50.94ms, 62.4MB)
테스트 9 〉	통과 (58.03ms, 63.6MB)
테스트 10 〉	통과 (51.63ms, 63.3MB)