package week16.BOJ_2910_S3_빈도정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_2910_S3_빈도정렬 {
	static int N, C;
	static Map<Integer, Node> map = new TreeMap<Integer, Node>();
	
	//들어온 순서와 빈도 수를 저장할 클래스
	static class Node{
		int order;
		int cnt;
		
		public Node(int order, int cnt) {
			super();
			this.order = order;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			//이미 있는 수인 경우
			if(map.containsKey(num)) {
				Node node = map.get(num);
				node.cnt +=1;
				map.put(num, node);
			}
			//없는 수인 경우
			else {
				Node node = new Node(i, 1);
				map.put(num, node);
			}
		}
		

		
		List<Entry<Integer, Node>> list = new ArrayList<Entry<Integer, Node>>(map.entrySet());
		
		//정렬
		Collections.sort(list, new Comparator<Entry<Integer, Node>>(){
			@Override
			public int compare(Entry<Integer, Node> o1, Entry<Integer, Node> o2) {
				//두 수의 빈도가 같은 경우 order기준 오름차순 정렬. order가 낮은 것이 먼저 들어온 것
				if(o1.getValue().cnt == o2.getValue().cnt) {
					return Integer.compare(o1.getValue().order, o2.getValue().order);
				}
				//빈도가 다른 경우 빈도기준 내림차순 정렬
				else {
					return Integer.compare(o2.getValue().cnt, o1.getValue().cnt);
				}
			}
		});
		
		//출력
		for (Entry<Integer, Node> entry : list) {
			int cnt = entry.getValue().cnt;
			int num = entry.getKey();
			//각각의 숫자를 빈도 수만큼 출력
			for (int i = 0; i < cnt; i++) {
				System.out.print(num+" ");
			}
		}
		
	}

}
