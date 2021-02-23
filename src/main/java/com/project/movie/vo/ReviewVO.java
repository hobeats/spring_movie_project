package com.project.movie.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	private int rno;
	private int mid;
	private String nickName;
	private double star;
	private String review;
	private int liked;
	private Date regdate;
}
