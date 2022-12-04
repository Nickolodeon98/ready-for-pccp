package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {
    private final static int INFINITY = 2147483647; // 무한대를 나타낸다. 자바에서 정수가 표현할 수 있는 가장 큰 수이다.
    public static List<Integer> findShortestPaths(List<List<Integer>> weightedGraph) {
        int numOfVertices = weightedGraph.get(0).size();

        List<Integer> dist = new ArrayList<>();
        int current;
        List<Boolean> visitedInfo = new ArrayList<>();

        dist.add(0); // 처음 정점과 처음 정점 간의 거리는 유일하게 알려져 있고, 0 이다.

        while (dist.size() < numOfVertices) {
            dist.add(INFINITY); // 시작점부터 나머지 정점까지의 거리는 모두 아직 안 알려져 무한대이다.
        }

        while (visitedInfo.size() < numOfVertices) {
            visitedInfo.add(false); // 모든 정점을 아직 방문되지 않았다고 명시하며 초기화한다.
        }

        while (visitedInfo.contains(false)) { // 아직 방문되지 않은 정점이 존재하는 한 반복한다.
            while (dist.size() > 0) {
                int toVisit = findMinIdx(dist); // 시작점으로부터 가장 가까운 곳을 찾는다.

                if (visitedInfo.get(toVisit) == false) {
                    visitedInfo.set(toVisit, true); // 방문한 곳이 아니라면, 방문한다.
                    break;
                }
                dist.remove(toVisit);
            }

        }
        return dist;
    }

    private static int findMinIdx(List<Integer> data) {
        int minIdx = 0;
        int min = data.get(minIdx);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) < min) {
                minIdx = i;
                min = data.get(i);
            }
        }
        return minIdx;
    }


    public static void main(String[] args) {
        int[][] vertices = new int[][]{{0, 3, 5, 1}, {3, 0, -1, 4}, {5, -1, 0, 7}, {-1, 4, 7, 0}};
        List<List<Integer>> weightedGraph = new ArrayList<>();
        List<Integer> vertex = List.of();

        for (int[] ints : vertices) {
            vertex = Arrays.asList(ints[0], ints[1], ints[2], ints[3]);
            weightedGraph.add(vertex);
        }
        System.out.println(weightedGraph);

        findShortestPaths(weightedGraph);
    }
}
