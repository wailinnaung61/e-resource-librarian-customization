package services;

import beans.UserBean;

public interface ForgotPasswordService {
	
    public void updateResetPasswordToken(String token, String email)throws Exception;

	public UserBean getByResetPasswordToken(String token);

	public void updatePassword(UserBean users, String password);
    
      
}
