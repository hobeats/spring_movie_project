package com.project.movie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.movie.vo.LikeVO;
import com.project.movie.vo.ReviewVO;

public interface MovieDAO {
	

	@Select("SELECT * FROM tbl_review WHERE nickName=#{nickName} AND mid=#{mid}")
	ReviewVO isRated(ReviewVO vo) throws Exception;
	
	@Insert("INSERT INTO tbl_review(mid,nickName,star,regdate) VALUES(#{mid},#{nickName},#{star},now())")
	void rating(ReviewVO vo) throws Exception;

	@Update("UPDATE tbl_review SET star = #{star} WHERE nickName=#{nickName} AND mid=#{mid}")
	void updateRating(ReviewVO vo);

	@Insert("INSERT INTO tbl_review(mid,nickName,review) VALUES(#{mid},#{nickName},#{review})")
	void writeReview(ReviewVO vo);

	@Update("UPDATE tbl_review SET review = #{review} WHERE nickName=#{nickName} AND mid=#{mid}")
	void updateReview(ReviewVO vo);
	
	@Select("SELECT * FROM tbl_review WHERE mid=#{mid} AND review is NOT NULL ORDER BY regdate DESC")
	List<ReviewVO> getList(int mid);

	@Insert("INSERT INTO tbl_like(rno,mid,nickName) VALUES(#{rno},#{mid},#{nickName})")
	void like(LikeVO vo);
	
	@Update("UPDATE tbl_review SET liked =  liked+1 WHERE rno = #{rno}")
	void plusLike(int rno);
	
	@Select("SELECT * FROM tbl_like WHERE rno = #{rno} AND mid=#{mid} AND nickName=#{nickName} ")
	LikeVO islike(LikeVO vo);
	
	@Delete("DELETE FROM tbl_like WHERE rno = #{rno} AND mid=#{mid} AND nickName=#{nickName}")
	void dislike(LikeVO vo);

	@Update("UPDATE tbl_review SET liked =  liked - 1 WHERE rno = #{rno}")
	void minusLike(int rno);

	@Select("SELECT * FROM tbl_review WHERE nickName = #{nickName}")
	List<ReviewVO> searchReviewByNick(String nickName);
}
