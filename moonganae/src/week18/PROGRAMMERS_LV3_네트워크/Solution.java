package week18.PROGRAMMERS_LV3_네트워크;

import java.util.*;
class Solution {
    int[] parent;	// 부모배열
    public int solution(int n, int[][] computers) {
        int answer = 1;
        init(n);
        
        // Union 작업
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j) continue;
                if(computers[i][j] == 1)
                    union(i,j);
            }
        }
        // 최종적으로 같은 네트워크인지 판별
        for(int i=0; i<n; i++){
            find(i);
        }
        // 정렬
        Arrays.sort(parent);
        
        // 다른 숫자가 등장 == 다른 네트워크가 있다.
        int cur = parent[0];
        for(int i=1; i<n; i++){
            if(cur != parent[i]){
                answer++;
                cur = parent[i];
            }
        }
        
        return answer;
    }
    // 부모배열 초기화
    public void init(int n){
        parent = new int[n];
        for(int i=0; i<n; i++)
            parent[i] = i;
    }
    // 부모찾기
    public int find(int a){
        if(parent[a] == a){
            return a;
        } else{
            return parent[a] = find(parent[a]);
        }
    }
    // 결합
    public void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        
        parent[pb] = pa;
    }
}

