package com.onlinegrocery.dto;

import com.onlinegrocery.enums.Status;

public class StatusDto {
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusDto [status=" + status + "]";
	}

	public StatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatusDto(Status status) {
		super();
		this.status = status;
	}
	
}
