package util;

import model.Host;
import model.Neighbourhood;
import model.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


//так как нет equals и hashcode нигде, то должны быть повторения в hosts, проверить это ?
//так как в ключе стоит Neighbourhood то скорее всего группировки по городам нет , проверить это
//формирование map убрать из load и перенести в отдельный метод, который будет вызван в конце метода load
//решение всех задач сделать через тот map

public class Database {
    private String nameR;
    private String nameN;

    private Set<Neighbourhood> neighbourhoods;
    private Set<Host> hosts;
    private ArrayList<Room> rooms;
    private Map<String, ArrayList<Room>> catalog; //Map<String, ArrayList<Room>>

    public Database(String nameRooms, String nameNeighbourhood) throws IOException {
        this.nameR = nameRooms;
        this.nameN = nameNeighbourhood;
        this.neighbourhoods = new HashSet<>();
        this.hosts = new HashSet<>();
        this.rooms = new ArrayList<>();
        this.catalog = new HashMap<>();
        load();
    }

    public String getNameR() {
        return nameR;
    }

    public String getNameN() {
        return nameN;
    }

    private void load() throws IOException { //private и вызов в консуторе , ошибку в сигнатуру конструктора
        try(BufferedReader br = new BufferedReader(new FileReader(nameN))){
            br.readLine();
            while (br.ready()){
                String line = br.readLine();
                String[] arrLine = line.split(";");
                Neighbourhood neighbourhood = new Neighbourhood(arrLine[1], arrLine[0]);
                neighbourhoods.add(neighbourhood);
            }
        }

        try (BufferedReader brr = new BufferedReader(new FileReader(nameR))){
            brr.readLine();
            while (brr.ready()){
                String line = brr.readLine();
                String[] arrLine = line.split(";");
                //id; name; host_id, host_name; neighbourhood; room_type; price; minimum_nights; number_of_reviews; days_open
                // 0    1     2         3           4             5         6         7               8                9
                hosts.add(new Host(Integer.parseInt(arrLine[2]), arrLine[3]));
                Room room = new Room(Integer.parseInt(arrLine[0]), arrLine[1], Integer.parseInt(arrLine[2]),
                        arrLine[4], Double.parseDouble(arrLine[6].replace("$", "").
                        replace(",", ".").replace(" ", "")),
                        Integer.parseInt(arrLine[7]), Integer.parseInt(arrLine[8]), arrLine[5]);
                rooms.add(room);
            }
        }
        for (Neighbourhood item : neighbourhoods) {
            catalog.put(item.getName_borough(), new ArrayList<>());
        }
        for (Neighbourhood item : neighbourhoods) {
            for (Room room : rooms) {
                if (item.getName_neighbourhood().equals(room.getNeighbourhood())) {
                    catalog.get(item.getName_borough()).add(room);
                }
            }
        }
    }

    public void averagePriceByBorough() {
        for (Map.Entry<String, ArrayList<Room>> item : catalog.entrySet()) {
            int count = 0;
            int sum = 0;
            for (Room room : item.getValue()) {
                sum += room.getPrice();
                count++;
            }
            System.out.printf("%.2f $ - средняя стоимость аренды квартиры в %s\n", (double)sum/count, item.getKey());
        }
    }

    public ArrayList<Room> searchRoom(String town, String typeRoom){
        ArrayList<Room> res = new ArrayList<>();
        ArrayList<Room> result = catalog.get(town);
        for (Room room : result) {
            if (room.getTypeRoom().equals(typeRoom))
                res.add(room);
        }
        return res;
    }

    public String searchNameHost(int id){
        for (Host item : hosts) {
            if (item.getId() == id)
                return item.getName();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Database database = (Database) o;
        return Objects.equals(nameR, database.nameR) &&
                Objects.equals(nameN, database.nameN) &&
                Objects.equals(neighbourhoods, database.neighbourhoods) &&
                Objects.equals(hosts, database.hosts) &&
                Objects.equals(rooms, database.rooms) &&
                Objects.equals(catalog, database.catalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameR, nameN, neighbourhoods, hosts, rooms, catalog);
    }

    @Override
    public String toString() {
        return "Database{" +
                "nameR='" + nameR + '\'' +
                ", nameN='" + nameN + '\'' +
                ", neighbourhoods=" + neighbourhoods +
                ", hosts=" + hosts +
                ", rooms=" + rooms +
                ", catalog=" + catalog +
                '}';
    }
}
