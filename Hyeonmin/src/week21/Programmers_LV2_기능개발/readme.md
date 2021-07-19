## Programmers LV2 기능개발
- 스택/큐
- level2

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42586

프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.


#### 제한사항
작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
작업 진도는 100 미만의 자연수입니다.
작업 속도는 100 이하의 자연수입니다.
배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
<br><br>

###  💡 풀이

변수
`Queue<Node> queue` : 각 작업을 순서대로 저장할 큐
`Queue<Integer> resultQueue` : 작업이 완료되었을 때 배포되는 기능의 수를 저장할 큐
`static class Node` : 작업 진도와 개발 속도를 멤버변수로 갖는 클래스


<br>

`progresses`와 `speeds`배열의 데이터를 각각의 `Node`클래스로 만들어서 순서대로 `queue`에 저장한다 

```java
		Queue<Node> queue = new LinkedList<>();
		Queue<Integer> resultQueue = new LinkedList<>();
		
		for (int i = 0; i < speeds.length; i++) {
			queue.offer(new Node(progresses[i], speeds[i]));
		}
```

모든 기능이 개발될 때 까지 while문을 반복한다. `queue`의 가장 앞에 있는 데이터를 peek()해서 현재 시간에 해당 작업의 진행 정도를 파악한다.
진행 정도가 100보다 작으면 해당 기능이 개발되는 시간으로 `time`값을 변경하고 while문을 벗어난다
진행 정도가 100보다 크면 큐에서 삭제하고 `count`의 숫자를 증가시킨다.

안쪽에 있는 while문이 종료가 되었을 떄 `count`가 0이 아닌 경우 배포되는 기능이 있는 것이므로 해당 값을 `resultQueue`에 저장한다.

```java
while(!queue.isEmpty()) {
			int count = 0;
			
			while(!queue.isEmpty()) {
				Node node = queue.peek();
				int num = node.progress + node.speed*time;
				
				if(num < 100) {
					time = (100-node.progress) % node.speed == 0 ? (100 - node.progress)/node.speed : (100 - node.progress)/node.speed +1;
					
					break;
				}
				
				queue.poll();
				count++;
				
			}
			
			if(count != 0)
				resultQueue.add(count);
		}
```

모든 작업들이 종료 되었으면 `reulstQueue`에 있는 데이터들로 배열을 생성하고 리턴한다.

```java
		int[] answer = new int[resultQueue.size()];
		
		for (int i = 0; i < answer.length; i++) {
			answer[i] = resultQueue.poll();
		}
		
		return answer;
```


<br><br>

###  💡 소스코드
```java
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_LV2_기능개발 {
	
	//작업 진도와 개발 속도를 멤버변수로 갖음 
	static class Node{
		int progress;
		int speed;
		
		public Node(int progress, int speed) {
			super();
			this.progress = progress;
			this.speed = speed;
		}
	}
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		
		int[] result = solution(progresses, speeds);
		
		for (int i : result) {
			System.out.print(i+" ");
		}
	}
	
	static int[] solution(int[] progresses, int[] speeds) {
		//각 작업을 순서대로 저장할 큐
		Queue<Node> queue = new LinkedList<>();
		//작업이 완료되었을 때 배포되는 기능의 수를 저장할 큐
		Queue<Integer> resultQueue = new LinkedList<>();
		
		//큐에 각 작업 진도와 개발속도를 저장
		for (int i = 0; i < speeds.length; i++) {
			queue.offer(new Node(progresses[i], speeds[i]));
		}
		
		//경과 시간을 저장할 변수
		int time = 1;
		
		//작업이 남아있는 동안 반복
		while(!queue.isEmpty()) {
			//완료된 작업의 수
			int count = 0;
			
			while(!queue.isEmpty()) {
				//가장 앞에 남아있는 기능
				Node node = queue.peek();
				//현재 시간에 기능의 작업 정도
				int num = node.progress + node.speed*time;
				
				//100퍼센트가 넘지 않은 경우
				if(num < 100) {
					//현재 작업이 완료되는 시간
					time = (100-node.progress) % node.speed == 0 ? (100 - node.progress)/node.speed : (100 - node.progress)/node.speed +1;
					
					break;
				}
				
				//100퍼센트가 넘은 경우 작업 목록에서 제거
				queue.poll();
				//완료된 기능수 증가
				count++;
				
			}
			
			//완료된 기능이 있는 경우 결과에 배포된 기능의 수 저장  
			if(count != 0)
				resultQueue.add(count);
		}
		
		//배포된 결과를 저장한 큐의 정보를 배열로 저장
		int[] answer = new int[resultQueue.size()];
		
		for (int i = 0; i < answer.length; i++) {
			answer[i] = resultQueue.poll();
		}
		
		return answer;
	}
}



```


<br>


테스트 1 〉	통과 (0.38ms, 52.2MB)
테스트 2 〉	통과 (0.53ms, 52.3MB)
테스트 3 〉	통과 (0.46ms, 52MB)
테스트 4 〉	통과 (0.41ms, 53.2MB)
테스트 5 〉	통과 (0.35ms, 52.9MB)
테스트 6 〉	통과 (0.39ms, 52MB)
테스트 7 〉	통과 (0.43ms, 53MB)
테스트 8 〉	통과 (0.37ms, 52MB)
테스트 9 〉	통과 (0.45ms, 52.7MB)
테스트 10 〉	통과 (0.43ms, 53.2MB)
테스트 11 〉	통과 (0.25ms, 53.8MB)