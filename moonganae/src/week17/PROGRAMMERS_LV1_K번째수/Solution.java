package week17.PROGRAMMERS_LV1_K번째수;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int idx=0; idx<commands.length; idx++){
            int i = commands[idx][0];
            int j = commands[idx][1];
            int k = commands[idx][2];
            
            // 내가 사용할 범위 가져오기
            int[] arr = new int[j-i+1];
            
            for(int z=i-1; z<j;z++){
                arr[z-(i-1)] = array[z];
            }
            
            // 병합정렬
            divide(arr, 0, arr.length-1);
            
            // 재정렬된 배열에서 K번째 수 가져오기
            answer[idx] = arr[k-1];
        }
        
        return answer;
    }
    // 분할
    public void divide(int[] arr, int left, int right){
        // 같은 곳을 가르키면 재귀종료
    	if(left == right) return;
        
        int mid = (left + right) / 2;
        
        // 왼쪽정렬
        divide(arr, left, mid);
        // 오른쪽정렬
        divide(arr, mid+1, right);
        // 병합
        merge(arr, left, right);
        
    }
    // 합병
    public void merge(int[] arr, int left, int right){
        int mid = (left + right) / 2;
        
        int L = left;
        int R = mid+1;
        
        
        int[] tmp = new int[right-left+1];
        int idx = 0;
        
        // 정렬하면서 병합
        while(L <= mid && R <= right)
            tmp[idx++] = arr[L] <= arr[R] ? arr[L++] : arr[R++];
        
        // 오른쪽 영역 다사용하기
        if(L>mid){
            for(int i=R; i<=right; i++)
                tmp[idx++] = arr[i];
        }
        // 왼쪽영역 다 사용하기
        else{
            for(int i=L; i<=mid; i++)
                tmp[idx++] = arr[i];
        }
        // 원본배열에 넣기
        for(int i=0; i<tmp.length; i++)
            arr[left+i] = tmp[i];
    }
}