package model;

import java.util.Objects;

public class Room{
    private int id;
    private String name;
    private int host_id;
    private String neighbourhood;
    private double price;
    private int minimumNights;
    private int countReviews;
    private String typeRoom;

    public Room(int id, String name, int host_id, String neighbourhood, double price, int minimumNights,
                int countReviews, String typeRoom) {
        this.id = id;
        this.name = name;
        this.host_id = host_id;
        this.neighbourhood = neighbourhood;
        this.price = price;
        this.minimumNights = minimumNights;
        this.countReviews = countReviews;
        this.typeRoom = typeRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHost_id() {
        return host_id;
    }

    public void setHost_id(int host_id) {
        this.host_id = host_id;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinimumNights() {
        return minimumNights;
    }

    public void setMinimumNights(int minimumNights) {
        this.minimumNights = minimumNights;
    }

    public int getCountReviews() {
        return countReviews;
    }

    public void setCountReviews(int countReviews) {
        this.countReviews = countReviews;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                host_id == room.host_id &&
                Double.compare(room.price, price) == 0 &&
                minimumNights == room.minimumNights &&
                countReviews == room.countReviews &&
                Objects.equals(name, room.name) &&
                Objects.equals(neighbourhood, room.neighbourhood) &&
                Objects.equals(typeRoom, room.typeRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, host_id, neighbourhood, price, minimumNights, countReviews, typeRoom);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", host_id=" + host_id +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", price=" + price +
                ", minimumNights=" + minimumNights +
                ", countReviews=" + countReviews +
                ", typeRoom='" + typeRoom + '\'' +
                '}';
    }
}
