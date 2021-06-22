package week16.BOJ_2910_S3_빈도정렬;

import java.io.*;
import java.util.*;

public class Main_BOJ_2910_S3_빈도정렬 {
	static HashMap<Integer, Node> map;
	static ArrayList<Node> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new HashMap<>(); //빈도체크 -> HashMap
		list = new ArrayList<>(); //정렬할 list
		StringBuilder sb = new StringBuilder(); //결과값 출력용 sb
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //메세지의 길이
		int C = Integer.parseInt(st.nextToken()); //최대값
		
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tgt = Integer.parseInt(st.nextToken());
			
			if(map.get(tgt)==null) { //map에 없다 -> cnt=1로 put
				map.put(tgt, new Node(tgt,i,1));
			}else { //cnt+=1해서 put
				map.put(tgt, new Node(tgt,map.get(tgt).idx, map.get(tgt).cnt+1));
			}
		}
		
		Iterator<Integer> iter = map.keySet().iterator();

		while(iter.hasNext()) { //정렬하기 위해 key Node를 list에 옮김
			list.add(map.get(iter.next()));
		}
		
		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.cnt==o2.cnt) { //등장하는 횟수가 같다면, 먼저 나온 것이 앞에 있어야 한다.
					return o1.idx -o2.idx;
				}
				return o2.cnt - o1.cnt; //빈도수에 따른 내림차순 정렬
			}
			
		});
		
		for (Node node : list) {
			int num = node.num;
			int cnt = node.cnt;
			for (int i = 0; i < cnt; i++) {
				sb.append(num).append(" ");
			}
		}
		
		System.out.println(sb);
		
	}
	
	static class Node{
		int idx; //인덱스
		int cnt; //빈도수
		int num; //숫자
		public Node(int num, int idx, int cnt) {
			super();
			this.num = num;
			this.idx = idx;
			this.cnt = cnt;
		}

	}
	
	
}
