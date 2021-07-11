## Programmers Lv3 디스크 컨트롤러
- 힙
- level3

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42627

각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)


#### 제한사항
jobs의 길이는 1 이상 500 이하입니다.
jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
<br><br>

###  💡 풀이

변수
`PriorityQueue<Integer> minPq` : 최댓값을 위한 우선순위 큐
`PriorityQueue<Integer> maxPq` : 최솟값을 위한 우선순위 큐


<br>

우선순위 큐를 사용해서 풀었습니다. 

최대힙 `maxPq`와 최소힙 `minPq`를 만들어 
명령어가 `D 1`인 경우 `maxPq`에서 값을 하나 꺼낸 후 그 값을 `minPq`에서도 제거한다
명령어가 `D -1`인 경우도 동일하게 `minPq`에서 값을 하나 꺼낸 후 그 값을 `maxPq`에서도 제거한다
제거 할 값이 없는 경우는 다음 명령을 실행한다

```
			//최댓값 삭제
			if(str.equals("D 1")) {
				if(minPq.isEmpty()) continue;
				
				int num = maxPq.poll();
				minPq.remove(num);
			}
			//최솟값 삭제
			else if(str.equals("D -1")) {
				if(minPq.isEmpty()) continue;
				
				int num = minPq.poll();
				maxPq.remove(num);
			}
```

명령어가 `D 1`혹은 `D -1`이 아닌 경우는 입력명령어로  주어진 값을 `minPq`와 `maxPq`에 넣어준다

```
			//값 입력
			else{
				String[] split = str.split(" ");
				int num = Integer.parseInt(split[1]);
				
				minPq.offer(num);
				maxPq.offer(num);
			}
```

모든 명령 실행이 끝난후 `minPq`에 값이 남아있으면 min은 `minPq`에서, max는 `maxPq`에서 값을 꺼내어 `answer`배열은 만들어 반환한다

```
		int min = 0;
		int max = 0;
		
		//남은 숫자가 있는 경우
		if(!minPq.isEmpty()){
			min = minPq.poll();
			max = maxPq.poll();
		}
		
		int[] answer = new int[] {max, min};
		
		return answer;
```


<br><br>

###  💡 소스코드
```
import java.util.Collections;
import java.util.PriorityQueue;

public class Programmers_LV3_이중우선순위큐 {
	public static void main(String[] args) {
		String[] operations = {
				"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"
		};
		
		int[] result = solution(operations);
		
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+" ");
		}
	}
	
	static int[] solution(String[] operations) {
		//최소
		PriorityQueue<Integer> minPq = new PriorityQueue<>();
		//최대
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
		
		//명령
		for (int i = 0; i < operations.length; i++) {
			String str = operations[i];
			
			//최댓값 삭제
			if(str.equals("D 1")) {
				if(minPq.isEmpty()) continue;
				
				int num = maxPq.poll();
				minPq.remove(num);
			}
			//최솟값 삭제
			else if(str.equals("D -1")) {
				if(minPq.isEmpty()) continue;
				
				int num = minPq.poll();
				maxPq.remove(num);
			}
			//값 입력
			else{
				String[] split = str.split(" ");
				int num = Integer.parseInt(split[1]);
				
				minPq.offer(num);
				maxPq.offer(num);
			}
			
		}
		
		int min = 0;
		int max = 0;
		
		//남은 숫자가 있는 경우
		if(!minPq.isEmpty()){
			min = minPq.poll();
			max = maxPq.poll();
		}
		
		int[] answer = new int[] {max, min};
		
		return answer;
	}
}



```


<br>


테스트 1 〉	통과 (0.55ms, 52MB)
테스트 2 〉	통과 (0.57ms, 52.5MB)
테스트 3 〉	통과 (0.73ms, 51.8MB)
테스트 4 〉	통과 (0.54ms, 52.6MB)
테스트 5 〉	통과 (0.56ms, 52.2MB)
테스트 6 〉	통과 (0.68ms, 52.3MB)
