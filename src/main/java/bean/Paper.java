   /**
    * Class Paper reflects paper management UI
    * class template
    */

package bean;

import javax.faces.bean.ManagedBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@ManagedBean(name="paper")
public class Paper {
	
	@SerializedName("lecturerId")
	@Expose
	private String lecturerId;
	@SerializedName("updatedAt")
	@Expose
	private String updatedAt;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("year")
	@Expose
	private String year;
	@SerializedName("createdAt")
	@Expose
	private String createdAt;
	@SerializedName("stream")
	@Expose
	private String stream;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("room")
	@Expose
	private String room;

	/**
	* No args constructor for use in serialization
	* 
	*/
	public Paper() {
	}

	/**
	* 
	* @param id
	* @param updatedAt
	* @param stream
	* @param status
	* @param createdAt
	* @param name
	* @param lecturerId
	* @param year
	* @param room
	*/
	public Paper(String lecturerId, String updatedAt, String status, String year, String createdAt, String stream, String id, String name, String room) {
	super();
	this.lecturerId = obtainLectureName();
	this.updatedAt = updatedAt;
	this.status = status;
	this.year = year;
	this.createdAt = createdAt;
	this.stream = stream;
	this.id = id;
	this.name = name;
	this.room = room;
	}

	public String getLecturerId() {
	return lecturerId;
	}

	public void setLecturerId(String lecturerId) {
	this.lecturerId = lecturerId;
	}

	public Paper withLecturerId(String lecturerId) {
	this.lecturerId = lecturerId;
	return this;
	}

	public String getUpdatedAt() {
	return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
	this.updatedAt = updatedAt;
	}

	public Paper withUpdatedAt(String updatedAt) {
	this.updatedAt = updatedAt;
	return this;
	}

	public String getStatus() {
	return status;
	}

	public void setStatus(String status) {
	this.status = status;
	}

	public Paper withStatus(String status) {
	this.status = status;
	return this;
	}

	public String getYear() {
	return year;
	}

	public void setYear(String year) {
	this.year = year;
	}

	public Paper withYear(String year) {
	this.year = year;
	return this;
	}

	public String getCreatedAt() {
	return createdAt;
	}

	public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
	}

	public Paper withCreatedAt(String createdAt) {
	this.createdAt = createdAt;
	return this;
	}

	public String getStream() {
	return stream;
	}

	public void setStream(String stream) {
	this.stream = stream;
	}

	public Paper withStream(String stream) {
	this.stream = stream;
	return this;
	}

	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public Paper withId(String id) {
	this.id = id;
	return this;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public Paper withName(String name) {
	this.name = name;
	return this;
	}

	public String getRoom() {
	return room;
	}

	public void setRoom(String room) {
	this.room = room;
	}

	public Paper withRoom(String room) {
	this.room = room;
	return this;
	}
	
	public String obtainLectureName() {

		return "Lecturers Name String";
	}
}
