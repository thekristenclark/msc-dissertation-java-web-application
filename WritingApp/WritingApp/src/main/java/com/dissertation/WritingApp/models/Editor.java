package  com.dissertation.WritingApp.models;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Editor {
	
	private @MongoId ObjectId userId;
	private String charNotes;
	private String story;
	private LocalDateTime createDate;
	private String diagramData; // stores the gojs json data
	private String storyTitle;
	private String storyId;
	

	public Editor() {}
	
	public Editor(ObjectId userId, String charNotes, String story, LocalDateTime createDate, String diagramData, String storyTitle, String storyId) {
		super();
		this.userId=userId;
		this.charNotes=charNotes;
		this.story=story;
		this.createDate=createDate;
		this.diagramData=diagramData;
		this.storyTitle = storyTitle;
		this.storyId = storyId;
	}

	// getters and setters
	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public String getCharNotes() {
		return charNotes;
	}

	public void setCharNotes(String charNotes) {
		this.charNotes = charNotes;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
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
    
	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}


	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

//	@Override
//	public String toString() {
//        return String.format("Editor[id=%s, userId=%s, idea=%s, summary=%s, createDate=%s, plotMapData=%s]", 
//                userId, userId, idea, summary, createDate, plotMapData);	}	
}