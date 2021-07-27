package week21.Programmers_LV2_주식가격;

import java.util.PriorityQueue;

public class Programmers_LV2_주식가격 {
	//각 시간별 주식 가격. 1초에서 time 값은0
    static class Node implements Comparable<Node>{
        int time;
        int price;
        
        Node(int time, int price){
            this.time = time;
            this.price = price;
        }

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(o.price, this.price);
		}
    }

	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3};
		
		int[] result = solution(prices);
		
		for (int i : result) {
			System.out.print(i+" ");
		}

	}
	
	static int[] solution(int[] prices) {
		//가격의 내림차순으로 정렬하는 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int[] result = new int[prices.length];
        
        //각 시간별로 주식의 가격을 pq에 저장
        for(int i = 0; i < prices.length ; i++){
        	//현재 지나온 시간들의 주식가격중 떨어지지 않은 시점이 있는동안 반복
            while(!pq.isEmpty()){
            	//현재 지나온 시간 중 가장 가격이 높은 시점의 정보
                Node node = pq.peek();
                
                //가격이 떨어진 경우
                if(node.price > prices[i]){
                	//해당 시점과 현재시점의 차이를 저장
                    result[node.time] = i - node.time;
                    //우선순위 큐에서 삭제
                    pq.poll();
                }
                //가격이 떨어지는 시점이 없는 경우
                else{
                    break;
                }
            }
            
            //현재 시점의 정보를 저장
            pq.offer(new Node(i, prices[i]));
        }
        
        //남은 시점들을 처리. 가격이 떨어진 적이 없는 경우들
        while(!pq.isEmpty()){
            Node node = pq.poll();
            
            result[node.time] = prices.length - (node.time+1);
        }
        
        return result;
	}

}
