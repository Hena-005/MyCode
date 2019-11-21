package ReadFromJson;

import java.util.ArrayList;
import java.util.List;

public class GraphData {
	
	List<Nodes> nodes= new ArrayList<Nodes>();

	List<Edgedata> edges=new ArrayList<Edgedata>();
	
	List<NodeLabel> nodeLabels=new ArrayList<NodeLabel>();
	
	List<EdgeLabel> edgeLabels=new ArrayList<EdgeLabel>();

	public List<EdgeLabel> getEdgeLabels() {
		return edgeLabels;
	}

	public void setEdgeLabels(List<EdgeLabel> edgeLabels) {
		this.edgeLabels = edgeLabels;
	}

	public List<Nodes> getNodes() {
		return nodes;
	}

	public List<NodeLabel> getNodeLabels() {
		return nodeLabels;
	}

	public void setNodeLabels(List<NodeLabel> nodeLabels) {
		this.nodeLabels = nodeLabels;
	}

	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}

	public List<Edgedata> getEdges() {
		return edges;
	}

	public void setEdges(List<Edgedata> edges) {
		this.edges = edges;
	}

	@Override
	public String toString() {
		return "GraphData [nodes=" + nodes + ", edges=" + edges + ", nodeLabels=" + nodeLabels + ", edgeLabels="
				+ edgeLabels + "]";
	}

//	public GraphData(Nodes[] nodes,Edgedata[] edges) {
//		this.nodes = nodes;
//		this.edges=edges;
//	}


	
	
}
