package week32.BOJ_2610_G2_회의준비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 사람수, 관계수
    static int N, M;
    static final int INF = 1000;

    // Union & find
    static int[] parent;

    // 관계배열
    static int[][] A;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N];
        A = new int[N][N];

        // 관계배열 및 부모배열 초기화
        for(int i=0; i<N; i++){
            Arrays.fill(A[i], INF);
            parent[i] = i;
        }

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 인덱스가 0부터 시작하기 때문에 -1
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            // 결합
            union(a,b);

            // 의견 경로의 wegith는 1
            A[a][b] = A[b][a] = 1;
        }

        // 플로이드 워샬 - 의견전달시간 구하기
        floydWarshall();

        // 위원장들 찾기
        List<int[]> leaders = findLeaders();

        // 위원장 인덱스 순으로 정렬
        Collections.sort(leaders, (e1, e2) -> e1[0]-e2[0]);
        // 위원회수 출력
        System.out.println(leaders.size());
        // 위원장 인덱스 출력
        for(int[] group : leaders){
            System.out.println(group[0] + 1);
        }
    }

    static int find(int t){
        if(t == parent[t]) return t;

        return parent[t] = find(parent[t]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa <= pb) parent[pb] = pa;
        else parent[pa] = pb;
    }

    static void floydWarshall(){
        // 거쳐가는 노드
        for(int k=0; k<N; k++){
            // 출발노드
            for(int i=0; i<N; i++){
                // 도착노드
                for(int j=0; j<N; j++){
                    if(i==j) continue;
                    if(A[i][k] + A[k][j] < A[i][j])
                        A[i][j] = A[i][k] + A[k][j];
                }
            }
        }
    }

    static List<int[]> findLeaders(){

        // 위원장 리스트 (int[0] : 위원장 인덱스번호, int[1] : 최대 의견전달시간)
        List<int[]> leaders = new ArrayList<>();

        // 각 그룹별 leaders 리스트의 인덱스 보관
        int[] idxs = new int[N];
        for(int i=0; i<N; i++){
            idxs[i] = -1;
        }

        for(int i=0; i<N; i++){

            int max = 0, group = find(i);

            // 최대 의견전달 시간 구하기
            for(int j=0; j<N; j++){
                if(A[i][j] == INF) continue;
                max = Math.max(max, A[i][j]);
            }

            int groupIdx = idxs[group];

            // 아직 그룹의 최대 의견전달 시간이 없을때
            if(groupIdx == -1){
                idxs[group] = leaders.size();
                leaders.add(new int[]{i,max});
            }else if(leaders.get(groupIdx)[1] > max){
                // 위원장 및 의견전달 시간 갱신
                leaders.get(groupIdx)[0] = i;
                leaders.get(groupIdx)[1] = max;
            }
        }

        return leaders;
    }


    static void printA(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void printBoss(){
        for (int i = 0; i < N; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
    }
}
