package week16.BOJ_2910_S3_빈도정렬;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 풀이시간 : 2시간
 */
public class Main {

	static int N, C;
	static int idx = 0;
	static Map<Integer, Number> map = new HashMap<>();
	static class Number{
		int cnt, born;
		
		public Number() {
			super();
			this.cnt = 1;
			this.born = idx++;
		}
	}
	public static void main(String[] args) throws Exception{
		/**
		 * 메세지는 숫자 N개로 이루어진 수열이고, 숫자는 모두 C보다 작거나, 같다.
		 * 이 숫자를 자주 등장하는 빈도순서대로 정렬하려고 한다.
		 * 
		 * X,Y가 있을때 X가 Y보다 수열에서 많이 등장하는 경우에는 X가 Y보다 앞에 있어야한다.
		 * 등장하는 횟수가 같다면 먼저 나온것이 앞에 있어야한다.
		 * 
		 * 수열이 주어졌을때 빈도 정렬을 하는 프로그램을 작성하시오.
		 * 
		 * [풀이]
		 * 
		 * 
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N, C 입력
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		

		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			
			// 숫자 입력
			int num = Integer.parseInt(st.nextToken());

			if(map.containsKey(num)) {
				map.get(num).cnt++;
			}else {
				map.put(num, new Number());
			}
			
		}
		
		List<Entry<Integer,Number>> list = new ArrayList<>(map.entrySet());
		// 정렬
		Collections.sort(list, new Comparator<Entry<Integer, Number>>() {

			@Override
			public int compare(Entry<Integer, Number> o1, Entry<Integer, Number> o2) {
				
				if(o1.getValue().cnt != o2.getValue().cnt) {
					return o2.getValue().cnt - o1.getValue().cnt;
				}
				
				return o1.getValue().born - o2.getValue().born;
			}
			
		});
		
		// 출력
		for(Entry<Integer, Number> el : list) {
			Number val = el.getValue();
			for(int i=0; i<val.cnt; i++) {
				System.out.print(el.getKey() + " ");
			}
		}
		
	}

}
