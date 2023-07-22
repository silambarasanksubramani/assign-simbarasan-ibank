package com.app.ibank;

import com.app.ibank.entity.Accounts;
import com.app.ibank.entity.Beneficiary;
import com.app.ibank.repository.AccountsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class IbankApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testAccountUpdate() throws Exception{
		long accountId = 123456;
		Accounts accounts = new Accounts();
		accounts.setAccountId(123456);
		accounts.setAccountName("David David");
		accounts.setEmail("david@gmail.com");
		accounts.setPhone("2424242424");
		accounts.setStatus("active");

		ResultActions perform = mockMvc.perform(put("http://localhost:8080/api/ibank/v1/accounts/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(accounts)));

		perform.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testBeneficiaryAdding() throws Exception{

		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryAccountId(343434343);
		beneficiary.setBeneficiaryName("Test John");
		beneficiary.setBeneficiaryIFSCCode("IFS1234567");
		beneficiary.setStatus("active");

		ResultActions perform = mockMvc.perform(post("http://localhost:8080/api/ibank/v1/beneficiary/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(beneficiary)));

		perform.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void testWithdraw() throws Exception{
		ResultActions perform = mockMvc.perform(put("http://localhost:8080/api/ibank/v1/withdraw")
				.contentType(MediaType.APPLICATION_JSON)
				.param("accountId", "123456")
				.param("amount", "3000")
		);

		perform.andExpect(status().isOk()).andDo(print());
	}

}
