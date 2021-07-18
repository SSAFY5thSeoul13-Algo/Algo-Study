## Progrmmers LV3 베스트앨범
- 그래프
- Level 3
- https://programmers.co.kr/learn/courses/30/lessons/42579
<br>

### 문제설명

> 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

```
	1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
	2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
	3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
```

> 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.


### 제한사항
- genres[i]는 고유번호가 i인 노래의 장르입니다.
- plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
- genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
- 장르 종류는 100개 미만입니다.
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
- 모든 장르는 재생된 횟수가 다릅니다.

### 입출력 예
|genres|	plays|	return|
|-----|-----|------|
|["classic", "pop", "classic", "classic", "pop"]|	[500, 600, 150, 800, 2500]|	[4, 1, 3, 0]|



#### 입출력 예 설명
classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

- 고유 번호 3: 800회 재생
- 고유 번호 0: 500회 재생
- 고유 번호 2: 150회 재생

pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.
- 고유 번호 4: 2,500회 재생
- 고유 번호 1: 600회 재생

따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

### 풀이 및 과정
HashMap 을 사용하여, 장르별 총 재생횟수와 노래목록을 저장하고, 정렬하여 리스트로 변환합니다.

그리고 리스트를 사용하여, 장르별로 최대 2곡씩 담아 반환하였습니다.


#### 막힌점
- Map의 Entry를 정렬시키는 부분에서 막혀서 구글링을 통해 해결하였습니다.
- import java.util.*; 를 사용하였지만, Entry를 인식하지 못했습니다 ㅠㅠ

### 소스코드
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        List<Integer> answerList = new ArrayList<>();
        
        Map<String, Genre> map = new HashMap<>();
        // 장르별 노래 총 재생수 구하기
        for(int i=0; i<plays.length; i++){
            String key = genres[i];
            
            Genre genre = map.get(key);
            
            if(genre == null){
                genre = new Genre();    
            }
            genre.add(i, plays[i]);
            map.put(key, genre);
        }
        // 총 재생수 별로 정렬
        List<Entry<String, Genre>> list = new ArrayList<Entry<String, Genre>>(map.entrySet());
        
        Collections.sort(list, new Comparator<Entry<String, Genre>>(){
            @Override
            public int compare(Entry<String, Genre> o1, Entry<String, Genre> o2){
                return o2.getValue().total - o1.getValue().total;
            }
        });
        
        // 장르별로 최대 2곡씩 선정
        
        for(Entry<String, Genre> entry : list){
        	PriorityQueue<Song> pq = entry.getValue().pq;
            
            for(int i=0; i<2; i++){
                if(pq.isEmpty()) break;
                answerList.add(pq.poll().idx);
            }
        }
        
        int[] answer = new int[answerList.size()];
        
        int i=0;
        for(int idx : answerList){
            answer[i++] = idx;
        }
        
        return answer;
    }
    
    class Genre{
        public int total;
        public PriorityQueue<Song> pq;
        public Genre(){
            this.total = 0;
            this.pq = new PriorityQueue<Song>();
        }
        public void add(int idx, int play){
            this.total += play;
            pq.offer(new Song(idx, play));
        }
    }
    
    class Song implements Comparable<Song>{
        public int idx, play;
        
        public Song(int idx, int play){
            this.idx = idx;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song a){
            if(this.play == a.play){
                return this.idx - a.idx;
            }
            return a.play - this.play;
        }
    }
}
```

### 결과
```
테스트 1 〉	통과 (1.26ms, 52.1MB)
테스트 2 〉	통과 (1.38ms, 53.1MB)
테스트 3 〉	통과 (1.36ms, 51.9MB)
테스트 4 〉	통과 (1.27ms, 53.1MB)
테스트 5 〉	통과 (1.55ms, 53.1MB)
테스트 6 〉	통과 (1.40ms, 52.7MB)
테스트 7 〉	통과 (1.50ms, 52.1MB)
테스트 8 〉	통과 (0.98ms, 52.3MB)
테스트 9 〉	통과 (1.27ms, 52.3MB)
테스트 10 〉	통과 (1.64ms, 52.5MB)
테스트 11 〉	통과 (1.52ms, 52.9MB)
테스트 12 〉	통과 (1.45ms, 53MB)
테스트 13 〉	통과 (1.52ms, 51.5MB)
테스트 14 〉	통과 (1.66ms, 53.1MB)
테스트 15 〉	통과 (1.42ms, 52.6MB)
```

