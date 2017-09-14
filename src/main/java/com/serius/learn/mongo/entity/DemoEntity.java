package com.serius.learn.mongo.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DemoEntity")
@CompoundIndexes({ 
@CompoundIndex(name = "demoId_demoName", def = "{demoId : 1, demoName : 1}", unique=true) 
})
public class DemoEntity {
	
	@Id
	private ObjectId objectId;
	
	private String demoId;
	
	private String demoName;
	
	@Indexed(direction=IndexDirection.DESCENDING)
	private Date insertTime;
	
	public String getDemoId() {
		return demoId;
	}
	public void setDemoId(String demoId) {
		this.demoId = demoId;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	public ObjectId getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}
