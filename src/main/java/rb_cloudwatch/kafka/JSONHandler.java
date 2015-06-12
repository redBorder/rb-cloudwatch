package rb_cloudwatch.kafka;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.type.TypeReference;
import rb_cloudwatch.model.Metric;
import rb_cloudwatch.model.MetricImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alberto on 7/5/15.
 */
class JSONHandler  {

    private static final Logger logger = Logger.getLogger(JSONHandler.class.getName());

    private Metric metric;
    private Map<String, String> map;
    private org.codehaus.jackson.map.ObjectMapper jacksonMapper;

    public JSONHandler() {
        map = new HashMap<String, String>(); //Construct map
        jacksonMapper = new org.codehaus.jackson.map.ObjectMapper();
    }

    public Metric processJSON(String json) throws Exception {

        try {
            map = jacksonMapper.readValue(json, new TypeReference<HashMap<String, String>>() {
            });
        } catch (JsonParseException e) {
            logger.log(Level.SEVERE, "Error reading JSON message", e);
            throw new Exception ("JSON error");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "No input to Jackson parser?", e);
            throw new Exception ("JSON input error");
        }
        metric = new MetricImpl(map.get("timestamp"), map.get("sensor_name"),
                map.get("monitor"), map.get("value"), map.get("type"),
                map.get("unit"));
        return metric;
    }
}
