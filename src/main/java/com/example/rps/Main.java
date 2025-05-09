//package com.example.rps;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Welcome to Rock Paper Scissors!");
//        System.out.print("Enter your name: ");
//        String name = scanner.nextLine();
//
//        GameEngine game = new GameEngine(name);
//
//        System.out.print("How many rounds would you like to play? ");
//        int totalRounds = scanner.nextInt();
//        scanner.nextLine(); // consume leftover newline
//
//        int round = 1;
//
//        while (round <= totalRounds) {
//            System.out.println("\nRound " + round + " of " + totalRounds);
//            System.out.print("Enter your move (ROCK, PAPER, SCISSORS or press SPACE to quit): ");
//            String input = scanner.nextLine().trim().toUpperCase();
//
//            if (input.equals(" ")) {
//                System.out.println("👋 Game ended early by user.");
//                break;
//            }
//
//            try {
//                Move playerMove = Move.valueOf(input);
//                GameResult result = game.playRound(playerMove);
//                result.printResult();
//                round++;
//            } catch (IllegalArgumentException e) {
//                System.out.println(" Invalid input. Please enter ROCK, PAPER, SCISSORS, or press SPACE to quit.");
//            }
//        }
//
//        game.printFinalScore();
//        System.out.println("\nThanks for playing!");
//    }
//}
