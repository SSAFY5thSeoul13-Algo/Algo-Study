package week16.boj2910;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class 빈도_정렬_2910 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 숫자, 빈도수 를 저장하는 HashMap 
		HashMap<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(map.containsKey(tmp)) {
				map.replace(tmp, map.get(tmp)+1);
			}else {
				map.put(tmp, 1);
			}
		}
		
		// HashMap에 저장된 key값을 list에 담는다.
		List<Integer> array = new ArrayList<Integer>(map.keySet());
		
		
		// value에 따라 내림차순으로 정렬 
		Collections.sort(array, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return map.get(o2)-map.get(o1);
			}
			
		});
		
		Iterator<Integer> iter = array.iterator();
		
		while(iter.hasNext()) {
			Integer integer = iter.next();
			// 빈도 수 만큼 for문을 돌린다.
			for(int i=0; i<map.get(integer); i++) {
				System.out.print(integer + " ");
			}
		}
		
		
		

	}

}
