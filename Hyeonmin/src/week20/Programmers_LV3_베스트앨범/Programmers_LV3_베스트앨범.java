package week20.Programmers_LV3_베스트앨범;

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
