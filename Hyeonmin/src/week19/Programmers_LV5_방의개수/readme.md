## Programmers Lv5 방의 개수
- 그래프
- level5

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/49190

원점(0,0)에서 시작해서 아래처럼 숫자가 적힌 방향으로 이동하며 선을 긋습니다.

그림을 그릴 때, 사방이 막히면 방하나로 샙니다.
이동하는 방향이 담긴 배열 arrows가 매개변수로 주어질 때, 방의 갯수를 return 하도록 solution 함수를 작성하세요.


#### 제한사항
배열 arrows의 크기는 1 이상 100,000 이하 입니다.
arrows의 원소는 0 이상 7 이하 입니다.
방은 다른 방으로 둘러 싸여질 수 있습니다.
<br><br>

###  💡 풀이

변수
`int[] deltaX` : 가로방향 델타 
`int[] deltaY` : 세로방향 델타
`Set<String> nodeSet` : 지나온 점을 기록하는 Set
`Set<String> edgeSet` : 지나온 선을 기록하는 Set


<br>

방이 만들어지는 경우는 이미 지나왔던 지점을 다시 지나갈 때 그 경로가 아직 지나지 않았던 경로인 경우이다
이 경우의 횟수를 구하면 방의 개수를 구할 수 있다

각 이동 때마다 이동을 했던 경로와 점을 표시하는 String 데이터를 만든다

```
	int ny = y + deltaY[arrow];
	int nx = x + deltaX[arrow];
				
	String node = ny+"&"+nx;
	String edge1 = y+"&"+x+"&"+ny+"&"+nx;
	String edge2 = ny+"&"+nx+"&"+y+"&"+x;
```

이전에 방문을 했던 점이면 경로를 확인해서 처음 지나는 경로인 경우 방의 수를 증가신다

```
				if(nodeSet.contains(node) && !edgeSet.contains(edge1)) {
					count++;
				}
```

이동을 할 때 지나오는 점들과 경로를 각각 `nodeSet`과 `edgeSet`에 저장한다

```
				edgeSet.add(edge1);
				edgeSet.add(edge2);
				nodeSet.add(node);
```

한번의 이동에서 위 과정을 2번 씩 반복한다
2번씩 반복하는 이유는 1번만 실행을 하면 대각선 이동중에 다른 선과 겹치게 되는 경우 생성되는 방을 체크할 수 없게 되기 때문이다

```
		for (int arrow : arrows) {
			
			for (int i = 0; i < 2; i++) {
				int ny = y + deltaY[arrow];
				int nx = x + deltaX[arrow];
				
				String node = ny+"&"+nx;
				String edge1 = y+"&"+x+"&"+ny+"&"+nx;
				String edge2 = ny+"&"+nx+"&"+y+"&"+x;
				
				if(nodeSet.contains(node) && !edgeSet.contains(edge1)) {
					count++;
				}
				
				edgeSet.add(edge1);
				edgeSet.add(edge2);
				nodeSet.add(node);
				
				x = nx;
				y = ny;
			}
		}
```


<br><br>

###  💡 소스코드
```
import java.util.HashSet;
import java.util.Set;

public class Programmers_LV5_방의개수 {
	static int[] deltaX = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] deltaY = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) {
		int[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
		System.out.println(solution(arrows));
	}
	
	static int solution(int[] arrows) {
		//지나온 점들
		Set<String> nodeSet = new HashSet<>();
		//지나온 선들
		Set<String> edgeSet = new HashSet<>();
		
		int y = 0;
		int x = 0;
		//방의 수
		int count = 0;
		
		//시작점 체크
		nodeSet.add("0&0");
		
		//모든 이동실행
		for (int arrow : arrows) {
			
			//각 이동을 동일하게 2번 실행
			for (int i = 0; i < 2; i++) {
				//이동한 y위치
				int ny = y + deltaY[arrow];
				//이동한 x위치
				int nx = x + deltaX[arrow];
				
				//방문 위치 체크
				String node = ny+"&"+nx;
				//지나온 선 체크
				String edge1 = y+"&"+x+"&"+ny+"&"+nx;
				//지나온 선 역방향 체크
				String edge2 = ny+"&"+nx+"&"+y+"&"+x;
				
				//이미 방문을 했던 위치에 왔을 때 방문한 방향이 처음인 경우 방이 생긴다
				if(nodeSet.contains(node) && !edgeSet.contains(edge1)) {
					count++;
				}
				
				//지나온 선 체크
				edgeSet.add(edge1);
				edgeSet.add(edge2);
				//점 방문체크
				nodeSet.add(node);
				
				x = nx;
				y = ny;
			}
		}
		
		
		return count;
	}

}



```


<br>


테스트 1 〉	통과 (25.86ms, 53.5MB)
테스트 2 〉	통과 (32.53ms, 54MB)
테스트 3 〉	통과 (30.82ms, 54.1MB)
테스트 4 〉	통과 (44.63ms, 55.5MB)
테스트 5 〉	통과 (96.29ms, 62.7MB)
테스트 6 〉	통과 (500.64ms, 112MB)
테스트 7 〉	통과 (84.95ms, 60MB)
테스트 8 〉	통과 (367.68ms, 101MB)
테스트 9 〉	통과 (672.11ms, 135MB)