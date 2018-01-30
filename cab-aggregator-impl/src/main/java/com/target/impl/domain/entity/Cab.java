package com.target.impl.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cab extends BaseEntity {

	@Id
	@Column(nullable = false, unique = true, name = "cab_id")
	private long cabId;

	@Column(nullable = false, name = "cost")
	private long cost;

	@Column(nullable = false, name = "capacity")
	private long capacity;

	public Cab() {
	}

	public Cab(Builder builder) {
		super(builder);
		cabId = builder.cabId;
		cost = builder.cost;
		capacity = builder.capacity;
	}

	public long getCabId() {
		return cabId;
	}

	public long getCost() {
		return cost;
	}

	public long getCapacity() {
		return capacity;
	}

	public static final class Builder extends BaseEntity.Builder<Builder> {
		private long cabId;

		private long cost;

		private long capacity;

		public Builder() {
		}

		public Builder(Cab test) {
			super(test);
			cabId = test.getCabId();
			cost = test.getCost();
			capacity = test.getCapacity();
		}

		public Builder cabId(long val) {
			cabId = val;
			return this;
		}

		public Builder cost(long val) {
			cost = val;
			return this;
		}

		public Builder capacity(long val) {
			capacity = val;
			return this;
		}

		public Cab build() {
			return new Cab(this);
		}
	}
}
