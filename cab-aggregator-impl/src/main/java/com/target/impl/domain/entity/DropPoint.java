package com.target.impl.domain.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(DropIds.class)
@Table(name = "drop_point")
public class DropPoint extends BaseEntity {

	@Id
	@Column(nullable = false, name = "`from`")
	private String from;

	@Id
	@Column(nullable = false, name = "`to`")
	private String to;

	@Column(nullable = false, name = "distance")
	private long distance;

	public DropPoint() {
	}


	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public long getDistance() {
		return distance;
	}

	public DropPoint(Builder builder) {
		super(builder);
		from = builder.from;
		to = builder.to;
		distance = builder.distance;
	}

	public static final class Builder extends BaseEntity.Builder<Builder> {
		private String from;
		private String to;
		private long distance;

		public Builder() {
		}

		public Builder(DropPoint test) {
			super(test);
			from = test.getFrom();
			to = test.getTo();
			distance = test.getDistance();
		}

		public Builder from(String val) {
			from = val;
			return this;
		}

		public Builder to(String val) {
			to = val;
			return this;
		}

		public Builder distance(long val) {
			distance = val;
			return this;
		}

		public DropPoint build() {
			return new DropPoint(this);
		}
	}
}
