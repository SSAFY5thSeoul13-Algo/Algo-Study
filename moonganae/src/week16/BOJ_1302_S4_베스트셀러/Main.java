package week16.BOJ_1302_S4_베스트셀러;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author moonseounguk
 * 풀이시간 :15분
 */
public class Main {

	public static void main(String[] args) 	throws Exception{
		/**
		 * 오늘 판매한 책의 제목을 보면서 가장 많이 팔린 책의 제목을 칠판에 써놓는 일도 한다.
		 * 
		 * 가장 많이 팔린 책의 제목을 출력하는 프로그램을 작성하시오.
		 * 
		 * [풀이]
		 * HashMap을 사용하여 갯수를 증가 시킨다.
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 판매한 책의 수 입력
		int N = Integer.parseInt(br.readLine());
		// 판매기록
		Map<String, Integer> log = new HashMap<>();
		
		// 최대 팔린 책의 수와 이름
		int max = -1;
		String maxName = null;
		
		for(int i=0; i<N; i++) {
			String name = br.readLine();
			int cnt = 0;
			// 판매기록이 있는책인면 팔린수 가져오기
			if(log.containsKey(name)) {
				cnt = log.get(name);
			}
			// 최대 팔린수인지 확인
			if(cnt > max) {
				maxName = name;
				max = cnt;
			}
			// 같으면 사전순으로 빠른것
			else if(cnt == max && name.compareTo(maxName) < 0) {
				maxName = name;
			}
			// 기록 갱신
			log.put(name, cnt+1);
		}
		System.out.println(maxName);
	}

}
