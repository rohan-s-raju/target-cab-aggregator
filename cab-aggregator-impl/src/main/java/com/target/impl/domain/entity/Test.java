package com.target.impl.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true, name = "test_id")
	private long testId;

	@Column(nullable = false, name = "test_code")
	private String testCode;

	@Column(nullable = false, name = "test_name")
	private String testName;

	public Test() {
	}

	public Test(Builder builder) {
		super(builder);
		testId = builder.testId;
		testCode = builder.testCode;
		testName = builder.testName;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}


	public static final class Builder extends BaseEntity.Builder<Builder> {
		private long testId;
		private String testCode;
		private String testName;

		public Builder() {
		}

		public Builder(Test test) {
			super(test);
			testId = test.getTestId();
			testCode = test.getTestCode();
			testName = test.getTestName();
		}

		public Builder testId(long val) {
			testId = val;
			return this;
		}

		public Builder testCode(String val) {
			testCode = val;
			return this;
		}

		public Builder testName(String val) {
			testName = val;
			return this;
		}

		public Test build() {
			return new Test(this);
		}
	}
}
