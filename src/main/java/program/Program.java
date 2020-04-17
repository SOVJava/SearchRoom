package program;

import constants.Town;
import constants.TypeRoom;
import model.Room;
import util.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Database db;
        try {
            db = new Database("rooms.csv", "boroughs.csv");
        } catch (IOException e) {
            System.out.println("Файлы не найдены");
            return;
        }

        db.averagePriceByBorough();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nЗдравствуйте!\nдля поиска подходящего варианта\n" +
                    "1 - Queens, 2 - Staten Island, 3 - Brooklyn, 4 - Manhattan, 5 - Bronx, 6 - exit\nВаш выбор:");
            String choice_1 = null;
            boolean has1 = true;
            String town = "";
            while (has1) {
                try {
                    town = sc.nextLine();
                    if (Integer.parseInt(town) >= 1 && Integer.parseInt(town) <= 6) {
                        has1 = false;
                    }
                    else {
                        System.out.println("Некорректный ввод, повторите ввод:");
                    }
                    if (Integer.parseInt(town) == 1)
                        choice_1 = Town.QUEENS;
                    if (Integer.parseInt(town) == 2)
                        choice_1 = Town.STATEN_ISLAND;
                    if (Integer.parseInt(town) == 3)
                        choice_1 = Town.BROOKLYN;
                    if (Integer.parseInt(town) == 4)
                        choice_1 = Town.MANHATTAN;
                    if (Integer.parseInt(town) == 5)
                        choice_1 = Town.BRONX;
                    if (Integer.parseInt(town) == 6) {
                        sc.close();
                        return;
                    }
                } catch (NumberFormatException ignored) {
                    System.out.println("Некорректный ввод, повторите ввод:");
                }
            }
            System.out.println("Ваш выбор " + choice_1 + "\nВыберете тип комнаты:\n" +
                    "1 - Private room, 2 - Entire home/apt, 3 - Shared room, 4 - back\nВаш выбор:");

            String choice_2 = null;
            String typeRoom = "";
            boolean has2 = true;
            boolean has2_1 = true;

            while (has2) {
                try {
                    typeRoom = sc.nextLine();
                    if (Integer.parseInt(typeRoom)>=1 && Integer.parseInt(typeRoom)<=4){
                        has2 = false;
                    }
                    else
                        System.out.println("Некорректный ввод, повторите ввод:");

                    if (Integer.parseInt(typeRoom) == 1)
                        choice_2 = TypeRoom.PRIVATE_ROOM;
                    if (Integer.parseInt(typeRoom) == 2)
                        choice_2 = TypeRoom.ENTIRE_HOME;
                    if (Integer.parseInt(typeRoom) == 3)
                        choice_2 = TypeRoom.SHARED_ROOM;
                    if (Integer.parseInt(typeRoom) == 4){
                        has2_1 = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод, повторите ввод:");
                }
            }

            if (has2_1) {
                System.out.println("Ваш выбор " + choice_2 + "\nУкажите колличество ночей:");
                String countNight = sc.nextLine();
                int choice_3 = Integer.parseInt(countNight);

                ArrayList<Room> resultSet = db.searchRoom(choice_1, choice_2);
                ArrayList<Room> result = new ArrayList<>(resultSet);

                result.sort(new Comparator<Room>() {
                    @Override
                    public int compare(Room o1, Room o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });

                int count = 0;
                System.out.println("------------------------------------------------------------------------------" +
                        "--------------------------");
                System.out.println("| Name                                               | Name Host              " +
                        "|  1 Night   |  ИТОГО     |");
                System.out.println("------------------------------------------------------------------------------" +
                        "--------------------------");
                for (Room item : result) {
                    if (choice_3 <= item.getMinimumNights()) {
                        System.out.printf("| %-50s | %-22s | %10.2f | %10.2f |\n", item.getName(),
                                db.searchNameHost(item.getHost_id()), item.getPrice(), item.getPrice() * choice_3);
                        count++;
                    }
                }
                System.out.println("------------------------------------------------------------------------------" +
                        "--------------------------");
                if (count != 0)
                    System.out.printf("В %s найдено %d подходящих вариантов (Тип комнаты: %s, на %d ночь/ночей))\n",
                        choice_1, count, choice_2, choice_3);
                else
                    System.out.println("Извините, в " + choice_1 + " нет подходящих вариантов на " + choice_3 + " дней");
            }
        }
    }
}

