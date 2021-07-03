package week17.PROGRAMMERS_LV2_소수찾기;

import java.util.*;
class Solution {
	/* 찾은 소수를 보관할 집합 */
    public Set<Integer> set = new HashSet<>();
    /* 종이조각들 */
    public char[] arr = null;
    public int solution(String numbers) {
        
        arr = numbers.toCharArray();
        StringBuilder sb = new StringBuilder("0");
        perm(0, 0, sb);
        
        return set.size();
    }
    /**
     *  종이조각으로 만들 수 있는 모든 수의 조합식 == 순열
     * @param num : 현재 확인한 종이수
     * @param mask : 내가 선택한 종이조각
     * @param sb : 선택한 종이조각으로 만든 수
     */
    public void perm(int num, int mask, StringBuilder sb){
        
    	// 종이조각 갯수보다 크면 종료
        if(num > arr.length){
            return;
        }
        // System.out.println(sb);
        
        // 현재 만든 순열
        int n = Integer.parseInt(sb.toString());
        // 소수이면서, 이미 찾은 수가 아니라면
        if(isPrime(n) && !set.contains(n)){
            // System.out.println(n + "은 소수");
        	// 찾은 수에 넣기
            set.add(n);
        }
     
        for(int i=0; i<arr.length; i++){
        	// 선택한 종이라면 넘어가기
            if( (mask & 1<<i) != 0) continue;
            
            sb.append(arr[i]);
            perm(i+1, mask | 1<<i, sb);
            sb.setLength(sb.length()-1);
        }
    }
    
    /**
     * 
     * @param num : 소수인지 판단하려는 수
     * @return : 소수인지 아닌지
     */
    public boolean isPrime(int num){
        // 1이하이면 소수가 아님
        if(num <= 1) return false;
        // 2는 소수
        if(num == 2) return true;
        // 짝수는 소수가 아님
        if(num % 2 == 0) return false;
        // 에라토스테네스의 체 알고리즘
        for(int i=3; i*i<=num; i+=2){
            if(num % i == 0)
                return false;
        }
        
        return true;
    }
}