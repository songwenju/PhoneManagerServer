package com.wjustudio.user.dao.impls;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wjustudio.common.ProjectConstant;
import com.wjustudio.db.BaseDB;
import com.wjustudio.user.dao.UserContactDao;
import com.wjustudio.user.domain.UserContact;

public class UserContactImpls extends BaseDB implements UserContactDao{

	public static final String USER_CONTACT_TABLE = ProjectConstant.USER_CONTACT_TABLE;


	@Override
	public int add(UserContact contact) {

		String sql = "insert into "+USER_CONTACT_TABLE+"(userId,contactJsonUrl)"
				+"values('"
				+contact.getUserId()+"','"
				+contact.getContactJsonUrl()+"')";
		System.out.println(sql);
		return super.executeUpdate(sql);
	}

	@Override
	public List<UserContact> getAllContacts(int userId) {
		List<UserContact>userContacts = new ArrayList<UserContact>();
		String sql = "select * from "+USER_CONTACT_TABLE+" where userId = ?";
		super.res = super.executeQuery(sql, userId);
		
		try {
			while (super.res.next()) {
				String contactJsonUrl = super.res.getString("contactJsonUrl");
				userContacts.add(new UserContact( userId, contactJsonUrl));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("userContact.size"+userContacts.size());
		return userContacts;
	}

}
