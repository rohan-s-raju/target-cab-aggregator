
package com.target.service;

import com.target.client.SkeletonClient;
import com.target.client.SkeletonClientException;
import com.target.impl.domain.entity.Test;
import com.target.impl.port.adaptor.persistance.repository.TestRepository;
import com.target.intf.ErrorDTO;
import com.target.intf.TestRequestDTO;
import com.target.intf.TestResponseDTO;
import cucumber.api.DataTable;
import cucumber.api.Format;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TestControllerStepDefs class.
 * @author Rohan Raju
 *
 */
public class TestControllerStepDefs extends StepDefs {

	@Autowired
	SkeletonClient skeletonClient;

	@Autowired
	TestRepository testRepository;

	@Autowired
    Environment environment;

	List<TestRequestDTO> testRequestDTOS = new ArrayList<>();
	List<TestResponseDTO> testResponseDTOS = new ArrayList<>();
	List<List<ErrorDTO>> listsSkeletonErrorDTOs = new ArrayList<>();

	@Before
	@After
	public void clearAll() {
		testResponseDTOS = Collections.EMPTY_LIST;
		testRequestDTOS = Collections.EMPTY_LIST;
		testRepository.deleteAll();

		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
	}


	@Given(value = "the following Add Test Request data:$")
	public void the_following_Add_Test_Request_data(DataTable dataTable) throws Throwable {
		testRequestDTOS = dataTable.asList(TestRequestDTO.class);
		testRequestDTOS.stream().forEach(this::filterStringAndSetItToNullIfBlank);
	}

	private void filterStringAndSetItToNullIfBlank(TestRequestDTO TestRequestDTO) {
		if(StringUtils.isBlank(TestRequestDTO.getTestName())){
			TestRequestDTO.setTestName(null);
		}
		if(StringUtils.isBlank(TestRequestDTO.getTestCode())){
			TestRequestDTO.setTestCode(null);
		}
	}

	@When(value = "call the add Test Client")
	public void call_the_add_Test_Client()  {
		List<TestResponseDTO> list = new ArrayList<>();
		for (TestRequestDTO TestRequestDTO : testRequestDTOS) {
			TestResponseDTO TestResponseDTO = null;
			try {
				TestResponseDTO = skeletonClient.create(TestRequestDTO);
			} catch (SkeletonClientException e) {
				listsSkeletonErrorDTOs.add(e.getSkeletonErrorDTOS());
			}
			list.add(TestResponseDTO);

		}
		testResponseDTOS = list;
	}

	@Then(value = "the all use find all Test client call to check data is present")
	public void the_all_use_find_all_Test_client_call_to_check_data_is_present() throws Throwable {
		List<TestResponseDTO> returnedTestResponseDTO = skeletonClient.getAllTest();
		Assert.assertEquals(testResponseDTOS.size(),returnedTestResponseDTO.size());
	}

	@Then(value = "the added Test request must be present in database")
	public void the_added_Test_request_must_be_present_in_database() throws Throwable {
		List<Test> Tests = testResponseDTOS.stream().map(TestResponseDTO -> {
			return testRepository.findOne(TestResponseDTO.getTestId());
		}).collect(Collectors.toList());
		Assert.assertEquals(testResponseDTOS.size(),Tests.size());
	}

	@Then("^the following errors should match:$")
	public void theFollowingErrorsShouldMatch(DataTable dataTable) throws Throwable {
		List<ErrorDTO> TestManagementErrorDTOSList = dataTable.asList(ErrorDTO.class);
		List<ErrorDTO> errorDtos = listsSkeletonErrorDTOs.stream().flatMap(TestManagementErrorDTOS -> TestManagementErrorDTOS.stream())
				.collect(Collectors.toList());
		Assert.assertTrue(CollectionUtils.isEqualCollection(TestManagementErrorDTOSList,errorDtos));
	}

	@Given("^the following Test in database:$")
	public void theFollowingTestInDatabase(@Format("yyyy-MM-dd'T'HH:mm:ss") DataTable dataTable) throws Throwable {
		List<Test> Tests = dataTable.asList(Test.class);
		Tests.stream().map(this::populateWithDefaultValue).forEach(testRepository::save);
	}

	private Test populateWithDefaultValue(Test Test) {
		Test.Builder builder = new Test.Builder(Test);
		builder.createdBy("TEST");
		builder.updatedBy("TEST");
		builder.updatedDate(new Date());
		builder.createdDate(new Date());
		return builder.build();
	}
}
