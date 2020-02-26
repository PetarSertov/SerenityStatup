package net.jakim.steps_libraries.api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yakimfb
 * @since 19.02.20
 **/
public class CommonAPICalls
{
    private EnvironmentVariables environmentVariables;
    private RequestSpecification requestSpecification;


    private final static Logger LOG = LoggerFactory.getLogger( CommonAPICalls.class );

    @Step( "prepares a REST client" )
    public void preparesForRESTCalls()
    {
        requestSpecification = SerenityRest
                .given()
                .baseUri( serviceBaseURI() )
                .accept( ContentType.ANY )
                .contentType( ContentType.JSON )
                .header( "User-Agent",
                         "GAN-Automation" );
    }

    @Step( "defines the request body \"{0}\"" )
    public void setsBody( final String jsonString )
    {
        requestSpecification
                .with()
                .body( jsonString )
                .log()
                .all();
    }

    @Step( "performs POST request to \"{0}\"" )
    public void postsTo( final String restEndPointURI )
    {
        requestSpecification
                .when()
                .post( restEndPointURI )
                .andReturn();
    }

    private String serviceBaseURI()
    {
        return EnvironmentSpecificConfiguration
                .from( this.environmentVariables )
                .getProperty( "api.base.uri" );
    }
}
