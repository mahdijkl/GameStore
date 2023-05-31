package ir.ac.kntu;

import java.util.ArrayList;

import ir.ac.kntu.Game.Type;

public class Test {
    public static void main(String[] args) {
        Game game = new Game("pes", "Football", 500, Type.SPORT);
        ArrayList<Game> games = new ArrayList<>();
        games.add(0, game);
        System.out.println(games);
        game.setName("Fifa");
        System.out.println(games);

    }
}
