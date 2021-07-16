## Programmers Lv3 베스트앨번
- 해시
- level3

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42579

스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.


#### 제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
<br><br>

###  💡 풀이

변수
`class Music` : 노래에 대한 클래스. 노래 인덱스와 재생 횟수를 저장
`class Genre` : 장르에 대한 클래스. 장르 종류와 해당 장르 재생횟수를 저장
`Map<String, List<Music>> map` : 각 장르별를 키로 노래 종류를 밸류로 갖음
`Map<String, Integer> playMap` : 각 장르별 총 재생횟수를 밸류로 갖음
`PriorityQueue<Genre> genrePq` : 장르를 촐 재생횟수로 정렬하는 우선순위 큐
`Queue<Integer> musicQueue` : 재생 순서대로 노래의 인덱스를 저장할 큐


<br>

가장 먼저 각 장르별 총 재생횟수와 노래 리스트를 저장할 맵을 2개 만든다

```java
		//각 장르별 노래의 리스트를 저장할 맵
		Map<String, List<Music>> map = new HashMap<>();
		//각 장르별 총 재생횟수를 저장할 맵
		Map<String, Integer> playMap = new HashMap<>();
```

배열에 있는 모든 노래들에 대해서 이미 있는 경우는 기존 맵에 데이터를 변경, 없는 경우 새로 만들어서 각 장르별 총 재생횟수와 노래 리스트를 갱신한다

```java
				//각 노래에 대해서
		for (int i = 0; i < genres.length; i++) {
			String genre = genres[i];
			int play = plays[i];
			
			//이미 있는 장르인 경우
			if(map.containsKey(genre)) {
				//그 장르 노래 목록에 추가
				List<Music> list = map.get(genre);
				list.add(new Music(i, play));
				map.put(genre, list);
				//그 장르 재생횟수를 변경
				playMap.put(genre, playMap.get(genre)+play);
			}
			//없는 장르인 경우
			else {
				//새로 리스트를 만들어서 노래 추가 후 맵에 저장
				List<Music> list = new ArrayList<Music>();
				list.add(new Music(i, play));
				map.put(genre, list);
				//재생횟수 저장
				playMap.put(genre, play);
			}
		}
```

갱신이 끝나면 `map`에 저장된 각 장르별 재생 획수를 `Genre`클래스로 만들어서 총 재생횟수로 정렬시키는 우선순위 큐에 넣는다

```java
		//각 장르들이 총 재생횟수로 정렬이 되도록 우선순위 큐에 저장
		for (Entry<String, Integer> genre : playMap.entrySet()) {
			genrePq.offer(new Genre(genre.getKey(), genre.getValue()));
		}
```

우선순위 큐에 있는 모든 데이터에 대해서 가장 만이 재생된 장르 순서대로 `playMap`에서 해당 장르의 노래 리스트를 가져온다.
노래 리스트를 재생이 많이된 순서로 정렬을 하고 `musicQueue`에 각 장르마다 최대 2개의 노래 인덱스를 재생이 많이된 순서로 넣는다. 

```java
		//큐가 빌 때 까지
		while(!genrePq.isEmpty()) {
			Genre genre = genrePq.poll();
			//가장 많이 재생된 순서대로 장르의 노래 리스트를 받아옴
			List<Music> list = map.get(genre.genre);
			//노래리스트를 노래 재생 횟수로 정렬
			Collections.sort(list);
			
			//각 장르별로 최대 2개까지 많이 재생된 순서로 큐에 저장
			for (int i = 0; i < 2 && i < list.size(); i++) {
				musicPueue.offer(list.get(i).index);
			}
			
		}
```

`musicQueue`에 저장된 데이터 크기만큼 `int[] answer`배열을 만들고 큐에 넣은 순서대로 `answer`의 0인덱스부터 데이터를 저장한다.

```java
			//큐의 크기만큼 배열 생성
		int size = musicQueue.size();
		
		int i = 0;
		int [] answer = new int[size];
		
		//배열에 규칙 순서대로 노래의 인덱스 저장
		while(!musicQueue.isEmpty()) {
			answer[i++] = musicQueue.poll();
		}
		
		return answer;
```


<br><br>

