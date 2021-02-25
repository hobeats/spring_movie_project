package com.project.movie.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movie.service.MovieService;
import com.project.movie.vo.LikeVO;
import com.project.movie.vo.ReviewVO;

@RestController
public class DetailController {
	
	@Inject
	MovieService ms;
	
	@PostMapping("/rating")
	public ResponseEntity<String> rating(ReviewVO vo) throws Exception{
		ResponseEntity<String> entity = null;
		System.out.println(vo);
		System.out.println("Rating : " + vo.getStar() );
		System.out.println("nickName : " +  vo.getNickName());
		if(vo.getNickName() != null || !vo.getNickName().equals("")) {
			ms.rating(vo);
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		}else {
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PostMapping("/writeReview")
	public ResponseEntity<String> writeReview(ReviewVO vo) throws Exception{
		ResponseEntity<String> entity = null;
		System.out.println(vo);
		if(!vo.getNickName().equals("")) {
			ms.writeReview(vo);
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		}else {
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping("/list/{mid}")
	public ResponseEntity<List<ReviewVO>> getList(@PathVariable("mid") int mid) throws Exception{
		ResponseEntity<List<ReviewVO>> entity = null;
		List<ReviewVO> list = ms.getList(mid);
		System.out.println(list);
		entity = new ResponseEntity<>(list,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/like")
	public ResponseEntity<String> like(LikeVO vo) throws Exception{
		ResponseEntity<String> entity = null;
		ms.like(vo);
		return entity;
	}
	
	@PostMapping("/dislike")
	public ResponseEntity<String> dislike(LikeVO vo) throws Exception{
		ResponseEntity<String> entity = null;
		ms.dislike(vo);
		return entity;
	}
	
	@PostMapping("/isliked")
	public ResponseEntity<LikeVO> isliked(LikeVO vo) throws Exception{
		ResponseEntity<LikeVO> entity = null;
		LikeVO likeVO = ms.isliked(vo);
		System.out.println("좋아요 확인");
		entity = new ResponseEntity<>(likeVO,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/getList")
	public ResponseEntity<List<ReviewVO>> getList(String nickName) throws Exception{
		ResponseEntity<List<ReviewVO>> entity = null;
		List<ReviewVO> list = ms.searchReviewByNick(nickName);
		entity = new ResponseEntity<>(list,HttpStatus.OK);
		return entity;
	}
}
