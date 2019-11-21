package ReadFromJson;

import com.yworks.yfiles.geometry.PointD;
import com.yworks.yfiles.graph.styles.IEdgeStyle;

public class Edgedata {

	String id;

	double[] startPortLocation = new double[2];

	double[][] midPortLocation;

	double[] endPortLocation = new double[2];

	public Edgedata(PointD startPoint, double[][] midPoints, PointD endPoint, IEdgeStyle style, Object id) {

		this.id = String.valueOf(id);

		this.startPortLocation[0] = startPoint.getX();
		this.startPortLocation[1]=startPoint.getY();

		this.endPortLocation[0] = endPoint.getX();
		this.endPortLocation[1] = endPoint.getY();

		// this.style = style;

		this.midPortLocation = midPoints;

	}

	// IEdgeStyle style;

	public double[] getStartPortLocation() {
		return startPortLocation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStartPortLocation(double[] startPortLocation) {
		this.startPortLocation = startPortLocation;
	}

	public double[][] getMidPortLocation() {
		return midPortLocation;
	}

	public void setMidPortLocation(double[][] midPortLocation) {
		this.midPortLocation = midPortLocation;
	}

	public double[] getEndPortLocation() {
		return endPortLocation;
	}

	public void setEndPortLocation(double[] endPortLocation) {
		this.endPortLocation = endPortLocation;
	}

	@Override
	public String toString() {
		return "Edgedata [id=" + id + "startPortLocation=" + startPortLocation + ", midPortLocation=" + midPortLocation
				+ ", endPortLocation=" + endPortLocation + "]";
	}

//	public IEdgeStyle getStyle() {
//		return style;
//	}
//
//	public void setStyle(IEdgeStyle style) {
//		this.style = style;
//	}

}
