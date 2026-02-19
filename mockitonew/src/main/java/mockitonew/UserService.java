package mockitonew;

public class UserService {
private UserDao dao;

public UserService(UserDao dao) {
	this.dao = dao;
}
public String typeOfuser(int id) {
	User user = dao.findById(id);
	if(user.getBalance()>0&&user.getBalance()<=1000) {
		return "new user";
	}
	if(user.getBalance()>1000&&user.getBalance()<=2000) {
		return "regular user";
	}
	else {
		return "premium user";
	}
}

}
