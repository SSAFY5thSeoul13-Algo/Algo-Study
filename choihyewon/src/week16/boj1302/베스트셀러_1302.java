package week16.boj1302;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class 베스트셀러_1302 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashMap<String,Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			String book = br.readLine();
			// 만약 이미 book이 hashmap에 들어있다면 +1을 더해준다.
			if(map.containsKey(book)) {
				map.replace(book, map.get(book)+1);
			}
			// 해당 book이 없다면 hashmap, 1 값을 넣어준다.
			else {
				map.put(book, 1);
			}
		}
		
		int max = 0;
		// hashmap에서 최대인 value 값을 찾는다.
		for (String key : map.keySet()) {
			max = Math.max(max, map.get(key));
		}
		
		// 베스트셀러인 key값을 담는 list
		List<String> list = new ArrayList<>();
		for (String key : map.keySet()) {
			if(map.get(key)==max) {
				list.add(key);
			}
		}
		
		Collections.sort(list);
		
		// list에서 사전 순으로 가장 앞서는 제목 출력 
		System.out.println(list.get(0));
		
	}

}
