package in.zylaheath.spring.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="phone_directory")
public class PhoneDirectoryEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String phoneNumber;
	
	private Integer status;
	
	
	private Date statusChangedOn;
	
	private String statusChangedRemarks;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	private String updatedBy;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStatusChangedOn() {
		return statusChangedOn;
	}
	public void setStatusChangedOn(Date statusChangedOn) {
		this.statusChangedOn = statusChangedOn;
	}
	public String getStatusChangedRemarks() {
		return statusChangedRemarks;
	}
	public void setStatusChangedRemarks(String statusChangedRemarks) {
		this.statusChangedRemarks = statusChangedRemarks;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
