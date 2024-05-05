package dev.cases.CaseJavaCompany;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.cases.company.CaseJavaCompanyApplication;

/**
 * @Created 05/05/2024
 * @project CaseJavaCompanyApplication
 * @Author K.ABIDA
 */

@SpringBootTest(classes = CaseJavaCompanyApplication.class)
class CaseJavaCompanyApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true);
	}

}
