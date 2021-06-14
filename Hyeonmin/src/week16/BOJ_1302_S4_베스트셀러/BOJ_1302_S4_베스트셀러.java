package week16.BOJ_1302_S4_베스트셀러;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * hash의 key를 제목으로 value를 카운트로*/
public class BOJ_1302_S4_베스트셀러 {
	static int N;
	//책의 이름을 key로, 팔린 수를 Value로 저장할 맵
	static Map<String, Integer> map = new HashMap<String, Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			//책 제목
			String str = br.readLine();
			
			//이미 맵에 저장되어 있는 책인 경우
			if(map.containsKey(str)) {
				//해당 책의 value를 가져옴
				int num = map.get(str);
				//value를 1상승시켜서 저장함
				map.put(str, num+1);
			}
			//맵에 없는 경우
			else {
				//value를 1로 맵에 새로 저장
				map.put(str, 1);
			}
		}
		
		//hashmap을 정렬하기 위한 엔트리 리스트
		List<Map.Entry<String, Integer>> entry = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		
		//리스트를 정렬
		Collections.sort(entry, new Comparator<Map.Entry<String, Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				//팔린 개수가 같은 경우 제목을 비교해서 정렬
				if(o1.getValue() == o2.getValue()) {
					return o1.getKey().compareTo(o2.getKey());
				}
				//팔린 개수의 내림 차순으로 정렬
				return o2.getValue().compareTo(o1.getValue());
			}
			
		});
		
		//정렬한 리스트의 가장 처음에 있는 데이터의 key값 출력
		System.out.println(entry.get(0).getKey());
	}
}
