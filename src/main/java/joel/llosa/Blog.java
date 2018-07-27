package joel.llosa;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region(value = "Blog")
public class Blog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private final String url;
	
	private String title;
	
	@PersistenceConstructor
	public Blog(String url, String title) {
		this.url = url;
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Blog [url=" + url + ", title=" + title + "]";
	}
	
}
