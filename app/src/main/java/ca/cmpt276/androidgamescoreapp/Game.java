package ca.cmpt276.androidgamescoreapp;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.Locale;

public class Game {
    private int player_count;

    private LinkedList<PlayerScore> player_list; // Stores list of players

    private LocalDate date;
    private LocalTime time;
    public Game(){
        LocalDateTime date_time = LocalDateTime.now();
        time = date_time.toLocalTime();
        date = date_time.toLocalDate();
        player_list = new LinkedList<>();
    }

    //New PlayerScore class is created and added to the player_list linked list.

    public void addPlayer(int cards, int sum, int wager_cards){
        PlayerScore new_player = new PlayerScore(cards, sum, wager_cards);
        player_list.add(player_count,new_player);
        ++player_count;
    }
    // Returns a LinkedList of the winning player(s)

    public void removePlayer(int index){
        player_list.remove(index);
        --player_count;
    }

    public LinkedList<Integer> gameWinners(){
        LinkedList<Integer> winners = new LinkedList<>();
        //0 players means no winners
        if(player_count == 0){
            return winners;
        }
        //Set the first player as the winner initially and its points
        winners.add(1);
        int points = player_list.get(0).getPoints();

        //Iterate over the rest of players and change the winners linked list according the points
        for(int i = 1; i < player_count; i++){
            int count = player_list.get(i).getPoints();
            //If player has the same ammount of points, add him to the winners list
            if(count == points){
                winners.add(i+1);
            }
            //If the player has more points than the winner(s), then clear the list and add the new player
            if(count > points){
                winners.clear();
                winners.add(i+1);
            }
        }
        return winners;
    }
    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public int getPlayer_count() {
        return player_count;
    }

    //Returns the score for a player in the player_list linked list.

    public int getScorePlayer(int index){
        return player_list.get(index).getPoints();
    }
    @NonNull
    @Override
    public String toString() {
        String pattern = "hh:mma";

        if(getScorePlayer(0) == getScorePlayer(1)){
            return getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " " +
                    getDate().getDayOfMonth() + " @ " + getTime().format(DateTimeFormatter.ofPattern(pattern))
                    + " - Tie : " + getScorePlayer(0) + " vs " + getScorePlayer(1);
        }
        return getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)  + " " +
                getDate().getDayOfMonth() + " @ " + getTime().format(DateTimeFormatter.ofPattern(pattern))
                + " - Player " + gameWinners().get(0) + " won : " +
                getScorePlayer(0) + " vs " + getScorePlayer(1);
    }

    public LinkedList<PlayerScore> getPlayer_list() {
        return player_list;
    }
}
