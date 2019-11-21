package ReadFromJson;

import com.yworks.yfiles.geometry.IOrientedRectangle;

public class EdgeLabel {
	String text;
	double x_Value = 0.0;
	double y_Value;
	double height;
	double width;
	double angle;
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public EdgeLabel(IOrientedRectangle layout, String text) {
		this.text = text;
		this.x_Value = layout.getAnchorX();
		this.y_Value = layout.getAnchorY();
		this.height = layout.getHeight();
		this.width = layout.getWidth();
		this.angle = Math.atan(layout.getUpY()/layout.getUpX());
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
		return "EdgeLabel [text=" + text + ", x_Value=" + x_Value + ", y_Value=" + y_Value + ", height=" + height
				+ ", width=" + width + ", angle=" + angle + "]";
	}
	
}
