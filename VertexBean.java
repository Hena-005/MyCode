package ReadFromJson;

public class VertexBean {
	
	public String id;
	
	public String objectId;
	
	public String name;
	
	public Properties[] properties;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Properties[] getProperties() {
		return properties;
	}

	public void setProperties(Properties[] properties) {
		this.properties = properties;
	} 

	@Override
	public String toString() {
		return "VertexBean [id=" + id + ", objectId=" + objectId + ", name=" + name + ", properties=" + properties
				+ ']';
	}
}
