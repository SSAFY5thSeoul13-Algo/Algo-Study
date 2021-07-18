package week20.PROGRAMMERS_LV3_베스트앨범;

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
            // 장르별 노래를 수록
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
        public int total;	// 장르별 총 재생수
        public PriorityQueue<Song> pq;	// 수록곡 모음
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
        public int idx, play;	// 노래고유번호, 재생횟수
        
        public Song(int idx, int play){
            this.idx = idx;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song a){
            if(this.play == a.play){
                return a.play - this.play;
            }
            return this.idx - a.idx;
        }
    }
}

