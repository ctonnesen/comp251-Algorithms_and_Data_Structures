import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph) {
		Stack<Integer> toVisit = new Stack<>();
		ArrayList<Integer> visited = new ArrayList<>();
		HashMap<Integer, Integer> parents = new HashMap<>();
		toVisit.push(0);
		boolean flag = false;
		while (!toVisit.isEmpty()) {
			int node = toVisit.pop();
			visited.add(node);
			if (node == destination) {
				flag = true;
				break;
			}
			for (Edge curEdge : graph.getEdges()) {
				if ((curEdge.nodes[0] == node)&&(curEdge.weight>0)&&(!visited.contains(curEdge.nodes[1]))) {
					toVisit.push(curEdge.nodes[1]);
					parents.put(curEdge.nodes[1], curEdge.nodes[0]);
				}
			}
		}
		if (flag) {
			ArrayList<Integer> path = new ArrayList<>();
			int current = destination;
			while (current != source) {
				path.add(0, current);
				current = parents.get(current);
			}
			path.add(0, source);
			return path;
		}
		return new ArrayList<>();
	}


	public static String fordfulkerson(WGraph graph) {
		ArrayList<Integer> result = pathDFS(graph.getSource(), graph.getDestination(), graph);
		WGraph residual = new WGraph(graph);
//		for (Edge curEdge : graph.getEdges()) {
//			int parent = curEdge.nodes[0];
//			int child = curEdge.nodes[1];
//			residual.setEdge(parent,child,graph.getEdge(parent,child).weight);
//			Edge newEdge = new Edge(child,parent,0);
//			residual.addEdge(newEdge);
//		}
		String answer = "";
		int maxFlow = 0;
		while (!result.isEmpty()) {
			int flow = Integer.MAX_VALUE;
			int parentNode;
			int childNode;
			for (int i=0; i < result.size()-1; i++) {
				parentNode = result.get(i);
				childNode = result.get(i+1);
				flow = Math.min(flow,residual.getEdge(parentNode,childNode).weight); // Could change to graph
			}
			for (int i=0; i < result.size()-1; i++) {
				parentNode = result.get(i);
				childNode = result.get(i+1);
				residual.getEdge(parentNode,childNode).weight -= flow;  // Change to graph
//				residual.getEdge(childNode,parentNode).weight += flow;
			}
			maxFlow += flow;
			result = pathDFS(graph.getSource(),graph.getDestination(),residual); // Could change to graph
		}
		for (Edge curEdge : graph.getEdges()) {
			int parent = curEdge.nodes[0];
			int child = curEdge.nodes[1];
			int newFlow = graph.getEdge(parent,child).weight - residual.getEdge(parent,child).weight;
			graph.setEdge(parent,child, newFlow);
		}
//		for (Edge curEdge : graph.getEdges()) {
//			if (curEdge.nodes[1] == graph.getDestination()) {
//				maxFlow += curEdge.weight;
//			}
//		}
		answer += maxFlow + "\n" + graph.toString();
		return answer;

	}
	

	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
	 }

}

