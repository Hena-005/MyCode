package ReadFromJson;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yworks.yfiles.geometry.SizeD;
import com.yworks.yfiles.graph.IBend;
import com.yworks.yfiles.graph.IEdge;
import com.yworks.yfiles.graph.IGraph;
import com.yworks.yfiles.graph.ILabel;
import com.yworks.yfiles.graph.INode;
import com.yworks.yfiles.graph.styles.ShinyPlateNodeStyle;
import com.yworks.yfiles.layout.LayoutOrientation;
import com.yworks.yfiles.layout.hierarchic.HierarchicLayout;
import com.yworks.yfiles.utils.IListEnumerable;
import com.yworks.yfiles.view.GraphComponent;
import com.yworks.yfiles.view.input.GraphViewerInputMode;

import databinding.ReadFromJson.EdgesBeanFactory;
import databinding.ReadFromJson.ResponseBean;
import databinding.ReadFromJson.VertexBean;
import tutorial02_CustomStyles.step11_CustomLabelStyle.MySimpleLabelStyle;

public class JSONReader {
	static ResponseBean responseBean = new ResponseBean();

	static VertexBean[] vertices;

	static EdgesBeanFactory[] edges;

	static GraphComponent graphComponent = new GraphComponent();

	public static void main(String[] args) {
		readFile();

		createGraph();

	}

	private static void createGraph() {
		// TODO Auto-generated method stub
		//init();
		IGraph graph = graphComponent.getGraph();
		graph.setUndoEngineEnabled(true);
//		graph.getNodeDefaults().getLabelDefaults().setStyle(new MySimpleLabelStyle());
//		graph.getEdgeDefaults().getLabelDefaults().setStyle(new MySimpleLabelStyle());
		
		vertices = responseBean.getVertices();
		List<INode> nodeList = new ArrayList<INode>();
		INode nodeCreated;
		for (VertexBean vertex : vertices) {
			nodeCreated = graph.createNode();
			nodeCreated.setTag(vertex.getId());
			graph.addLabel(nodeCreated, vertex.id);
			nodeList.add(nodeCreated);
		}
		graph.getNodeDefaults().setSize(new SizeD(40, 40));
		ShinyPlateNodeStyle defaultNodeStyle = new ShinyPlateNodeStyle();
		defaultNodeStyle.setPaint(Color.ORANGE);
		graph.getNodeDefaults().setStyle(defaultNodeStyle);
		edges = responseBean.getEdges();
		IEdge edgeCreated;
		for (EdgesBeanFactory edge : edges) {
			INode start = nodeList.stream().filter(p -> p.getTag().equals(edge.source)).collect(Collectors.toList())
					.get(0);
			INode end = nodeList.stream().filter(p -> p.getTag().equals(edge.target)).collect(Collectors.toList())
					.get(0);
			edgeCreated = graph.createEdge(start, end);
			graph.addLabel(edgeCreated, edge.label);
		}

		HierarchicLayout layout = new HierarchicLayout();
		layout.setLayoutOrientation(LayoutOrientation.LEFT_TO_RIGHT);
		graph.applyLayout(layout);

		graphComponent.fitGraphBounds();
		IGraph finalGraph = graphComponent.getGraph();
		
		List<Nodes> jsonNodes = extractNodes(finalGraph);
		List<Edgedata> jsonEdges = extractEdges(finalGraph);
		List<NodeLabel> jsonNodeLabels = new ArrayList<NodeLabel>();
		jsonNodeLabels = extractNodeLabels(jsonNodeLabels,finalGraph);
		List<EdgeLabel> edgeLabels = new ArrayList<EdgeLabel>();
		edgeLabels = extractEdgeLabels(edgeLabels,finalGraph);
		
		GraphData graphData = new GraphData();
		graphData.setEdges(jsonEdges);
		graphData.setNodes(jsonNodes);
		graphData.setNodeLabels(jsonNodeLabels);
		graphData.setEdgeLabels(edgeLabels);

		writeDataToJson(graphData);

	}

	private static void init() {
		// TODO Auto-generated method stub
		graphComponent.setInputMode(new GraphViewerInputMode());
		JFrame frame = new JFrame("Hello, yFiles for Java (Swing)");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(graphComponent, BorderLayout.CENTER);
	}

	private static void writeDataToJson(GraphData graphData) {
		String jsonString = "";

		ObjectMapper Obj = new ObjectMapper();
		try {
			jsonString = Obj.writeValueAsString(graphData);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			FileWriter fw = new FileWriter("D:\\Maps_WS\\TrialSVG\\YfilesJava\\demos\\tutorials\\ReadFromJson\\testout.json");
			fw.write(jsonString);
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Success...");
	}

	private static List<EdgeLabel> extractEdgeLabels(List<EdgeLabel> edgeLabels, IGraph finalGraph) {
		
		EdgeLabel edgeLabel;
		for(ILabel label: finalGraph.getEdgeLabels()) {
			edgeLabel = new EdgeLabel(label.getLayout(),label.getText());
			edgeLabels.add(edgeLabel);
		}
		
		return edgeLabels;
	}

	private static List<NodeLabel> extractNodeLabels(List<NodeLabel> jsonNodeLabels, IGraph finalGraph) {

		NodeLabel nodeLabel;
		for(ILabel label:finalGraph.getNodeLabels()) {
			
			nodeLabel = new NodeLabel(label.getLayout(),label.getText());
			jsonNodeLabels.add(nodeLabel);
		}
		
		return jsonNodeLabels;
	}

	private static List<Nodes> extractNodes(IGraph finalGraph) {
		List<Nodes> jsonNodes = new ArrayList<Nodes>();
		Nodes jsonNode;

		for (INode node : finalGraph.getNodes()) {
			jsonNode = new Nodes(node.getLayout(), node.getStyle(), node.getTag());
			jsonNodes.add(jsonNode);

		}
		return jsonNodes;
	}

	private static List<Edgedata> extractEdges(IGraph finalGraph) {
		List<Edgedata> jsonEdges = new ArrayList<Edgedata>();
		Edgedata jsonEdge;
		double[][] midPoints;
		double[][] bendPoints;
		StringBuilder tag = new StringBuilder();
		for (IEdge edge : finalGraph.getEdges()) {
			tag = new StringBuilder();
			midPoints = new double[edge.getBends().size()][2];
			bendPoints = findBends(midPoints, edge.getBends());
			tag.append(edge.getSourceNode().getTag()).append('_').append(edge.getTargetNode().getTag());
			edge.setTag(tag.toString());
			jsonEdge = new Edgedata(edge.getSourcePort().getLocation(), bendPoints, edge.getTargetPort().getLocation(),
					edge.getStyle(), edge.getTag());
			jsonEdges.add(jsonEdge);
		}
		return jsonEdges;
	}

	private static double[][] findBends(double[][] midPoints, IListEnumerable<IBend> bends) {
		int i = 0;

		for (IBend bend : bends) {
			midPoints[i][0] = bend.getLocation().getX();
			midPoints[i][1] = bend.getLocation().getY();
			i++;
		}
		if (midPoints == null || midPoints.length == 0 || midPoints[0].length == 0) {
			return null;
		}
		return midPoints;
	}

	private static void readFile() {

		String path = "D:\\Maps_WS\\TrialSVG\\YfilesJava\\demos\\src\\databinding\\ReadFromJson\\response.json";
		File file = new File(path);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			responseBean = objectMapper.readValue(file, ResponseBean.class);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
