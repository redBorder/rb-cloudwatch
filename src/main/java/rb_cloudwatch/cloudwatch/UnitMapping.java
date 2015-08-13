package rb_cloudwatch.cloudwatch;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by alberto on 16/6/15.
 */
public class UnitMapping{

    private Map<String, String> map;
    private static final Logger logger = Logger.getLogger(CloudwatchConnectorImpl.class.getName());

    UnitMapping() {
        map = new HashMap<String, String>();
        map.put("%", "Percent");
        map.put("ms", "Milliseconds");
        map.put("", "None");
        map.put("msgs", "Count");
        map.put("tasks", "Count");
        map.put("desired_capacity", "None");
    }

    public String getMappedUnit(String unit) {
        String awsUnit = map.get(unit);
        if(awsUnit == null) {
            logger.warning("Unit " + unit + "not found in map, using AWS unit None");
            awsUnit = "None";
        }
        return awsUnit;
    }
}
