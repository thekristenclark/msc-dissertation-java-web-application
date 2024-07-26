package  com.dissertation.WritingApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Editor {
	@Id
	private String id;
	
	@Field
	private String userid;
	
	@Field
	private String idea;

	@Field
	private String summary;
	
	@Field
	private String createdate;
	
	public Editor() {}
	
	public Editor(String userid, String idea, String summary, String createdate) {
		super();
		this.userid=userid;
		this.idea=idea;
		this.summary=summary;
		this.createdate=createdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

//	@Override
//	public String toString() {
//		return String.format("User[id='$s', firstname='$s', lastname='$s']", id, firstName, lastName);
//	}	
}