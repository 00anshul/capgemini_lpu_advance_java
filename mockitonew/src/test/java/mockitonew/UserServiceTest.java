package mockitonew;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	UserDao  daomock;
	
	@InjectMocks;
	UserService service;
	
	@Test
	public void typeOfuser() {
	
	//creating fake obj
	User fakeobj = new User();
	fakeobj.setId(1);
	fakeobj.setBalance(2000);
	fakeobj.setName("Allen");
	
	//returning fake obj
	when(daomock.findById(1)).thenReturn(fakeobj);
	
	User user1 = new User();
	fakeobj.setId(2);
	fakeobj.setBalance(1000);
	fakeobj.setName("Miller");
	when(daomock.findById(2)).thenReturn(user1);
	
	UserService service = new UserService(daomock);
	String res = service.typeOfuser(1);
	assertEquals("regular user",res);
	}
}
