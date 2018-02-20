package chatbot.common.util;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypto {
	@Test
	public void encrypt() {
		BCryptPasswordEncoder bcr = new BCryptPasswordEncoder();
		String result = bcr.encode("1234");  
		System.out.println("암호: " + result);
	}
}
