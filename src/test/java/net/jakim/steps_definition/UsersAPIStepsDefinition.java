package net.jakim.steps_definition;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.jakim.utils.services.EmployeesServices;
import net.jakim.utils.entities.Employ;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.jakim.steps_libraries.api.CommonAPICalls;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author yakimfb
 * @since 19.02.20
 **/
public class UsersAPIStepsDefinition
{
    private final static Logger LOG = LoggerFactory.getLogger( UsersAPIStepsDefinition.class );
    @Steps
    CommonAPICalls api;

    @When( "{} does GET request to {string}" )
    public void performGetRequest( String userName,
                                   String resourcePath )
    {
        LOG.info( "Inside performGetRequest() method" );
        api.get( resourcePath );
        LOG.info( "Exiting performGetRequest() method" );
    }

    @Then( "request is successful with code {int}" )
    public void requestIsSuccessfulWithCode( int statusCode )
    {
        assertThat( api.getLastResponse()
                       .statusCode(),
                    is( equalTo( statusCode ) ) );
        ///restAssuredThat( lastResponse -> lastResponse.body( equalTo( statusCode ) ) );
    }

    @Then( "all existing users are returned" )
    public void allExistingUsersAreReturned()
    {
        List<Employ> actualEmployees = EmployeesServices.extractEmployeesFrom( api.getLastResponse()
                                                                                  .getBody()
                                                                                  .jsonPath()
                                                                                  .setRoot( "data" ) );


        Employ expectedUser = new Employ();
        expectedUser.setId( 5 );
        expectedUser.setName( "Airi Satou" );
        expectedUser.setSalary( "162700" );
        expectedUser.setAge( 33 );
        assertThat( actualEmployees,
                    is( not( empty() ) ) );

        assertThat( actualEmployees,
                    hasItem( expectedUser ) );
    }
}
