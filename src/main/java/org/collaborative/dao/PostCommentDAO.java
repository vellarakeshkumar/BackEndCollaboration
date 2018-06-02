package org.collaborative.dao;

import java.util.List;

import org.collaborative.model.Comment;

public interface PostCommentDAO {
	

	public boolean addPostComment(Comment comment);
	
	public boolean updatePostComment(Comment comment);
	
	public boolean deletePostComment(Comment comment);
	
	public Comment getPostComment(int blogCommentId);
	
	public List<Comment> getAllPostComments(int postid);

}