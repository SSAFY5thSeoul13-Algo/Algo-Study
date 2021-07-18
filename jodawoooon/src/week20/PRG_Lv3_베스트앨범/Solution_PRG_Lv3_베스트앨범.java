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