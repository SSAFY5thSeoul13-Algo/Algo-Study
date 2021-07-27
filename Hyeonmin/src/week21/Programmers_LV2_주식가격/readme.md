## Programmers LV2 주식가격
- 스택/큐
- level2

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42584

초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.


#### 제한사항
prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
prices의 길이는 2 이상 100,000 이하입니다.
<br><br>

###  💡 풀이

변수
`PriorityQueue<Node> pq` : 가격의 내림차순으로 정렬하는 우선순위 큐
`class Node implements Comparable<Node>` : 각 시간별 주식 정보 저장하는 클래스. 1초에서 time 값은0


<br>

각 시점의 주식 정보를 저장해서 가격의 내림차순으로 정렬하는 우선순위 큐 `pq`를 사용한다

```java
		//가격의 내림차순으로 정렬하는 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>();
```

각 시간대별로 현재 `pq`에 있는 값들을 보고 현재 시점보다 가격이 높은 순간이 가격이 떨어진 경우이므로 그 시점과 `pq`에 저장된 각 데이터의 시점을 비교하여 그 차이를 `result`에 저장후에 현재 시점 주식의 정보를 `pq`에 저장한다

```java
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
```

모든 시점의 주식 정보를 `pq`에 저장하고 나서도 아직 `pq`에 남아있는 데이터는 가격이 떨어진 적이 없는 경우이다

```java
        //남은 시점들을 처리. 가격이 떨어진 적이 없는 경우들
        while(!pq.isEmpty()){
            Node node = pq.poll();
            
            result[node.time] = prices.length - (node.time+1);
        }
```



<br><br>

###  💡 소스코드
```java
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



```


<br>


정확성  테스트
테스트 1 〉	통과 (0.73ms, 53MB)
테스트 2 〉	통과 (0.84ms, 51.8MB)
테스트 3 〉	통과 (2.63ms, 54.3MB)
테스트 4 〉	통과 (2.27ms, 53.1MB)
테스트 5 〉	통과 (3.46ms, 53.6MB)
테스트 6 〉	통과 (0.71ms, 52MB)
테스트 7 〉	통과 (1.91ms, 52.7MB)
테스트 8 〉	통과 (1.88ms, 53.6MB)
테스트 9 〉	통과 (0.77ms, 53.2MB)
테스트 10 〉	통과 (3.33ms, 52.8MB)
효율성  테스트
테스트 1 〉	통과 (40.04ms, 72.4MB)
테스트 2 〉	통과 (33.38ms, 67.9MB)
테스트 3 〉	통과 (36.50ms, 74.8MB)
테스트 4 〉	통과 (37.74ms, 72.7MB)
테스트 5 〉	통과 (30.13ms, 66.3MB)