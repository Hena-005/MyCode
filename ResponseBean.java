package ReadFromJson;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ResponseBean {

	
	public VertexBean[] vertices;
	
	public EdgesBeanFactory[] edges;
	
	//public String[] ipaths;
	public ArrayList<String>[] paths = new ArrayList[10];

	
	public VertexBean[] getVertices() {
		return vertices;
	}

	@JsonSetter("vertices")
	public void setVertices(VertexBean[] vertices) {
		this.vertices = vertices;
	}

	
	public EdgesBeanFactory[] getEdges() {
		return edges;
	}

	@JsonSetter("edges")
	public void setEdges(EdgesBeanFactory[] edges) {
		this.edges = edges;
	}

	public ArrayList<String>[] getPaths() {
		return paths;
	}

	@JsonSetter("paths")
	public void setPaths(ArrayList<String>[] paths) {
		this.paths = paths;
	}

	
	
}
