package tr.com.orties.curse.model;

/**
 * Created by Yunus on 13-Mar-16.
 */
public class Message {
    private String message;
    private String username;
    private Location location;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
