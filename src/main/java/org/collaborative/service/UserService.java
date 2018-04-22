package org.collaborative.service;

import org.collaborative.model.BlogUserDetail;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
	public boolean saveUser(BlogUserDetail userDetail);
}
