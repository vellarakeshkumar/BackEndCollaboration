package org.collaborative.service;

import org.collaborative.dao.PostDAO;
import org.collaborative.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service

public class PostServiceImpl implements PostService {

	
	@Autowired
	
	private PostDAO postDAO;
	@Override
	public boolean postBlog(Post post) {
		return postDAO.postBlog(post);
	}

}
