package email;

import domain.User;

import java.util.List;

public class Email {

    private User from;
    private List<User> to;
    private String title, body;

    public User getFrom() {
        return from;
    }

    public Email setFrom(User from) {
        this.from = from;
        return this;
    }

    public List<User> getTo() {
        return this.to;
    }

    public Email setTo(List<User> to) {
        this.to = to;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Email setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Email setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        List<User> users = getTo();
        StringBuilder usersTo = new StringBuilder();
        for (User user : users) {
            usersTo.append(user);
        }

        return "SEND EMAIL:" + "\n" +
                "From: " + getFrom() +
                "To: " + usersTo +
                "Title: " + getTitle() + "\n" +
                "Body: " + getBody() + "\n";
    }
}