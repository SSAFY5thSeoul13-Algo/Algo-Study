

## BOJ 2910 S3 빈도정렬
- silver3
- HashMap
- 정렬
  

<br>



### 풀이과정

Node : num, cnt, idx로 구성
N : 메세지의 길이
C : 수열 최대값
map : 빈도체크 할 HashMap
list : map의 key Node를 정렬하기 위한 list

1. 입력받은 수열을 HashMap에 put한다.  
	이 때, Node 타입의 key를 이용하여 idx와 cnt를 저장한다.
	

	    for (int i = 0; i < N; i++) {
			int tgt = Integer.parseInt(st.nextToken());
			
			if(map.get(tgt)==null) { //map에 없다 -> cnt=1로 put
				map.put(tgt, new Node(tgt,i,1));
			}else { //cnt+=1해서 put
				map.put(tgt, new Node(tgt,map.get(tgt).idx, map.get(tgt).cnt+1));
			}
		}

2. iterator를 이용하여 ArrayList에 map의 keySet을 add한다.
	

	    Iterator<Integer> iter = map.keySet().iterator();

		while(iter.hasNext()) { //정렬하기 위해 key Node를 list에 옮김
			list.add(map.get(iter.next()));
		}

3. 조건에 맞게 list를 도수에 따른 내림차순 정렬 한다. 이 때, 만약 빈도수가 같다면 먼저 나온 것이 앞으로 오도록 정렬한다.
	

	    Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.cnt==o2.cnt) { //등장하는 횟수가 같다면, 먼저 나온 것이 앞에 있어야 한다.
					return o1.idx -o2.idx;
				}
				return o2.cnt - o1.cnt; //빈도수에 따른 내림차순 정렬
			}
			
		});
4. list의 num값을 cnt만큼 sb에 붙여서 sb출력
	

	    for (Node node : list) {
			int num = node.num;
			int cnt = node.cnt;
			for (int i = 0; i < cnt; i++) {
				sb.append(num).append(" ");
			}
		}

<br>


### 결과
메모리 :   12136KB  
시간 :   100ms
 