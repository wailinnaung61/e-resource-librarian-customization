package services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import beans.UserBean;
import dao.UserDao;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService{
	
	@Autowired
	UserDao userDao;
	
	public void updateResetPasswordToken(String token, String email) throws Exception {
		UserBean userbean=new UserBean();
        int isHave = userDao.findByEmail(email);
        if (isHave > 0) {
            userbean.setResetPasswordToken(token);
            userDao.saveToken(token,email);
        } else {
            throw new Exception("Could not find any customer with the email " + email);
        }
    }

	@Override
	public UserBean getByResetPasswordToken(String token) {
		return userDao.getByResetPasswordToken(token);
	}

	@Override
	public void updatePassword(UserBean users, String password) {
		 userDao.updatepassword(users, password);		
	}

}
