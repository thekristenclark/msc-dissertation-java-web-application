package  com.dissertation.WritingApp.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Editor {
	
	private ObjectId userId;
	private String charNotes;
	private String story;
	private LocalDateTime createDate;
	private String diagramData; // stores the gojs json data
	private String storyTitle;
	private @MongoId String storyId;
	private String charMapData;
	

	public Editor() {}
	
	public Editor(ObjectId userId, String charNotes, String story, LocalDateTime createDate, String diagramData, String storyTitle, String storyId, String charMapData) {
		super();
		this.userId=userId;
		this.charNotes=charNotes;
		this.story=story;
		this.createDate=createDate;
		this.diagramData=diagramData;
		this.storyTitle = storyTitle;
		this.storyId = storyId;
		this.charMapData = charMapData;
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
	
	public String getCharMapData() {
		return charMapData;
	}

	public void setCharMapData(String charMapData) {
		this.charMapData = charMapData;
	}
}