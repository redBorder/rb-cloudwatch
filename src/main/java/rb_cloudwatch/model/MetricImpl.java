package rb_cloudwatch.model;


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
    private String unit;

    /* Constructors */
    public MetricImpl(String timestamp, String sensor_name, String monitor, String value, String type, String unit) {

        this.timestamp = new Date(Long.parseLong(timestamp)*1000);
        this.sensor_name = sensor_name;
        this.monitor = monitor;
        this.value = Double.parseDouble(value);
        this.type = type;
        this.unit = unit;
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
    public String getUnit() {
        return unit;
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
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "MetricImpl{" +
                "\ntimestamp=" + timestamp +
                ", \nsensor_name='" + sensor_name + '\'' +
                ", \nmonitor='" + monitor + '\'' +
                ", \nvalue=" + value +
                ", \ntype='" + type + '\'' +
                ", \nunit='" + unit + '\'' +
                "\n}";
    }
}
