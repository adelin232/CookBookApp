package service;

import domain.CookBook;
import domain.User;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CookBookReport {

    public int getNumberOfUsers(CookBook cookBook) {
        return cookBook.getUsers().size();
    }

    /**
     * TODO: Recipes vs FavoriteRecipes
     */
    public int getNumberOfFavoriteRecipes(CookBook cookBook) {
//        return cookBook.getUsers().stream().map(user -> user.getFavoriteRecipes().size()).reduce(0, Integer::sum);
        return cookBook.getUsers().stream().mapToInt(user -> user.getFavoriteRecipes().size()).sum();
    }

    public SortedSet<User> getUsersSorted(CookBook cookBook) {
//        return cookBook.getUsers().stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getUsername))));
        return cookBook.getUsers().stream().sorted(Comparator.comparing(User::getUsername)).collect(Collectors.toCollection(TreeSet::new));
    }
}
