package net.jakim.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JSONUtils
{
    private static Logger logger = LoggerFactory.getLogger( JSONUtils.class );

    public static String mapToJson( final Map<String, String> map )
    {
        logger.info( "Inside mapToJson() method" );
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try
        {
            logger.info( "Converting {} to json string",
                         map );
            jsonString = mapper.writeValueAsString( map );
            logger.info( "Randomizing {} json string",
                         jsonString );
            jsonString = Randomizer.randomizeValue( jsonString );
        } catch ( JsonProcessingException e )
        {
            logger.error( "Cannot convert {} to json string",
                          map );
            e.printStackTrace();
        }

        logger.info( "Prepared json string is {}",
                     jsonString );
        logger.info( "Exiting mapToJson() method" );
        return jsonString;
    }

    public static String extractJSONOString( ResponseBodyExtractionOptions body,
                                             String jsonPath )
    {
        return extractJSONObject( body,
                                  jsonPath ).toString();

    }

    public static JSONObject extractJSONObject( ResponseBodyExtractionOptions body,
                                                String jsonPath )
    {
        logger.info( "Inside extractJSONObject() method" );

        logger.info( "Extracting JSON from {}",
                     body );
        Map<String, Object> jsonMAP = body
                .jsonPath()
                .getJsonObject( jsonPath );
        logger.info( "Extracted JSON  {} as map",
                     jsonMAP );

        logger.info( "Creating JSONObject.... " );
        JSONObject actualJSON = new JSONObject( jsonMAP );
        logger.info( "Created JSONObject {}",
                     actualJSON );
        logger.info( "Exiting extractJSONObject() method" );
        return actualJSON;
    }

    public static CustomComparator getJsonComparator( JSONCompareMode comparatorMode )
    {
        return new CustomComparator( comparatorMode,
                                     new Customization( "***",
                                                        // apples regex matcher to app paths
                                                        new RegularExpressionValueMatcher<>() ) );
    }
}
