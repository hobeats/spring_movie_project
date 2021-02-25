package com.project.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface TaskDAO {
	
	@Select("SELECT profile_path FROM movie_user")
	List<String> getProfilePath() throws Exception;
}
