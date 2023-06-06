package email;

import domain.User;

import java.util.ArrayList;

public class Email {

	private User from;
    private ArrayList<User> to, copy;
    private String title, body;

    public User getFrom() {
        return from;
    }

    public Email setFrom(User from) {
        this.from = from;
        return this;
    }

    public ArrayList<User> getTo() {
        return to;
    }

    public void setTo(ArrayList<User> to) {
        this.to = to;
    }

    public Email setTo(User to) {
    	ArrayList<User> toList = new ArrayList<>();
    	toList.add(to);
        setTo(toList);
        return this;
    }

    public ArrayList<User> getCopy() {
        return copy;
    }

    public void setCopy(ArrayList<User> copy) {
        this.copy = copy;
    }

    public Email setCopy(User copy) {
    	ArrayList<User> copyList = new ArrayList<>();
    	copyList.add(copy);
        setCopy(copyList);
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
    	ArrayList<User> users = getTo();
    	StringBuilder usersTo = new StringBuilder();
    	for (User user : users) {
            usersTo.append(user);
    	}
    	
    	users = getCopy();
    	StringBuilder usersCopy = new StringBuilder();
    	for (User user : users) {
            usersCopy.append(user);
    	}
    	
    	return "SEND EMAIL:" + "\n" + 
    			"From: " + getFrom() +
    			"To: " + usersTo +
    			"Copy: " + usersCopy +
    			"Title: " + getTitle() + "\n" +
    			"Body: " + getBody() + "\n";
    }
}