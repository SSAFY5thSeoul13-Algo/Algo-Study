### [문제풀이]
빈도 수와 들어온 순번을 저장하는 node 클래스를 만들어서 그것을 value로 TreeMap을 만들었습니다. 숫자를 key 값으로 하는 트리맵에 입력한 숫자의 cnt를 갱신하고 모든 숫자의 입력이 끝나면 정렬을 했습니다.
정렬은 Collections.sort를 사용해서 value에 들어있는 node의 cnt의 내림차순으로 하되 cnt가 같으면 order를 기준으로 오름차순으로 정렬했습니다.
정렬이 끝나면 foreach문을 사용해서 TreeMap에 있는 각 데이터의 value를 받아서 key값에 저장된 num을 cnt값만큼 출력했습니다.

### [결과]
메모리 : 12992 KB
시간 : 124 ms