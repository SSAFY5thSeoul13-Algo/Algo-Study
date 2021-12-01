## Programmers - �ζ��� �ְ� ������ ���� ���� 
- Implementation
- Level 1
- 2021 Dev-Matching: �� �鿣�� ������(��ݱ�)
-[�ζ��� �ְ� ������ ���� ���� ���� �ٷΰ���](https://programmers.co.kr/learn/courses/30/lessons/77484)

## Ǯ��

lottos �迭�� �ε����� 0�� ���� 0�� �ƴ� ��츦 ����� ������ Ǯ�����ϴ�.
0�� ��� zero�� count ���־���, �ƴ� ��쿡�� win_nums �迭�� ���Ͽ� ������ match�� count���ص� break���־� ���� �ε����� �Ѿ�� ������ ���Ͽ����ϴ�.


## �ҽ��ڵ�
~~~java
class Solution {
	    public int[] solution(int[] lottos, int[] win_nums) {
	        int[] answer = new int[2];
	        int zero = 0;
	        int match = 0;
	        for(int i : lottos){
	            // 0�� �ƴ� ��쿡�� win_nums Ž�� 
	            if(i==0){
	                zero++;
	            }else{
	                for(int j : win_nums){
	                    if(i==j){
	                        match++;
	                        break;
	                    }
	                }
	            }
	        }
	        
	        
	        answer[0] = match !=0 ? 7-match-zero : (zero !=0 ? 7-zero : 6);
	        answer[1] = match !=0 ? 7-match : 6;
	        
	        return answer;
	    }
	}

~~~

## ���

|��Ȯ��|�׽�Ʈ|
|�׽�Ʈ 1 |	��� (0.02ms, 79.3MB)|
|�׽�Ʈ 2 |	��� (0.01ms, 73.5MB)|
|�׽�Ʈ 3 |	��� (0.02ms, 73.6MB)|
|�׽�Ʈ 4 |	��� (0.01ms, 78.5MB)|
|�׽�Ʈ 5 |	��� (0.01ms, 73.3MB)|
|�׽�Ʈ 6 |	��� (0.01ms, 76.4MB)|
|�׽�Ʈ 7 |	��� (0.01ms, 73.9MB)|
|�׽�Ʈ 8 |	��� (0.01ms, 77.5MB)|
|�׽�Ʈ 9 |	��� (0.02ms, 78.8MB)|
|�׽�Ʈ 10|	��� (0.02ms, 75.8MB)|
|�׽�Ʈ 11 |	��� (0.02ms, 76.4MB)|
|�׽�Ʈ 12 |	��� (0.03ms, 73.4MB)|
|�׽�Ʈ 13 |	��� (0.01ms, 74.8MB)|
|�׽�Ʈ 14 |	��� (0.01ms, 67.9MB)|
|�׽�Ʈ 15 |	��� (0.02ms, 77.8MB)|