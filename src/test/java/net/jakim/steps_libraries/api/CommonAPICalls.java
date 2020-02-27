package net.jakim.steps_libraries.api;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.*;

/**
 * @author yakimfb
 * @since 19.02.20
 **/
public class CommonAPICalls
{
    private EnvironmentVariables environmentVariables;
    private final static Logger LOG = LoggerFactory.getLogger( CommonAPICalls.class );

    @Step( "prepares a REST client" )
    public void preparesForRESTCalls()
    {
        LOG.info( "Inside preparesForRESTCalls() method" );
        setDefaultRequestSpecification( given().baseUri( serviceBaseURI() )
                                               .accept( ContentType.ANY )
                                               .contentType( ContentType.JSON )
                                               .header( "User-Agent",
                                                        "GAN-Automation" )
                                               .and()
                                               .log()
                                               .all() );
        LOG.info( "Exiting preparesForRESTCalls() method" );
    }

    @Step( "defines the request body \"{0}\"" )
    public void setsBody( final String jsonString )
    {
        LOG.info( "Inside setsBody() method" );
        LOG.info( "Preparing request body with: {}",
                  jsonString );
        setDefaultRequestSpecification( getDefaultRequestSpecification().with()
                                                                        .body( jsonString ) );
        LOG.info( "Exiting setsBody() method" );
    }

    @Step( "performs {0} request to \"{1}\"" )
    public void requests( @NotNull final Method method,
                          final String restEndPointURI )
    {
        LOG.info( "Inside requests() method" );
        LOG.info( "Performing a {} request to '{}' endpoint",
                  method,
                  restEndPointURI );
        getDefaultRequestSpecification().when()
                                        .request( method.name(),
                                                  restEndPointURI );
        LOG.info( "Exiting requests() method" );
    }

    @Step( "performs {0} request to \"{1}\" with path variables {2}" )
    public void requests( @NotNull final Method method,
                          final String restEndPointURI,
                          final Map<String, String> pathVariables )
    {
        LOG.info( "Inside requests() method" );
        LOG.info( "Performing a {} request to '{}' endpoint with {} path variables",
                  method,
                  restEndPointURI,
                  pathVariables );
        getDefaultRequestSpecification().with()
                                        .pathParams( pathVariables )
                                        .when()
                                        .request( method.name(),
                                                  restEndPointURI );
        LOG.info( "Exiting requests() method" );
    }

    public void tearsDownREST()
    {
        LOG.info( "Inside tearsDownREST() method" );
        SerenityRest.reset();
        LOG.info( "Exiting tearsDownREST() method" );
    }

    private String serviceBaseURI()
    {
        LOG.info( "Inside serviceBaseURI() method" );
        String baseURU = EnvironmentSpecificConfiguration
                .from( this.environmentVariables )
                .getProperty( "api.base.uri" );
        LOG.info( "Got '{}' for base uri",
                  baseURU );

        LOG.info( "Exiting serviceBaseURI() method" );
        return baseURU;
    }
}