## Programmers - �ٴܰ� ĩ�� �Ǹ�
- HashMap
- Level 3
- 2021 Dev-Matching: �� �鿣�� ������(��ݱ�)
-[�ٴܰ� ĩ�� �Ǹ� ���� �ٷΰ���](https://programmers.co.kr/learn/courses/30/lessons/77486)

## Ǯ��

���� �ڽİ� �θ� ��� hashmap�� �ڽŰ� ������ ��� hashmap�� ������־����ϴ�.

~~~java
		HashMap<String,String> parents = new HashMap<>();
		HashMap<String,Integer> order = new HashMap<>();
		
		for(int i=0; i<enroll.length; i++){
            String child = enroll[i];
            String parent = referral[i];
            parents.put(child,parent);
            order.put(child,i);
		}
~~~

�׸��� seller�� �迭�� ���������� ���鼭 ��õ����  "-" �̸� break, �ƴҰ�� ���� ��õ���� ������ �Ǿ� �ٽ� �ݺ����� ���� ĩ���� �ǸŰ��� �����ؼ� �����־����ϴ�.
�� �� �ڽ��� �������� ���� ��õ�ο��� 10%�� ���� ���� ���̹Ƿ� amount[�ڽ��� �ε�����] - (amount[�ڽ��� �ε�����]/10)�� �����ָ� �˴ϴ�.

~~~java
		for(int i=0; i<seller.length; i++){
            String child = seller[i];
            String parent = (String)parents.get(child);
            int money = amount[i]*100;
            while(true){
                int index = order.get(child);
                answer[index] += money-money/10;
                
                if(parent.equals("-")){
                    break;
                }
                
                child = parent;
                parent = (String)parents.get(child);
                money = money/10; 
 
                if(money<1){
                    break;
                }
            }
        }
~~~


## ������
ó���� 11~13���� �ð��ʰ��� ���Խ��ϴ�. �ٸ� ����� �ڵ带 �����ϸ� ���� ���� 10%�� ��õ�ο��� �ִٺ��� ���� ������ ���� �ʴ� ��츦 ������� �ʾƼ� ����� ���������ϴ�. ���� while�� �ȿ� money�� 1���� ���� ��츦 break�� ���־� �̸� �ذ��߽��ϴ�.

~~~java
                if(money<1){
                    break;
                }
~~~

## �ҽ��ڵ�
~~~java
import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String,String> parents = new HashMap<>();
        HashMap<String,Integer> order = new HashMap<>();
        
        for(int i=0; i<enroll.length; i++){
            String child = enroll[i];
            String parent = referral[i];
            parents.put(child,parent);
            order.put(child,i);
        }
        
        for(int i=0; i<seller.length; i++){
            String child = seller[i];
            String parent = (String)parents.get(child);
            int money = amount[i]*100;
            while(true){
                int index = order.get(child);
                answer[index] += money-money/10;
                
                if(parent.equals("-")){
                    break;
                }
                
                child = parent;
                parent = (String)parents.get(child);
                money = money/10; 
 
                if(money<1){
                    break;
                }
            }
        }
        return answer;
    }
}
~~~

## ���

|��Ȯ��|�׽�Ʈ|
|-----|-----|
|�׽�Ʈ 1 |	��� (0.04ms, 71.8MB)|
|�׽�Ʈ 2 |	��� (0.07ms, 74.5MB)|
|�׽�Ʈ 3 |	��� (0.08ms, 73.6MB)|
|�׽�Ʈ 4 |	��� (0.14ms, 85.4MB)|
|�׽�Ʈ 5 |	��� (1.26ms, 78MB)|
|�׽�Ʈ 6 |	��� (2.64ms, 106MB)|
|�׽�Ʈ 7 |	��� (2.86ms, 106MB)|
|�׽�Ʈ 8 |	��� (4.12ms, 104MB)|
|�׽�Ʈ 9 |	��� (22.17ms, 125MB)|
|�׽�Ʈ 10 |	��� (62.95ms, 133MB)|
|�׽�Ʈ 11 |	��� (33.03ms, 147MB)|
|�׽�Ʈ 12 |	��� (40.74ms, 140MB)|
|�׽�Ʈ 13 |	��� (36.01ms, 130MB)|