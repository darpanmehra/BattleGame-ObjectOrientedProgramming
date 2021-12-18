package battleground;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import battleground.entities.players.Player;

/**
 * Driver method for the battle.
 */
public class Driver {

  /**
   * Main method of the driver class.
   *
   * @param args args.
   */
  public static void main(String[] args) {

    try {
      String input;
      Random random = new Random();
      //Introduction to the program.
      System.out.println("<------ Welcome to the Viking Battle ------>");
      System.out.println("This is a two player turn-based game where every player is assigned "
              + "abilities at random and can pick the gear from the bag at random.\nPlayers "
              + "can also request a weapon from the armory which is assigned randomly. "
              + "\nThe Player with higher charisma dazzles their opponent and gets in the first "
              + "strike.\nThe player who did not take a total damage greater than or equal to "
              + "their health is declared the victor.\nIt is possible for a battle to end "
              + "in a draw. \n");
      do {
        //Create a battle with 2 players.
        Battle battle = new Battle(2, random);

        //Creating players in the battle.
        System.out.println("<------ Players in the Battle ------>");
        System.out.println(battle.getPlayersBasicInfo());

        System.out.println("<------ Bag of gears ------>");
        System.out.println(battle.getGearBag() + "\n");

        System.out.println("Picking Gear for Players.... \n");
        battle.pickGearForPlayers();

        System.out.println("Picking Weapon for Player 1.... ");
        battle.pickWeapon(1);
        System.out.println("Picking Weapon for Player 2.... \n");
        battle.pickWeapon(2);

        System.out.println("<------ Players Info ------>\n");
        System.out.print(battle.getPlayersDetailedInfo() + "\n");


        //First Strike
        System.out.println("<------ Selecting order of strike (First Strike).... ------>");
        List<Player> players = battle.getInitialOrderOfFight();
        System.out.println("First Strike goes to: " + players.get(0).getName() + "\n");

        //Rounds of battle
        Player attacker;
        Player opponent;

        for (int i = 1; i <= 50; i++) {

          System.out.println("<------ Round " + i + " ------>");
          attacker = players.get(0);
          opponent = players.get(1);
          System.out.println("Attacker:" + attacker.getName()
                  + ", Opponent: " + opponent.getName());
          System.out.println(battle.roundAction(attacker, opponent) + "\n");

          //Check if Battle is over. If not change the attacker and opponent.
          if (battle.isBattleOver()) {
            break;
          } else {
            if (i == 50) {
              System.out.println("\nMatch ends in a Draw \n");
            } else {
              Collections.swap(players, 0, 1);
            }
          }
        }

        //Ask for rematch
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want a rematch? (yes/no)");
        input = sc.nextLine();
      }
      while (!input.equalsIgnoreCase("no"));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
