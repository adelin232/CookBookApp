package main;

import domain.CookBook;
import domain.Recipe;
import domain.User;

public class CookBookApplication {

    private static CookBook cookBook;

    public static void main(String[] args) {
        cookBook = new CookBook();
        modifyCookBook();
    }

    private static void modifyCookBook() {
        User user1 = new User("adelin232", "narcis.adelin.miulet@gmail.com");
//        Recipe recipe = new Recipe();
    }
}