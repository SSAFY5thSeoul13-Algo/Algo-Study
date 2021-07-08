## Programmers Lv3 더 맵게
- 그래프
- level3

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42626

모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.

```
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
```

모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.


#### 제한사항
scoville의 길이는 2 이상 1,000,000 이하입니다.
K는 0 이상 1,000,000,000 이하입니다.
scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
<br><br>

###  💡 풀이

변수
`PriorityQueue<Integer> pq` : 현재 만들어진 스코빌 지수를 저장할 우선순위 큐
`boolean canMake` : 모든 스코빌 지수가 K이상 되는지 여부
`int count` : 음식을 섞은 횟수

<br>

우선순위 큐를 사용해서 풀었습니다. 

우선 `scoville` 배열에 담긴 데이터를 오름차순 정렬을 하는 우선순위 큐 `pq`에 저장을 합니다

```
		for (int i = 0; i < scoville.length; i++) {
			pq.offer(scoville[i]);
		}
```

while문을 실행해 `pq`에서 가장 작은 두개의 수를 각각 `first`와 `second`에 저장합니다. 그 후에 `pq`에 `first`와 `second`의 스코빌 지수를 섞을 때의 값을 `pq`에 저장하고 `count`를 증가 시킵니다.

`first`의 값이 `K`이상인 경우는 남은 스코빌 지수들도 모두 `K`이상입니다.
따라서 이 과정을 `pq`에 더이상 값이 없거나 `first`의 값이 `K`와 같거나 클 때까지 반복합니다.

```
		while(!pq.isEmpty()) {
			//가장 낮은 스코빌 지수
			int first = pq.poll();
			
			//가장 낮은게 목표 스코빌 지수보다 높은 경우
			if(first >= K) {
				canMake = true;
				break;
			}
			//더 남은 음식이 없는 경우
			else if(pq.isEmpty()) {
				break;
			}
			
			//두번째로 낮은 스코빌 지수
			int second = pq.poll();
			
			//두 개를 섞은 스코빌지수를 큐에 저장
			pq.offer(first + second*2);
			//섞은 횟수 증가
			count++;
			
		}
```

이 때 `canMake`값에 따라서 모든 스코빌 지수가 `K`이상인지에 따라서 `count`를 리턴하거나 `-1`을 리턴합니다

```
		//모든 스코빌지수가 K이상인 경우 섞은 횟수를 리턴
		if(canMake) {
			return count;
		}
		//K이상으로 만들지 못하면 -1 리턴
		else {
			return -1;
		}
```


<br><br>

###  💡 소스코드
```
public class Programmers_LV2_더맵게 {

	public static void main(String[] args) {
		int K = 7;
		int[] scoville = {1, 2, 3, 9, 10, 12};
		
		int result = solution(scoville, K);
		
		System.out.println(result);

	}
	
	static int solution(int[] scoville, int K) {
		//현재 만들어진 스코빌 지수를 저장할 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		//우선순위 큐에 값 넣음
		for (int i = 0; i < scoville.length; i++) {
			pq.offer(scoville[i]);
		}
		
		//모든 스코빌 지수가 K이상 되는지 여부
		boolean canMake = false;
		//음식을 섞은 횟수
		int count = 0;
		
		while(!pq.isEmpty()) {
			//가장 낮은 스코빌 지수
			int first = pq.poll();
			
			//가장 낮은게 목표 스코빌 지수보다 높은 경우
			if(first >= K) {
				canMake = true;
				break;
			}
			//더 남은 음식이 없는 경우
			else if(pq.isEmpty()) {
				break;
			}
			
			//두번째로 낮은 스코빌 지수
			int second = pq.poll();
			
			//두 개를 섞은 스코빌지수를 큐에 저장
			pq.offer(first + second*2);
			//섞은 횟수 증가
			count++;
			
		}
		
		//모든 스코빌지수가 K이상인 경우 섞은 횟수를 리턴
		if(canMake) {
			return count;
		}
		//K이상으로 만들지 못하면 -1 리턴
		else {
			return -1;
		}
	}

}


```


<br>

