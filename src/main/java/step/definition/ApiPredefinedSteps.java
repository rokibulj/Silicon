package step.definition;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ApiPredefinedSteps {

	RequestSpecification request = RestAssured.given();

	Response response;
	String requestMethod;

	@Given("^My api base URL is \"([^\"]*)\"$")
	public void my_api_base_URL_is(String baseUrl) throws Throwable {
		request.baseUri(baseUrl);

	}

	@Given("^Port number is \"([^\"]*)\"$")
	public void port_number_is(String port) throws Throwable {
		request.port(Integer.valueOf(port));
	}

	@When("^My request method is \"([^\"]*)\"$")
	public void my_request_method_is(String method) throws Throwable {
		requestMethod = method;

	}

	@When("^I set header \"([^\"]*)\" as \"([^\"]*)\"$")
	public void i_set_header_as(String key, String value) throws Throwable {
		request.header(key, value);

	}

	@When("^I set query parameter \"([^\"]*)\" as \"([^\"]*)\"$")
	public void i_set_query_parameter_as(String param, String value) throws Throwable {
		request.param(param, value);
	}

	@When("^I set path parameter \"([^\"]*)\" as \"([^\"]*)\"$")
	public void i_set_path_parameter_as(String param, String value) throws Throwable {
		request.pathParam(param, value);

	}

	@When("^I set request body from \"([^\"]*)\"$")
	public void i_set_request_body_from(String fileName) throws Throwable {
		JSONParser parser = new JSONParser();

		try {
			FileReader fileContent = new FileReader("./src/main/resources/data/" + fileName);
			Object object = parser.parse(fileContent);
			JSONObject jsonObject = (JSONObject) object;
			request.body(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^I make a request to \"([^\"]*)\"$")
	public void i_make_a_request_to(String endpoint) throws Throwable {

		if (requestMethod.equalsIgnoreCase("GET")) {
			response = request.get(endpoint);
		} else if (requestMethod.equalsIgnoreCase("POST")) {
			response = request.post(endpoint);
		} else if (requestMethod.equalsIgnoreCase("PUT")) {
			response = request.put(endpoint);
		} else if (requestMethod.equalsIgnoreCase("DELETE")) {
			response = request.delete(endpoint);
		}

	}

	@Then("^I expect status code as \"([^\"]*)\"$")
	public void i_expect_status_code_as(String statusCode) throws Throwable {

		int currentStatusCode = Integer.valueOf(statusCode);
		Assert.assertEquals(currentStatusCode, response.statusCode());

	}

	@Then("^I want to print response body$")
	public void i_want_to_print_response_body() throws Throwable {

		try {
			response.prettyPrint();
		} catch (Exception e) {
			response.print();
		}

	}

	@Then("^I expect \"([^\"]*)\" equals \"([^\"]*)\" in response body$")
	public void i_expect_equals_in_response_body(String node, String value) throws Throwable {

		String nodeValue = response.getBody().jsonPath().get(node);

		Assert.assertEquals(value, nodeValue);

	}

	@Then("^I expect \"([^\"]*)\" contains \"([^\"]*)\" in response body$")
	public void i_expect_contains_in_response_body(String node, String value) throws Throwable {

		String nodeValue = response.getBody().jsonPath().get(node);

		boolean condition = nodeValue.contains(value);
		Assert.assertTrue(condition);

	}

}
