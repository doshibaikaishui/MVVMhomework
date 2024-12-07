package com.res.back;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackApplicationTests {

	@Test
	void contextLoads() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("123456"));
		System.out.println(passwordEncoder.encode("654321"));
		System.out.println(passwordEncoder.encode("987654"));
	}

}
