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
	private String diagramData; // stores the gojs json data
	
	public Editor() {}
	
	public Editor(ObjectId userId, String idea, String summary, LocalDateTime createDate, String diagramData) {
		super();
		this.userId=userId;
		this.idea=idea;
		this.summary=summary;
		this.createDate=createDate;
		this.diagramData=diagramData;
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
	
    public String getDiagramData() {
        return diagramData;
    }

    public void setDiagramData(String diagramData) {
        this.diagramData = diagramData;
    }

//	@Override
//	public String toString() {
//        return String.format("Editor[id=%s, userId=%s, idea=%s, summary=%s, createDate=%s, plotMapData=%s]", 
//                userId, userId, idea, summary, createDate, plotMapData);	}	
}