package net.jakim.steps_definition;

import io.cucumber.java.Before;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.jakim.steps_libraries.api.CommonAPICalls;
import net.thucydides.core.annotations.Steps;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.jakim.utils.JSONUtils.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

/**
 * @author yakimfb
 * @since 19.02.20
 **/
public class UsersAPIStepsDefinition
{
    private final static Logger LOG = LoggerFactory.getLogger( UsersAPIStepsDefinition.class );

    @Steps
    CommonAPICalls restClient;

    @Given( "the following body is prepared:" )
    public void theFollowingBody( @Transpose Map<String, String> requestBody )
    {
        String jsonString = mapToJson( requestBody );
        restClient.setsBody( jsonString );
    }

    @When( "the api client does a POST to {string}" )
    public void playerCompletesTheChangePasswordForm( String restEndPoint )
    {
        restClient.postsTo( restEndPoint );
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

    @Before
    public void initRequestSpec()
    {
        restClient.preparesForRESTCalls();
    }
}
