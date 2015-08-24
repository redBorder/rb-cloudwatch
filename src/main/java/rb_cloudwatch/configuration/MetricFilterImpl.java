package rb_cloudwatch.configuration;

import rb_cloudwatch.model.Metric;

import java.util.*;

/**
 * Created by alberto on 21/8/15.
 */
public class MetricFilterImpl implements MetricFilter {

    private List<String> allowedMetricNames;
    private List<String> allowedInstanceIds;
    private Set<String> allowedMetricNamesSet;
    private Set<String> allowedInstaceIdsSet;

    /*Constructors*/
    public MetricFilterImpl() {
        this.allowedInstanceIds = null;
        this.allowedMetricNames = null;
        this.allowedInstaceIdsSet = null;
        this.allowedMetricNamesSet = null;
    }
    public MetricFilterImpl(List<String> allowedInstanceIds, List<String> allowedMetricNames) {
        this.allowedInstanceIds = allowedInstanceIds;
        this.allowedMetricNames = allowedMetricNames;
        this.allowedInstaceIdsSet = null;
        this.allowedMetricNamesSet = null;
    }

    public List<String> getAllowedInstanceIds() {
        return allowedInstanceIds;
    }
    public void setAllowedInstanceIds(List<String> allowedInstanceIds) {
        this.allowedInstanceIds = allowedInstanceIds;
    }
    public List<String> getAllowedMetricNames() {
        return allowedMetricNames;
    }
    public void setAllowedMetricNames(List<String> allowedMetricNames) {
        this.allowedMetricNames = allowedMetricNames;
    }

    @Override
    public String toString() {
        return "MetricFilterImpl{" +
                "allowedMetricNames=" + allowedMetricNames +
                ", allowedInstanceIds=" + allowedInstanceIds +
                ", allowedMetricNamesSet=" + allowedMetricNamesSet +
                ", allowedInstaceIdsSet=" + allowedInstaceIdsSet +
                '}';
    }

    /**
     * Method that checks if this Metric must be forwarded
     */
    public void createAllowedInstanceIdsHashSet() {
        allowedInstaceIdsSet = new HashSet<>(allowedInstanceIds);
    }
    public void createAllowedMetricNamesHashSet() {
        allowedMetricNamesSet = new HashSet<>(allowedMetricNames);
    }
    public boolean checkMetric(Metric metric) {
        boolean resultInstanceIds = false;
        boolean resultMetricNames = false;
        if(allowedInstaceIdsSet.contains(metric.getSensor_name()) || allowedInstaceIdsSet.contains("*")) {
            resultInstanceIds = true;
        }
        if(allowedMetricNamesSet.contains(metric.getMonitor()) || allowedMetricNamesSet.contains("*")) {
            resultMetricNames = true;
        }
        return resultInstanceIds && resultMetricNames;
    }
}
