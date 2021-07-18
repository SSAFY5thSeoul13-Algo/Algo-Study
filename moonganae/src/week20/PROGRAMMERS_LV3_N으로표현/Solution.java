package week20.PROGRAMMERS_LV3_N으로표현;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
class Solution {
	
	public static void main(String[] args) {
		System.out.println(solution(2, 11));
	}
	
    public static int solution(int N, int number) {
    	int answer = 0;
        
        Set<Integer>[] set = new HashSet[9];
        
        for(int i=1; i<=8; i++){
            set[i] = new HashSet<Integer>();
        }
        int tmpN = N;
        // i : 1~8까지 순회
        for(int i=1; i<=8; i++){
            set[i].add(tmpN);
            // j : 1~i까지 순회
            for(int j=1; j<i; j++){
                // j번 아이템 순회
                for(int a: set[j]){
                    // i-j번 아이템 순회
                    for(int b : set[i-j]){
                        // 0인 경우는 의미가 없기때문에 제외
                    	if(b==0) continue;
                    	// 덧셈
                        set[i].add(a + b);
                        // 뺄셈
                        if(a-b >= 0)
                            set[i].add(a-b);
                        // 곱셈
                        set[i].add(a*b);
                        
                        // 나눗셈
                        set[i].add(a/b);
                    }
                }
            }
            
            for(int item : set[i]){
                if(item == number)
                    return i;
            }
            tmpN = 10 * tmpN + N;
        }
        // 모든 순회를 마치고도 찾지못했다면 도달할 수 없는 숫자이다.
        return -1;
    }
}
