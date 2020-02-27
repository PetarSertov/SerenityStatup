package net.jakim.steps_definition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import net.jakim.steps_libraries.api.CommonAPICalls;
import net.thucydides.core.annotations.Steps;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Map;

import static net.jakim.utils.JSONUtils.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsNot.not;

/**
 * @author yakimfb
 * @since 19.02.20
 **/
public class UsersAPIStepsDefinition
{
    @Steps
    CommonAPICalls restClient;

    @Given( "the following body is prepared:" )
    public void theFollowingBody( @Transpose Map<String, String> requestBody )
    {
        String jsonString = mapToJson( requestBody );
        restClient.setsBody( jsonString );
    }

    @When( "the api client does a {} request to {string}" )
    public void performJSONRequest( Method method,
                                    String restEndPoint )
    {
        restClient.requests( method,
                             restEndPoint );
    }

    @When( "the api client does a {} request to {string}:" )
    public void performJSONRequest( Method method,
                                    String restEndPoint,
                                    @Transpose Map<String, String> pathVariables )
    {
        restClient.requests( method,
                             restEndPoint,
                             pathVariables );
    }

    @Then( "a response with status code {int} is returned" )
    public void responseIsReturnedWithStatus( int code )
    {
        restAssuredThat( response -> response.statusCode( code ) );
    }

    @Then( "the response payload on {string} contains:" )
    public void jsonResponseValidator( String jsonPath,
                                       @Transpose Map<String, String> expectedPayload )
            throws
            JSONException
    {
        String expectedJSON = mapToJson( expectedPayload );

        JSONAssert.assertEquals( expectedJSON,
                                 extractJSONOString( lastResponse().getBody(),
                                                     jsonPath ),
                                 getJsonComparator( JSONCompareMode.LENIENT ) );
    }

    @Then( "the response payload on {string} is an array" )
    public void theResponsePayloadOnIsAnArray( String path )
    {
        restAssuredThat( response -> response.body( path,
                                                    not( empty() ) ) );
    }

    @Before
    public void initRequestSpec()
    {
        restClient.preparesForRESTCalls();
    }

    @After
    public void resetRequestSpec()
    {
        restClient.tearsDownREST();
    }


}
