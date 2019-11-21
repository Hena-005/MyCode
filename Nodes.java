package ReadFromJson;

import com.yworks.yfiles.geometry.IRectangle;
import com.yworks.yfiles.graph.styles.INodeStyle;

public class Nodes {
	String id;
	double x_Value = 0.0;
	double y_Value;
	double height;
	double width;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// INodeStyle style;
	public Nodes(IRectangle layout, INodeStyle style, Object object) {
		this.id = (String) object;
		this.x_Value = layout.getX();
		this.y_Value = layout.getY();
		this.height = layout.getHeight();
		this.width = layout.getWidth();
		// this.style = style;

	}

	public double getX_Value() {
		return x_Value;
	}

	public void setX_Value(double x_Value) {
		this.x_Value = x_Value;
	}

	public double getY_Value() {
		return y_Value;
	}

	public void setY_Value(double y_Value) {
		this.y_Value = y_Value;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "Nodes [id=" + id + ", x_Value=" + x_Value + ", y_Value=" + y_Value + ", height=" + height + ", width="
				+ width + "]";
	}

}
