package ReadFromJson;

import com.fasterxml.jackson.annotation.JsonSetter;

public class EdgesBeanFactory {
	
	public String source;
	
	public String target;
	
	public String label;

	public String getSource() {
		return source;
	}

	@JsonSetter("source")
	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	@JsonSetter("target")
	public void setTarget(String target) {
		this.target = target;
	}

	public String getLabel() {
		return label;
	}

	@JsonSetter("label")
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "EdgesBeanFactory [source=" + source + ", target=" + target + ", label=" + label + ']';
	}
	
	

}
