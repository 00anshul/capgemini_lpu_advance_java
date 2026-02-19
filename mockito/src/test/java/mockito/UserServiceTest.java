package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class UserServiceTest {
	
//	@Test
	public void testGetTypeofCustomer() {
		UserDAO userdao = new UserDAO();
		UserService  userservice = new UserService(userdao);
		String actualres = userservice.typeOfUser(1);
	    assertEquals("new User",actualres);	

	}
	@Test
	public void testDobleAddition() {
		//create fake mock object
		Calculator calmock = mock(Calculator.class);
		//return from mock object
		when(calmock.add(20, 2)).thenReturn(202);
		//give fake object reference
		MathService service = new MathService(calmock);
		int res=service.doubleAddition(20, 2);
		
		assertEquals(202,res);
		
	}
}
