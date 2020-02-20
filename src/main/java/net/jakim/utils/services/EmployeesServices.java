package net.jakim.utils.services;

import io.restassured.path.json.JsonPath;
import net.jakim.utils.entities.Employ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author yakimfb
 * @since 20.02.20
 **/
public class EmployeesServices
{
    private final static Logger LOG = LoggerFactory.getLogger( EmployeesServices.class );

    public static List<Employ> extractEmployeesFrom( JsonPath jsonEmployees )
    {
        LOG.info( "Inside extractEmployeesFrom() method" );
        LOG.info( "Converting {} json to list of employees",
                  jsonEmployees.prettify() );
        List<Employ> employs = jsonEmployees.getList( "",
                                                      Employ.class );
        LOG.info( "Converted list with employees",
                  employs );
        LOG.info( "Exiting extractEmployeesFrom() method" );
        return employs;
    }


}
