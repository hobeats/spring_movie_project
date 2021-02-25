package com.project.common.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.common.dao.TaskDAO;

@Component
public class FileCheckTest {
	
	@Inject
	TaskDAO dao;
	
	@Inject
	ServletContext context;
	
	@Resource(name="uploadFolder")
	String uploadPath;
	
	List<String> trashPath = new ArrayList<>();
	@Scheduled(cron="* * 4 * * *")
	public void profileCheck() throws Exception{
		System.out.println("SCHEDULED METHOD");
		uploadPath = context.getRealPath(uploadPath);
		List<String> currentPath = dao.getProfilePath();
		File file = new File(uploadPath);
		String[] savedPath = file.list();
		System.out.println("savedPath : " + savedPath);
		if(savedPath != null) {
			for(int i =0; i<currentPath.size(); i++)  {
				String cPath = currentPath.get(i);
				for(int j=0; j<savedPath.length; j++) {
					if(!cPath.equals("/movie/upload/"+savedPath[j])) {
						System.out.println(cPath + " : "+ savedPath[j]);
						trashPath.add(savedPath[j]);
					}
				}
			}
		}
		if(!trashPath.isEmpty()) {
			for(String path : trashPath) {
				path = uploadPath+'/'+path;
				System.out.println(path+"\n");
				new File(path.replace('/', File.separatorChar)).delete();
			}
			
		}
	}
}
