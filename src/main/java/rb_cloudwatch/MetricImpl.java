package rb_cloudwatch;

import java.util.Date;
/**
 * Created by alberto on 11/5/15.
 */
public class MetricImpl implements Metric {

    /* Attributes */
    private Date timestamp;
    private String sensor_name;
    private String monitor;
    private Double value;
    private String type;

    /* Constructors */
    public MetricImpl(Date timestamp, String sensor_name, String monitor, Double value, String type) {
        this.timestamp = timestamp;
        this.sensor_name = sensor_name;
        this.monitor = monitor;
        this.value = value;
        this.type = type;
    }

    /* Methods */
    public Date getTimestamp() {
        return timestamp;
    }
    public String getSensor_name() {
        return sensor_name;
    }
    public String getMonitor() {
        return monitor;
    }
    public Double getValue() {
        return value;
    }
    public String getType() {
        return type;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }
    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public void setType(String type) {
        this.type = type;
    }

}
