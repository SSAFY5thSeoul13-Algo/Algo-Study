package week20.hash4;

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
