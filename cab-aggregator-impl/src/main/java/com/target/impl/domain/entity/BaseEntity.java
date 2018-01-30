package com.target.impl.domain.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

	@Column(nullable = false, name = "created_by")
	private String createdBy;

	@Column(nullable = false, name = "updated_by")
	private String updatedBy;

	@Column(nullable = false, name = "created_date")
	private Date createdDate;

	@Column(nullable = false, name = "updated_date")
	private Date updatedDate;

	@Column(nullable = false, name = "version")
	@Version
	private Integer version;

	public BaseEntity() { // Hibernate/JPA Needs this
	}

	public BaseEntity(Builder builder) {
		createdBy = builder.createdBy;
		updatedBy = builder.updatedBy;
		createdDate = builder.createdDate;
		updatedDate = builder.updatedDate;
		version = builder.version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

		public static class Builder<T extends Builder> {
		private String createdBy;
		private String updatedBy;
		private Date createdDate;
		private Date updatedDate;
		private Integer version;

		public Builder() {
		}

		public Builder(BaseEntity baseEntity) {
			createdBy = baseEntity.getCreatedBy();
			updatedBy = baseEntity.getUpdatedBy();
			createdDate = baseEntity.getCreatedDate();
			updatedDate = baseEntity.getUpdatedDate();
			version = baseEntity.getVersion();
		}

		public T createdBy(String val) {
			createdBy = val;
			return (T) this;
		}

		public T updatedBy(String val) {
			updatedBy = val;
			return (T) this;
		}

		public T createdDate(Date val) {
			createdDate = val;
			return (T) this;
		}

		public T updatedDate(Date val) {
			updatedDate = val;
			return (T) this;
		}

		public T version(Integer val) {
			version = val;
			return (T) this;
		}

		public BaseEntity build() {
			return new BaseEntity(this);
		}
	}
}
