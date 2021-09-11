package week26.Programmers_배달;

import java.util.*;

public class Programmers_배달 {
    static List<List<Node>> list;
    static int[] distance;
    static final int INF = Integer.MAX_VALUE;
    
	static class Node implements Comparable<Node> {
		int index;
		int dis;

		public Node(int index, int dis) {
			this.index = index;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
	}
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        list = new ArrayList<>();
        
        for(int i =0 ; i<= N; i++){
            list.add(new ArrayList<Node>());
        }
        
        //경로 저장
        for(int[] edge : road){
            int idx1 = edge[0];
            int idx2 = edge[1];
            int dis = edge[2];
            
            list.get(idx1).add(new Node(idx2, dis));
            list.get(idx2).add(new Node(idx1, dis));
        }
        
        //1번 마을 기준 다익스트라
        distance = dijkstra(1, N);
        
        for(int i = 1; i <= N; i++){
            if(distance[i] <= K)    answer++;
        }

        return answer;
    }
    
	static int[] dijkstra(int start, int n) {
		boolean[] isVisited = new boolean[n + 1];
		int[] distance = new int[n + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (isVisited[node.index])
				continue;

			isVisited[node.index] = true;

			for (Node nNode : list.get(node.index)) {
				if (!isVisited[nNode.index] && distance[nNode.index] >= nNode.dis + node.dis) {
					distance[nNode.index] = nNode.dis + node.dis;
					pq.offer(new Node(nNode.index, distance[nNode.index]));
				}
			}
		}

		return distance;
	}
}
