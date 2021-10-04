package ca.cmpt276.androidgamescoreapp;

import java.util.LinkedList;

public class GameManager {
    private LinkedList<Game> games;
    private int numberOfGames;

    private static GameManager instance;
    private GameManager(){}

    public static GameManager getInstance() {
        if(instance == null) {
            instance = new GameManager();
            instance.games = new LinkedList<>();
            instance.numberOfGames = 0;
        }
        return instance;
    }

    //Create and add new Game and add it to the "games" linked list
    public void addNewGame(Game newGame){
        games.add(numberOfGames, newGame);
        ++numberOfGames;
    }
    //Returns the Game from the linked list based off index

    public Game getGame(int index){
        return games.get(index);
    }
    //Removes a Game from the linked list based off index

    public void removeGame(int index){
        games.remove(index - 1);
        --numberOfGames;
    }
    //Returns number of created games

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public LinkedList<Game> getGames() {
        return games;
    }
}
