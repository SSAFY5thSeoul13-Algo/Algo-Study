## Programmers Lv3 베스트앨범
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
<br>

#### ✔ 제한사항
- genres[i]는 고유번호가 i인 노래의 장르입니다.
- plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
- genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
- 장르 종류는 100개 미만입니다.
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
- 모든 장르는 재생된 횟수가 다릅니다.
<br>

#### ✔ 입출력 예
| genres | plays | return | 
|--|--|--|
| ["classic", "pop", "classic", "classic", "pop"] | [500, 600, 150, 800, 2500] | [4, 1, 3, 0] |

classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

- 고유 번호 3: 800회 재생
- 고유 번호 0: 500회 재생
- 고유 번호 2: 150회 재생

pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

- 고유 번호 4: 2,500회 재생
- 고유 번호 1: 600회 재생

따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

<br><br>

###  💡 풀이

`

먼저 `1. 속한 노래가 많이 재생된 장르를 먼저 수록하기` 위해 장르 별로 재생된 횟수를 저장하기 위해 `HashMap` 자료구조를 이용하였다.

그리고 `2,3`번 조건을 만족하기 위해 고유번호, 장르, 재생횟수를 같이 저장하기 위해 `Node`라는 Class를 만들고 `Node`형 `ArrayList`를 이용했다.

```java
ArrayList<Node> songList = new ArrayList<>();
//노래 정보 저장할 list
ArrayList<Integer> bestAlbum = new ArrayList<>();
//정답 저장할 list
HashMap<String, Integer> genreMap = new HashMap<>();
//장르별 재생된 횟수
```

<br>

그리고 장르 별로 재생 횟수를 내림차순 정렬하기 위해 `keySet()`을`ArrayList`에 담은 뒤 정렬하였다.

```java
ArrayList<String> keyList = new ArrayList<>();
	for(String key : genreMap.keySet()) {
	}
	Collections.sort(keyList, new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return genreMap.get(o2) - genreMap.get(o1);
			//재생 수 내림차순 정렬
		}
	});
```

그리고 2,3번을 만족하기 위해 `songList`를 조건에 맞게 정렬했다.

```java
	Collections.sort(songList, new Comparator<Node>() {
	@Override
		public int compare(Node o1, Node o2) {
			if(o1.playCnt==o2.playCnt) return o1.idx - o2.idx;
			//장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.(오름차순)
			return o2.playCnt - o1.playCnt;
			//장르 내에서 많이 재생된 노래를 먼저 수록합니다.(내림차순)
		}
			 
	}); //
```
<br>


위와 같은 과정을 마치면,    
1. `keyList`에는 장르명이 많이 재생된 순으로 정렬되어 있다.  
2. `songList`에는 2,3번 조건에 맞게 노래가 정렬되어 있다.  

=> 즉 `keyList`에서 정렬된 `genre`별로, `songList`에서 찾아 앞에 2개씩  `bestAlbum`에 담는다.  

```java
int cnt = 0; // 장르 별 2개만
for(String genre : keyList) {
	cnt = 0;
			 
	for(Node n : songList) {
		if(cnt>=2) break;
		if(genre.equals(n.genre)) {
			bestAlbum.add(n.idx);
			cnt++; //저장완료함
		}
	}
			 //장르별로 가장 많이 재생 된 노래 두개씩 저장
}
```


완성된 `bestAlbum`을 `int형 Array`로 변환하여 `return`한다.

<br>



<br><br>

###  💡 소스코드
```java
import java.util.*;
class Solution {
    public static int[] solution(String[] genres, int[] plays) {
		 ArrayList<Node> songList = new ArrayList<>();
		 //노래 정보 저장할 list
		 ArrayList<Integer> bestAlbum = new ArrayList<>();
		 //정답 저장할 list
		 
		 HashMap<String, Integer> genreMap = new HashMap<>();
		 //장르별 재생된 횟수
		 for(int i = 0 ; i < genres.length ; i++){
			 String genre = genres[i];
			 int playCnt = plays[i];
	         
			 songList.add(new Node(i, genre, playCnt)); //idx, genre, playCnt (노래 별 정보)
			 genreMap.put(genre, genreMap.getOrDefault(genre, 0)+playCnt); //장르별 재생횟수 
			 
		 }
		 //map에 장르별로 재생된 횟수 저장 (key : 장르, value : 재생된 횟수) 
		 //list에 노래 정보 저장 (idx, genre, playCnt)
		 
		 ArrayList<String> keyList = new ArrayList<>();
		 for(String key : genreMap.keySet()) {
			 keyList.add(key);
		 }
		 Collections.sort(keyList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return genreMap.get(o2) - genreMap.get(o1);
				//재생 수 내림차순 정렬
			}
		 });
		 //genreMap을 재생순 내림차순 정렬 => List를 이용하여 정렬 (keySet을 List로 가져온 뒤 정렬)
		 
		 
		 Collections.sort(songList, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.playCnt==o2.playCnt) return o1.idx - o2.idx;
				//장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.(오름차순)
				return o2.playCnt - o1.playCnt;
				//장르 내에서 많이 재생된 노래를 먼저 수록합니다.(내림차순)
			}
			 
		 }); //
 
		 
		 
		 int cnt = 0; // 장르 별 2개만
		 for(String genre : keyList) {
			 cnt = 0;
			 
			 for(Node n : songList) {
				 if(cnt>=2) break;
				 if(genre.equals(n.genre)) {
					 bestAlbum.add(n.idx);
					 cnt++; //저장완료함
				 }
			 }
			 //장르별로 가장 많이 재생 된 노래 두개씩 저장
		 }
		 
		 int answerSize = bestAlbum.size();
		 int[] answer = new int[answerSize];
		 for(int i = 0; i<answerSize ; i++) {
			 answer[i] = bestAlbum.get(i);
		 }
            //int형 array로 return
		 return answer;
		 
	 }
	 
	 static class Node {
		 
		int idx;
		String genre;
		int playCnt;
		
		public Node(int idx, String genre, int playCnt) {
			super();
			this.idx = idx;
			this.genre = genre;
			this.playCnt = playCnt;
		}
	 }
}
```

<br>

### 결과
채점을 시작합니다.  
정확성  테스트  
테스트 1 〉	통과 (0.96ms, 52.2MB)  
테스트 2 〉	통과 (1.06ms, 54MB)  
테스트 3 〉	통과 (0.91ms, 52.6MB)  
테스트 4 〉	통과 (1.63ms, 52.9MB)  
테스트 5 〉	통과 (1.31ms, 51.7MB)  
테스트 6 〉	통과 (1.29ms, 52.6MB)  
테스트 7 〉	통과 (1.10ms, 52.7MB)  
테스트 8 〉	통과 (1.06ms, 52.9MB)  
테스트 9 〉	통과 (1.11ms, 52.1MB)  
테스트 10 〉	통과 (2.04ms, 53MB)  
테스트 11 〉	통과 (0.93ms, 52.4MB)  
테스트 12 〉	통과 (1.24ms, 52.1MB)  
테스트 13 〉	통과 (1.24ms, 53.3MB)  
테스트 14 〉	통과 (1.18ms, 54MB)  
테스트 15 〉	통과 (1.02ms, 52.2MB)  
채점 결과  
정확성: 100.0  
합계: 100.0 / 100.0  