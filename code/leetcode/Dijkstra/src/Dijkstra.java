import java.util.Scanner;

public class Dijkstra {
    int start;              //起始点
    int n;                  //节点数
    int edges;              //边数
    int[][] graph;          //邻接矩阵
    int[] dist;             //dist[i]是"顶点start"到"顶点i"的最短路径的长度
    boolean[] visit;        //标记当前顶点是否已被遍历的数组

    public static void main(String[] args) {
        Dijkstra test = new Dijkstra();
        test.dijkstra();;
    }

    public void dijkstra(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        edges = sc.nextInt();
        start = sc.nextInt();

        /**
         * 初始化
         */
        graph = new int[n][n];
        dist = new int[n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.MAX_VALUE;
                if (i==j)
                    graph[i][j] = 0;
            }
        }

        /**
         * 输入邻接矩阵
         * example
         * 1 2 5 : graph[1][2] = 5
         * 2 3 7 : graph[2][3] = 7
         */
        for (int i = 0; i < edges; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            graph[node1][node2] = sc.nextInt();
        }

        /**
         * 初始化
         * dist[i]是"顶点start"到"顶点i"的最短路径的长度。
         * prev[i]的值是"顶点start"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点
         * */

        for (int i = 0; i < n; i++) {
            dist[i] = graph[start][i];
            visit[i] = false;
        }
        dist[start] = 0;
        visit[start] = true;

        /**
         * 遍历n次，每次找出一个顶点的最短路劲
         */
        int k = 0;                      // 顶点k为获取到的最短路径
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;    // 初始化最短路径
            // 寻找当前最小的路径；
            // 即，在未获取最短路径的顶点中，找到离vs最近的顶点(k)。
            for (int j = 0; j < n; j++) {
                if (!visit[j] && dist[j] < min) {
                    min = dist[j];
                    k = j;
                }
            }
            visit[k] = true;                // 标记"顶点k"为已经获取到最短路径

            // 修正当前最短路径和前驱顶点
            // 即，当已经"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"。
            for (int j = 0; j < n; j++) {
                int dist_kj;
                //这里需要加上对min(即dist[k])的判断条件，当源点没有向外的边时有用，因为此时所有的dist都为Integer.MAX_VALUE
                if (graph[k][j]==Integer.MAX_VALUE || min==Integer.MAX_VALUE)
                    dist_kj = Integer.MAX_VALUE;
                else
                    dist_kj = min + graph[k][j];
                //更新最短路径
                if (!visit[j] && dist[j] > dist_kj) {
                    dist[j] = dist_kj;
                }
            }
        }

        //输出结果
        for (int i = 0; i < n; i++) {
            if (dist[i]==Integer.MAX_VALUE)
                System.out.println("dist(" + start + ", " + i + "): NO");
            else
                System.out.println("dist(" + start + ", " + i + "): "+dist[i]);

        }

    }
}
