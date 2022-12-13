package com.dalhousie.foodnculture.models;

import com.dalhousie.foodnculture.enums.EventType;

public class Event {

    private int id;
    private String title;
    private String description;
    private EventType eventType;
    private String status;
    private String startDatetime;
    private String endDatetime;
    private String venue;
    private int maxCapacity;
    private String updatedAt;
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDateTime) {
        this.startDatetime = startDateTime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDateTime) {
        this.endDatetime = endDateTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updateAt) {
        this.updatedAt = updateAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", description=" + description + ", eventType=" + eventType
                + ", status=" + status + ", startDateTime=" + startDatetime + ", endDateTime=" + endDatetime
                + ", venue=" + venue + ", maxCapacity=" + maxCapacity + ", updatedAt=" + updatedAt + ", createdAt="
                + createdAt + "]";
    }

}
