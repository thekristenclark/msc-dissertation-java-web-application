package  com.dissertation.WritingApp.models;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Editor {
//	@Id
//	private String id;
	
	private @MongoId ObjectId userId;
	private String idea;
	private String summary;
	private LocalDateTime createDate;
	
	public Editor() {}
	
	public Editor(ObjectId userId, String idea, String summary, LocalDateTime createDate) {
		super();
		this.userId=userId;
		this.idea=idea;
		this.summary=summary;
		this.createDate=createDate;
	}

	// getters and setters
	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

//	@Override
//	public String toString() {
//		return String.format("User[id='$s', firstname='$s', lastname='$s']", id, firstName, lastName);
//	}	
}