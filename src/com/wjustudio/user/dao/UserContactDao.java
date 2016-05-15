package com.wjustudio.user.dao;

import java.util.List;

import com.wjustudio.user.domain.UserContact;

public interface UserContactDao {
	/**
	 * 添加备份
	 * @param userContact
	 */
	public int add(UserContact contact);
	
	/**
	 * 获得所有的备份信息
	 * @param userId
	 * @return
	 */
    public List<UserContact> getAllContacts(int userId);
    
    
}
