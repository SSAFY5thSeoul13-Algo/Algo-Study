## Programmers - 베스트앨범  
- Hash, map
- Level3

🔗[베스트앨범 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42579)

## 풀이
먼저 고유번호, 장르, 재생수 정보를 담고 있는 Song 객체를 만들어주고 재생수를 기준으로 내림차순, 만약 재생수가 같으면 고유번호를 기준으로 오름차순 정렬을 해주었습니다.

~~~java
	static class Song implements Comparable<Song>{
		// 고유번호, 장르, 재생수 
		int index;
		String genre;
		int play;
		public Song(int index,String genre,int play) {
			this.index = index;
			this.genre = genre;
			this.play = play;
		}
		@Override
		public int compareTo(Song o) {
			// 재생수가 많은 것부터 정렬, 재생수가 같은 경우 고유번호가 낮은 노래가 먼저오도록 정렬 
			if(this.play == o.play) {
				return this.index - o.index;
			}else {
				return o.play - this.play;
			}
		}
		
	}
~~~

그리고 genreMap에 장르, 각 장르별로 총 재생횟수를 담아줍니다.

~~~java
	for(int i=0; i<genres.length; i++) {
        	String genre = genres[i];
        	int play = plays[i];
        	list.add(new Song(i,genre,play));
        	        	
        	if(genreMap.containsKey(genre)) {
        		genreMap.put(genre, genreMap.get(genre)+play);
        	}else {
        		genreMap.put(genre, play);
        	}
        	
        }
~~~

그다음, Comparator를 이용하여 장르가 다른경우 총 재생횟수가 많은 장르가 먼저 오도록 정렬을 해주고, 장르가 같다면 compareTo를 이용하여 기존 정렬방식대로 정렬해주었습니다.

~~~java
	Collections.sort(list, new Comparator<Song>() {

			@Override
			public int compare(Song o1, Song o2) {
				// 장르가 똑같다면 기존 정렬방식 대로 
				if(o1.genre.equals(o2.genre)) {
					return o1.compareTo(o2);
				}
				// 장르가 다를 경우 총 재생횟수가 더 많은 것이 먼저 오도록 정렬 
				else {
					return genreMap.get(o2.genre) - genreMap.get(o1.genre);
				}
			}
        	
        });
~~~

그리고 각 장르별로 2곡만 담을 수 있기 때문에 장르와 베스트 앨범에 담긴 장르별 개수를 담는 hashMap을 통해 list에 담긴 순서대로 map에 넣어주고 map에 들어가는 고유번호는 answers에 넣어주었습니다. 만약 개수가 2가 되었으면 continue를 해주었습니다.

~~~java
	for(int i=0; i<list.size(); i++) {
        	String genre = list.get(i).genre;
        	int index = list.get(i).index;
        	
        	if(!bestAlbum.containsKey(genre)) {
        		bestAlbum.put(genre, 1);
        		answers.add(index);
        	}else {
        		// 이미 2곡을 넣었다면 continue
        		if(bestAlbum.get(genre)>=2)	continue;
        		
        		bestAlbum.put(genre, bestAlbum.get(genre)+1);
        		answers.add(index);
 
        	}
        }
~~~



## 소스코드
~~~java
import java.util.*;

public class Programmers_베스트앨범 {
	static class Song implements Comparable<Song>{
		// 고유번호, 장르, 재생수 
		int index;
		String genre;
		int play;
		public Song(int index,String genre,int play) {
			this.index = index;
			this.genre = genre;
			this.play = play;
		}
		@Override
		public int compareTo(Song o) {
			// 재생수가 많은 것부터 정렬, 재생수가 같은 경우 고유번호가 낮은 노래가 먼저오도록 정렬 
			if(this.play == o.play) {
				return this.index - o.index;
			}else {
				return o.play - this.play;
			}
		}
		
	}
	public int[] solution(String[] genres, int[] plays) {
		
        HashMap<String,Integer> genreMap = new HashMap<String,Integer>();
        HashMap<String,Integer> bestAlbum = new HashMap<String,Integer>();
        List<Song> list = new ArrayList<Song>();
        List<Integer> answers = new ArrayList<>();
        for(int i=0; i<genres.length; i++) {
        	String genre = genres[i];
        	int play = plays[i];
        	list.add(new Song(i,genre,play));
        	        	
        	if(genreMap.containsKey(genre)) {
        		genreMap.put(genre, genreMap.get(genre)+play);
        	}else {
        		genreMap.put(genre, play);
        	}
        	
        }

        Collections.sort(list, new Comparator<Song>() {

			@Override
			public int compare(Song o1, Song o2) {
				// 장르가 똑같다면 기존 정렬방식 대로 
				if(o1.genre.equals(o2.genre)) {
					return o1.compareTo(o2);
				}
				// 장르가 다를 경우 총 재생횟수가 더 많은 것이 먼저 오도록 정렬 
				else {
					return genreMap.get(o2.genre) - genreMap.get(o1.genre);
				}
			}
        	
        });
        

        for(int i=0; i<list.size(); i++) {
        	String genre = list.get(i).genre;
        	int index = list.get(i).index;
        	
        	if(!bestAlbum.containsKey(genre)) {
        		bestAlbum.put(genre, 1);
        		answers.add(index);
        	}else {
        		// 이미 2곡을 넣었다면 continue
        		if(bestAlbum.get(genre)>=2)	continue;
        		
        		bestAlbum.put(genre, bestAlbum.get(genre)+1);
        		answers.add(index);
 
        	}
        }
        
        int[] answer = new int[answers.size()];
        for(int i=0; i<answers.size(); i++) {
        	answer[i] = answers.get(i);
        }
        
        
        return answer;  
    }

}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.68ms, 52.1MB)|
|테스트 2 |	통과 (0.54ms, 52.1MB)|
|테스트 3 |	통과 (0.47ms, 52.6MB)|
|테스트 4 |	통과 (0.71ms, 52.1MB)|
|테스트 5 |	통과 (1.03ms, 52.6MB)|
|테스트 6 |	통과 (1.57ms, 53.3MB)|
|테스트 7 |	통과 (0.85ms, 51.8MB)|
|테스트 8 |	통과 (0.86ms, 52MB)|
|테스트 9 |	통과 (1.05ms, 52.2MB)|
|테스트 10 |	통과 (1.23ms, 53.2MB)|
|테스트 11 |	통과 (0.68ms, 52.7MB)|
|테스트 12 |	통과 (0.88ms, 52.7MB)|
|테스트 13 |	통과 (1.17ms, 52.9MB)|
|테스트 14 |	통과 (0.83ms, 52.2MB)|
|테스트 15 |	통과 (0.73ms, 52.1MB)|