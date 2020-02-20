package net.jakim.steps_libraries.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
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
    private Response response;
    EnvironmentVariables environmentVariables;
    private final static Logger LOG = LoggerFactory.getLogger( CommonAPICalls.class );

    @Step( "performs GET {0}" )
    public void get( String resourcePath )
    {
        LOG.info( "Inside get() method" );
        String hostURL = EnvironmentSpecificConfiguration.from( environmentVariables )
                                                         .getProperty( "api.base.url" );
        String getURL = hostURL + resourcePath;
        LOG.info( "Requesting GET: {}",
                  getURL );
        this.response = RestAssured.get( getURL );
        LOG.info( "Got Response {}", response );
        LOG.info( "Exiting get() method" );
    }

    public Response getLastResponse()
    {
        return response;
    }
}
