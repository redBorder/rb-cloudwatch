package rb_cloudwatch;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by alberto on 7/5/15.
 */
public class JSONHandler  {

    ObjectMapper mapper;

    JSONHandler () {

        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //Disable the
        //feature that causes the mapper to break if it enconters an unknown property.



    }

}
