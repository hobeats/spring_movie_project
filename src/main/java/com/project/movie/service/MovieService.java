package com.project.movie.service;

import java.util.List;

import com.project.movie.vo.LikeVO;
import com.project.movie.vo.ReviewVO;

public interface MovieService {
	//별점 남기기
	void rating(ReviewVO vo) throws Exception;
	//별점 남겼나 확인
	ReviewVO isRated(ReviewVO vo) throws Exception;
	//아이디로 리뷰 전체 확인하기
	List<ReviewVO> searchReviewByNick(String nickName) throws Exception;
	//리뷰 쓰기
	void writeReview(ReviewVO vo) throws Exception;
	//최신순 리뷰 받아오기
	List<ReviewVO> getList(int mid) throws Exception;
	//좋아요
	void like(LikeVO vo) throws Exception;
	//좋아요 취소
	void dislike(LikeVO vo) throws Exception;
	//좋아요 확인
	LikeVO isliked(LikeVO vo) throws Exception;
	
	
}
