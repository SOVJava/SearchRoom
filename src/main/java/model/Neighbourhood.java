package model;

import java.util.Objects;

public class Neighbourhood {
    private String name_borough;
    private String name_neighbourhood;

    public Neighbourhood(String name_borough, String name_neighbourhood) {
        this.name_borough = name_borough;
        this.name_neighbourhood = name_neighbourhood;
    }

    public String getName_borough() {
        return name_borough;
    }

    public void setName_borough(String name_borough) {
        this.name_borough = name_borough;
    }

    public String getName_neighbourhood() {
        return name_neighbourhood;
    }

    public void setName_neighbourhood(String name_neighbourhood) {
        this.name_neighbourhood = name_neighbourhood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbourhood that = (Neighbourhood) o;
        return Objects.equals(name_borough, that.name_borough) &&
                Objects.equals(name_neighbourhood, that.name_neighbourhood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name_borough, name_neighbourhood);
    }

    @Override
    public String toString() {
        return "Neighbourhood{" +
                "name_borough='" + name_borough + '\'' +
                ", name_neighbourhood='" + name_neighbourhood + '\'' +
                '}';
    }
}
