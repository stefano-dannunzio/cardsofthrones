package com.stefanodannunzio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.Scanner;

import org.apache.http.ParseException;

import com.stefanodannunzio.characters.Elf;
import com.stefanodannunzio.characters.Human;
import com.stefanodannunzio.characters.Orc;
import com.stefanodannunzio.characters.Character;

public class Main {
    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static Random random = new Random();


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an option:");
        System.out.println("1. Start game with random characters");
        System.out.println("2. Start game by manually entering characters");
        System.out.println("3. Read logs from previous games");
        System.out.println("4. Delete logs");
        System.out.println("5. Exit");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (option) {
            case 1:
                generateRandomCharacters();
                
                startGame();
                break;
            case 2:
                enterCharactersManually();
                
                startGame();
                break;
            case 3:
                readLogs();
                break;
            case 4:
                deleteLogs();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private static void generateRandomCharacters() {
        try {
            NicknameGenerator nicknameGenerator = new NicknameGenerator();
    
            for (int i = 0; i < 6; i++) {
                String name = RandomUserGenerator.getRandomName();
                String nickname = nicknameGenerator.generateNickname();
                LocalDate birthDate = generateRandomBirthDate();
    
                Character character;
                switch (random.nextInt(3)) {
                    case 0:
                        character = new Human(name, nickname, birthDate);
                        break;
                    case 1:
                        character = new Elf(name, nickname, birthDate);
                        break;
                    case 2:
                        character = new Orc(name, nickname, birthDate);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
                }
    
                if (i < 3) {
                    player1.addCharacter(character);
                } else {
                    player2.addCharacter(character);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    private static LocalDate generateRandomBirthDate() {
        int year = random.nextInt(20) + 1980;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;

        return LocalDate.of(year, month, day);
    }

    private static void enterCharactersManually() {
    Scanner scanner = new Scanner(System.in);

    for (int i = 0; i < 6; i++) {
        System.out.println("Enter character No. " + (i + 1) + " data:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Nickname: ");
        String nickname = scanner.nextLine();

        System.out.print("Birthdate (dd/MM/yyyy): ");
        String birthDateStr = scanner.nextLine();
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid datetime format. Using today's date.");
            birthDate = LocalDate.now();
        }

        Character character;
        switch (random.nextInt(3)) {
            case 0:
                character = new Human(name, nickname, birthDate);
                break;
            case 1:
                character = new Elf(name, nickname, birthDate);
                break;
            case 2:
                character = new Orc(name, nickname, birthDate);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
        }

        if (random.nextBoolean()) {
            player1.addCharacter(character);
        } else {
            player2.addCharacter(character);
        }
    }
}

    private static void startGame() {

        GameLog.startLogging();

        GameLog.writeToBothConsoleAndFile("*****************************");

        GameLog.writeToBothConsoleAndFile("Player 1 characters:");
        GameLog.writeToBothConsoleAndFile(player1.getCharactersInfo());
        GameLog.writeToBothConsoleAndFile("*****************************");
        GameLog.writeToBothConsoleAndFile("Player 2 characters:");
        GameLog.writeToBothConsoleAndFile(player2.getCharactersInfo());
        GameLog.writeToBothConsoleAndFile("*****************************");

        // Inicializar la ronda actual
        int currentRound = 1;

        // Jugar hasta que uno de los jugadores se quede sin personajes
        while (player1.hasCharacters() && player2.hasCharacters()) {
            GameLog.writeToBothConsoleAndFile("Round " + currentRound);


            // Obtener los personajes a enfrentar
            Character player1Character = player1.getRandomCharacter();
            Character player2Character = player2.getRandomCharacter();

            // Crear un objeto Combat y ejecutar los ataques
            Combat combat = new Combat(player1Character, player2Character);
            combat.start();

            // Verificar si algún personaje ha sido derrotado
            if (player1Character.getHealth() <= 0) {
                GameLog.writeToBothConsoleAndFile(player1Character.getName() + " fell.");
                player1.removeCharacter(player1Character);
            }
            if (player2Character.getHealth() <= 0) {
                GameLog.writeToBothConsoleAndFile(player2Character.getName() + " fell.");
                player2.removeCharacter(player2Character);
            }

            // Otorgar mejoras al personaje ganador
            if (player1Character.getHealth() > 0) {
                player1Character.improveStats();
                GameLog.writeToBothConsoleAndFile(player1Character.getName() + " received an upgrade.");
            } else if (player2Character.getHealth() > 0) {
                player2Character.improveStats();
                GameLog.writeToBothConsoleAndFile(player2Character.getName() + " received an upgrade.");
            }

            // Pasar a la siguiente ronda
            GameLog.writeToBothConsoleAndFile("-----------------------------------------------------------");
            currentRound++;
    }

    // Declarar al ganador
    if (player1.hasCharacters()) {
        GameLog.writeToBothConsoleAndFile("Player 1 Wins the Iron Throne!");
    } else {
        GameLog.writeToBothConsoleAndFile("Player 2 Wins the Iron Throne!");
    }
    }

    public static void readLogs() {
        try {
            File logFile = new File(GameLog.LOG_FILE_NAME);
            if (logFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(logFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } else {
                System.out.println("No hay logs disponibles.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLogs() {
        File logFile = new File(GameLog.LOG_FILE_NAME);
        File dataFile = new File(GameLog.DATA_FILE_NAME);

        if (logFile.delete()) {
            System.out.println("Archivo de logs eliminado.");
        } else {
            System.out.println("No se pudo eliminar el archivo de logs.");
        }

        if (dataFile.delete()) {
            System.out.println("Archivo de datos eliminado.");
        } else {
            System.out.println("No se pudo eliminar el archivo de datos.");
        }
    }
}