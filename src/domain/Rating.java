package domain;

public class Rating {

    private final User user;
    private final Recipe recipe;
    private int stars; // acesta poate varia de la 1 la 5, de exemplu
    private String comment; // optional, nu toți utilizatorii vor dori să lase un comentariu

    public Rating(User user, Recipe recipe, int stars) {
        this.user = user;
        this.recipe = recipe;
        this.stars = stars;
    }

    public User getUser() {
        return user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
