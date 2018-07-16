
package com.mswim.general.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "company",
    "starred",
    "name",
    "show-announcement",
    "announcement",
    "description",
    "status",
    "isProjectAdmin",
    "created-on",
    "category",
    "start-page",
    "startDate",
    "logo",
    "notifyeveryone",
    "id",
    "last-changed-on",
    "endDate",
    "harvest-timers-enabled"
})
public class Project implements Serializable
{

    @JsonProperty("company")
    private Company company;
    @JsonProperty("starred")
    private Boolean starred;
    @JsonProperty("name")
    private String name;
    @JsonProperty("show-announcement")
    private Boolean showAnnouncement;
    @JsonProperty("announcement")
    private String announcement;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("isProjectAdmin")
    private Boolean isProjectAdmin;
    @JsonProperty("created-on")
    private String createdOn;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("start-page")
    private String startPage;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("notifyeveryone")
    private Boolean notifyeveryone;
    @JsonProperty("id")
    private String id;
    @JsonProperty("last-changed-on")
    private String lastChangedOn;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("harvest-timers-enabled")
    private String harvestTimersEnabled;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1527479869466513641L;

    @JsonProperty("company")
    public Company getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonProperty("starred")
    public Boolean getStarred() {
        return starred;
    }

    @JsonProperty("starred")
    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("show-announcement")
    public Boolean getShowAnnouncement() {
        return showAnnouncement;
    }

    @JsonProperty("show-announcement")
    public void setShowAnnouncement(Boolean showAnnouncement) {
        this.showAnnouncement = showAnnouncement;
    }

    @JsonProperty("announcement")
    public String getAnnouncement() {
        return announcement;
    }

    @JsonProperty("announcement")
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("isProjectAdmin")
    public Boolean getIsProjectAdmin() {
        return isProjectAdmin;
    }

    @JsonProperty("isProjectAdmin")
    public void setIsProjectAdmin(Boolean isProjectAdmin) {
        this.isProjectAdmin = isProjectAdmin;
    }

    @JsonProperty("created-on")
    public String getCreatedOn() {
        return createdOn;
    }

    @JsonProperty("created-on")
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("start-page")
    public String getStartPage() {
        return startPage;
    }

    @JsonProperty("start-page")
    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    @JsonProperty("logo")
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @JsonProperty("notifyeveryone")
    public Boolean getNotifyeveryone() {
        return notifyeveryone;
    }

    @JsonProperty("notifyeveryone")
    public void setNotifyeveryone(Boolean notifyeveryone) {
        this.notifyeveryone = notifyeveryone;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("last-changed-on")
    public String getLastChangedOn() {
        return lastChangedOn;
    }

    @JsonProperty("last-changed-on")
    public void setLastChangedOn(String lastChangedOn) {
        this.lastChangedOn = lastChangedOn;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("harvest-timers-enabled")
    public String getHarvestTimersEnabled() {
        return harvestTimersEnabled;
    }

    @JsonProperty("harvest-timers-enabled")
    public void setHarvestTimersEnabled(String harvestTimersEnabled) {
        this.harvestTimersEnabled = harvestTimersEnabled;
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
