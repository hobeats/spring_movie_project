package com.project.movie.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.movie.dao.MovieDAO;
import com.project.movie.vo.LikeVO;
import com.project.movie.vo.ReviewVO;

@Service
public class MovieServiceImpl implements MovieService{

	@Inject
	MovieDAO dao;
	
	@Override
	public void rating(ReviewVO vo) throws Exception {
		ReviewVO rVO = dao.isRated(vo);
		if(rVO == null) {
			dao.rating(vo);
		}else {
			dao.updateRating(vo);
		}
	}

	@Override
	public ReviewVO isRated(ReviewVO vo) throws Exception {
		return dao.isRated(vo);
	}

	@Override
	public void writeReview(ReviewVO vo) throws Exception {
		ReviewVO rVO = dao.isRated(vo);
		if(rVO == null) {
			dao.writeReview(vo);
		}else {
			dao.updateReview(vo);
		}
	}

	@Override
	public List<ReviewVO> getList(int mid) throws Exception {
		return dao.getList(mid);
	}

	@Override
	@Transactional
	public void like(LikeVO vo) throws Exception{
		dao.like(vo);
		dao.plusLike(vo.getRno());
	}

	@Override
	public LikeVO isliked(LikeVO vo) throws Exception{
		return dao.islike(vo);
	}

	@Override
	@Transactional
	public void dislike(LikeVO vo) throws Exception{
		dao.dislike(vo);
		dao.minusLike(vo.getRno());
	}

	@Override
	public List<ReviewVO> searchReviewByNick(String nickName) throws Exception {
		return dao.searchReviewByNick(nickName);
	}

	
}