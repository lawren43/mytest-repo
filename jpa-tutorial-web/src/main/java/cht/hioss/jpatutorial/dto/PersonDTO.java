package cht.hioss.jpatutorial.dto;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDTO implements Serializable {

	private Long id;
	private String country;
	private String name;
	private Date modifyDate;
	private int version;

	private Integer departmentId;
	private String departmentName;
	
	private String personDetailTitle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPersonDetailTitle() {
		return personDetailTitle;
	}

	public void setPersonDetailTitle(String personDetailTitle) {
		this.personDetailTitle = personDetailTitle;
	}

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", country=" + country + ", name=" + name + ", modifyDate=" + modifyDate
				+ ", version=" + version + ", departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", personDetailTitle=" + personDetailTitle + "]";
	}

}
