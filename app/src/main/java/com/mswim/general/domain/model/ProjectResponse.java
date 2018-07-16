
package com.mswim.general.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "STATUS",
    "projects"
})
public class ProjectResponse implements Serializable
{

    @JsonProperty("STATUS")
    private String sTATUS;
    @JsonProperty("projects")
    private List<Project> projects = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7857281120364320657L;

    @JsonProperty("STATUS")
    public String getSTATUS() {
        return sTATUS;
    }

    @JsonProperty("STATUS")
    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    @JsonProperty("projects")
    public List<Project> getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
