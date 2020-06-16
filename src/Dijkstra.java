
import java.util.*;

public class Dijkstra {

    /*
    * This method takes a Dijkstra Object,
    * reads a predefined file
    * and creates the initial graph
    * !DID NOT WORK!
    * */
//    public void readFile(Dijkstra d, File file) throws FileNotFoundException {
//        Scanner reader = new Scanner(new FileReader(file)).useLocale(Locale.US);
//        d.n = reader.nextInt();
//        reader.nextInt();
//        while(reader.hasNext()){
//            d.addEdge(reader.nextInt(), reader.nextInt(), reader.nextDouble());
//        }
//    }

    /*
    * An edge class to represent a directed edge
    * between two nodes with a certain cost.
    * */

    private final int n;

    private int edgeCount;
    private Integer[] prev;
    private List<List<Edge>> graph;

    public Dijkstra(int n) {
        this.n = n;
        createEmptyGraph();
    }

    // Construct an empty graph with n nodes including the source and sink nodes.
    private void createEmptyGraph() {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    }

    public void addEdge(int from, int to, double cost) {
        edgeCount++;
        graph.get(from).add(new Edge(to, cost));
    }

    public List<List<Edge>> getGraph() {
        return graph;
    }

    // Run Dijkstra's algorithm on a directed graph to find the shortest path
    // from a starting node to an ending node. If there is no path between the
    // starting node and the destination node the returned value is set to be
    // Double.POSITIVE_INFINITY.
    public double dijkstra(int start, int end) {

        // Keep an Indexed Priority Queue (ipq) of the next most promising node
        // to visit.
        int degree = edgeCount / n;
        MinIndexedHeap<Double> ipq = new MinIndexedHeap<>(degree, n);
        ipq.insert(start, 0.0);

        // Maintain an array of the minimum distance to each node.
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0.0;

        boolean[] visited = new boolean[n];
        prev = new Integer[n];

        while (!ipq.isEmpty()) {
            int nodeId = ipq.peekMinKeyIndex();

            visited[nodeId] = true;
            double minValue = ipq.pollMinValue();

            // We already found a better path before we got to
            // processing this node so we can ignore it.
            if (minValue > dist[nodeId]) continue;

            for (Edge edge : graph.get(nodeId)) {

                // We cannot get a shorter path by revisiting
                // a node we have already visited before.
                if (visited[edge.to]) continue;

                // Relax edge by updating minimum cost if applicable.
                double newDist = dist[nodeId] + edge.cost;
                if (newDist < dist[edge.to]) {
                    prev[edge.to] = nodeId;
                    dist[edge.to] = newDist;
                    // Insert the cost of going to a node for the first time in the PQ,
                    // or try and update it to a better value by calling decrease.
                    if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                    else ipq.decrease(edge.to, newDist);
                }
            }
            // Once we've processed the end node we can return early (without
            // necessarily visiting the whole graph) because we know we cannot get a
            // shorter path by routing through any other nodes since Dijkstra's is
            // greedy and there are no negative edge weights.
            if (nodeId == end) return dist[end];
        }
        // End node is unreachable.
        return Double.POSITIVE_INFINITY;
    }

    public List<Integer> reconstructPath(int start, int end) {
        if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
        if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
        List<Integer> path = new ArrayList<>();
        double dist = dijkstra(start, end);
        if (dist == Double.POSITIVE_INFINITY) {
            System.out.println("Destination unreachable!");
            return path;
        }
        for (Integer at = end; at != null; at = prev[at]) path.add(at);
        Collections.reverse(path);
        return path;
    }


}