package mockito;


public class UserService {
UserDAO dao;
public UserService(UserDAO dao) {
	
	this.dao=dao;
}
public String typeOfUser (int id) {
	User user = dao.findbyId(id);
	if(user.getBalance()>0&&user.getBalance()<=10000) {
		return "new User";
	}
	else if(user.getBalance()>10000&&user.getBalance()<=20000)
	{
		return "regular user";
	}
	else {
		return "Premium User";
	}
}
}