###  💡 소스코드
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_LV3_베스트앨범 {
	
	static class Music implements Comparable<Music>{
		int index;
		int play;
		
		public Music(int index, int play) {
			super();
			this.index = index;
			this.play = play;
		}

		@Override
		public int compareTo(Music o) {
			return o.play - this.play;
		}
	}
	
	static class Genre implements Comparable<Genre>{
		String genre;
		int totalPlay;

		public Genre(String genre, int totalPlay) {
			super();
			this.genre = genre;
			this.totalPlay = totalPlay;
		}

		@Override
		public int compareTo(Genre o) {
			return o.totalPlay - this.totalPlay;
		}
	}
	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		int[] result = solution(genres, plays);
		
		for (int i : result) {
			System.out.println(i);
		}
	}
	
	static int[] solution(String[] genres, int[] plays){
		//각 장르별 노래의 리스트를 저장할 맵
		Map<String, List<Music>> map = new HashMap<>();
		//각 장르별 총 재생횟수를 저장할 맵
		Map<String, Integer> playMap = new HashMap<>();
		
		//각 노래에 대해서
		for (int i = 0; i < genres.length; i++) {
			String genre = genres[i];
			int play = plays[i];
			
			//이미 있는 장르인 경우
			if(map.containsKey(genre)) {
				//그 장르 노래 목록에 추가
				List<Music> list = map.get(genre);
				list.add(new Music(i, play));
				map.put(genre, list);
				//그 장르 재생횟수를 변경
				playMap.put(genre, playMap.get(genre)+play);
			}
			//없는 장르인 경우
			else {
				//새로 리스트를 만들어서 노래 추가 후 맵에 저장
				List<Music> list = new ArrayList<Music>();
				list.add(new Music(i, play));
				map.put(genre, list);
				//재생횟수 저장
				playMap.put(genre, play);
			}
		}
		
		PriorityQueue<Genre> genrePq = new PriorityQueue<>();
		Queue<Integer> musicQueue = new LinkedList<>();
		
		//각 장르들이 총 재생횟수로 정렬이 되도록 우선순위 큐에 저장
		for (Entry<String, Integer> genre : playMap.entrySet()) {
			genrePq.offer(new Genre(genre.getKey(), genre.getValue()));
		}
		
		//큐가 빌 때 까지
		while(!genrePq.isEmpty()) {
			Genre genre = genrePq.poll();
			//가장 많이 재생된 순서대로 장르의 노래 리스트를 받아옴
			List<Music> list = map.get(genre.genre);
			//노래리스트를 노래 재생 횟수로 정렬
			Collections.sort(list);
			
			//각 장르별로 최대 2개까지 많이 재생된 순서로 큐에 저장
			for (int i = 0; i < 2 && i < list.size(); i++) {
				musicQueue.offer(list.get(i).index);
			}
			
		}
		
		//큐의 크기만큼 배열 생성
		int size = musicQueue.size();
		
		int i = 0;
		int [] answer = new int[size];
		
		//배열에 규칙 순서대로 노래의 인덱스 저장
		while(!musicQueue.isEmpty()) {
			answer[i++] = musicQueue.poll();
		}
		
		return answer;
	}
}




```


<br>


테스트 1 〉	통과 (1.20ms, 52.6MB)
테스트 2 〉	통과 (1.18ms, 53.6MB)
테스트 3 〉	통과 (1.21ms, 51.8MB)
테스트 4 〉	통과 (1.31ms, 53.1MB)
테스트 5 〉	통과 (1.40ms, 54.6MB)
테스트 6 〉	통과 (1.44ms, 52.2MB)
테스트 7 〉	통과 (1.30ms, 53MB)
테스트 8 〉	통과 (1.29ms, 52.2MB)
테스트 9 〉	통과 (1.21ms, 52.1MB)
테스트 10 〉	통과 (1.49ms, 52.3MB)
테스트 11 〉	통과 (1.29ms, 52.6MB)
테스트 12 〉	통과 (1.36ms, 52.8MB)
테스트 13 〉	통과 (1.37ms, 52.8MB)
테스트 14 〉	통과 (1.63ms, 52.4MB)
테스트 15 〉	통과 (1.25ms, 53MB)