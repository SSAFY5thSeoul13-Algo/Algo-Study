package week18.Programmers_LV3_단어변환;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_LV3_단어변환 {
	static String begin="hit", target="cog";
	static String[] words= {"hot", "dot", "dog", "lot", "log", "cog"};
	static Queue<String> queue = new LinkedList<String>();
	
	public static void main(String[] args) {
		int num = solution();
		
		System.out.println(num);
	}
	
	static int solution() {
		//시작 단어 큐에 저장
		queue.offer(begin);
		//큐 실행 뎁스
		int count = 0;
		
		boolean canChange =false;
		
		//배열에 target 단어가 존재하는 경우 체크
		for (int i = 0; i < words.length; i++) {
			if(words[i].equals(target)) {
				canChange = true;
			}
		}
		
		
		//존재하지 않으면 0리턴
		if(!canChange) {
			return 0;
		}
		
		//bfs 실행
		while(!queue.isEmpty()) {
			//큐에 있는 데이터 수
			int size = queue.size();
			
			//모든 데이터에 대해서
			for (int k = 0; k < size; k++) {
				//큐에 있던 단어
				String word = queue.poll();
				
				//타겟이면 현재 뎁스 리턴
				if(word.equals(target)) {
					return count;
				}

				//words 배열에 있는 모든 단어들에 대해서
				for (int i = 0; i < words.length; i++) {
					int equalCount = 0;
					//큐에서 꺼낸 단어와 같은 글자 수를 비교
					for (int j = 0; j < word.length(); j++) {
						char a = word.charAt(j);
						char b = words[i].charAt(j);
						
						if(a==b) {
							equalCount++;
						}
					}
					
					//글자수 차이가 1개면 큐에 저장
					if(equalCount == word.length()-1) {
						queue.offer(words[i]);
					}
				}
				
				
			}
			//뎁스 증가
			count++;
			
			
		}
		
		return 0;
	}
	
}
