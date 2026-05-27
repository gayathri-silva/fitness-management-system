package pg222.fitness.model;

import java.util.Objects;

public class RenewalRequest {
    private final int requestId;
    private final String username;
    private final String requestDate;
    private String status;
    private final int tierId;

    public RenewalRequest(int requestId, String username, String requestDate, String status, int tierId) {
        this.requestId = requestId;
        this.username = username;
        this.requestDate = requestDate;
        this.status = status;
        this.tierId = tierId;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getUsername() {
        return username;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTierId() {
        return tierId;
    }

    @Override
    public String toString() {
        return requestId + "," + username + "," + requestDate + "," + status + "," + tierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenewalRequest that = (RenewalRequest) o;
        return requestId == that.requestId &&
                tierId == that.tierId &&
                Objects.equals(username, that.username) &&
                Objects.equals(requestDate, that.requestDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, username, requestDate, status, tierId);
    }
}