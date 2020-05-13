package com.cg.assetmanagementsystem.requestservice.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;
    @Column(name = "requested_from")
    private LocalDate requestedFrom;
    @Column(name = "requested_till")
    private LocalDate requestedTill;
    @Column(name = "requested_by")
    private Integer requestedBy;
    @Column(name = "requested_for")
    private Integer requestedFor;
    @Column(name = "requested_asset")
    private Integer requestedAsset;
    @Column(name = "status")
    private String status;

    public Request() {
    }

    public Request(LocalDate requestedFrom, LocalDate requestedTill, Integer requestedBy, Integer requestedFor, Integer requestedAsset, String status) {
        this.requestedFrom = requestedFrom;
        this.requestedTill = requestedTill;
        this.requestedBy = requestedBy;
        this.requestedFor = requestedFor;
        this.requestedAsset = requestedAsset;
        this.status = status;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public LocalDate getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(LocalDate requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public LocalDate getRequestedTill() {
        return requestedTill;
    }

    public void setRequestedTill(LocalDate requestedTill) {
        this.requestedTill = requestedTill;
    }

    public Integer getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Integer requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Integer getRequestedFor() {
        return requestedFor;
    }

    public void setRequestedFor(Integer requestedFor) {
        this.requestedFor = requestedFor;
    }

    public Integer getRequestedAsset() {
        return requestedAsset;
    }

    public void setRequestedAsset(Integer requestedAsset) {
        this.requestedAsset = requestedAsset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return getRequestId().equals(request.getRequestId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestId());
    }
}
