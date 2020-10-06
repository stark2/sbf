package com.oreilly.sbf;

import com.oreilly.sbf.entities.Greeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SbfApplicationTests {

	//@Autowired private ApplicationContext context;

	@Autowired  @Qualifier("greeting")
	private Greeting myGreeting;

	@Test
	void contextLoads(@Autowired ApplicationContext context) {
		System.out.println("There are " + context.getBeanDefinitionCount() + " beans in the application context.");
		System.out.println("The app context class is " + context.getClass().getName());

		System.out.println("The beans in the application context are:");
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

	@Test
	void findDefaultGreeting(@Autowired ApplicationContext context) {
		Greeting greeting1 = context.getBean("greeting", Greeting.class);
		Assertions.assertNotNull(greeting1.getMessage());
		Greeting greeting2 = context.getBean("hiGreeting", Greeting.class);
		Assertions.assertNotNull(greeting2.getMessage());
	}

}
