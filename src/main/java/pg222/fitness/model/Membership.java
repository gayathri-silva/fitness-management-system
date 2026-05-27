package pg222.fitness.model;

import java.util.Objects;

public class Membership {
    private final String username;
    private String status;
    private String expiryDate;
    private int tierId;

    public Membership(String username, String status, String expiryDate, int tierId) {
        this.username = username;
        this.status = status;
        this.expiryDate = expiryDate;
        this.tierId = tierId;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getTierId() {
        return tierId;
    }

    public void setTierId(int tierId) {
        this.tierId = tierId;
    }

    @Override
    public String toString() {
        return username + "," + status + "," + expiryDate + "," + tierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return tierId == that.tierId &&
                Objects.equals(username, that.username) &&
                Objects.equals(status, that.status) &&
                Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, status, expiryDate, tierId);
    }
}