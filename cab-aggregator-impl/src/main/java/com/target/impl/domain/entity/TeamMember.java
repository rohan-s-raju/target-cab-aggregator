package com.target.impl.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeamMember extends BaseEntity {

	@Id
	@Column(nullable = false, unique = true, name = "team_Member_ID")
	private long teamMemberID;

	@Column(nullable = false, name = "gender")
	private Gender gender;

	@Column(nullable = false, name = "drop_point")
	private String dropPoint;

	public TeamMember() {
	}

	public TeamMember(Builder builder) {
		super(builder);
		teamMemberID = builder.teamMemberID;
		gender = builder.gender;
		dropPoint = builder.dropPoint;
	}


	public long getTeamMemberID() {
		return teamMemberID;
	}

	public Gender getGender() {
		return gender;
	}

	public String getDropPoint() {
		return dropPoint;
	}

	public static final class Builder extends BaseEntity.Builder<Builder> {
		private long teamMemberID;
		private Gender gender;
		private String dropPoint;

		public Builder() {
		}

		public Builder(TeamMember test) {
			super(test);
			teamMemberID = test.getTeamMemberID();
			gender = test.getGender();
			dropPoint = test.getDropPoint();
		}

		public Builder teamMemberID(long val) {
			teamMemberID = val;
			return this;
		}

		public Builder gender(Gender val) {
			gender = val;
			return this;
		}

		public Builder dropPoint(String val) {
			dropPoint = val;
			return this;
		}

		public TeamMember build() {
			return new TeamMember(this);
		}
	}
}
