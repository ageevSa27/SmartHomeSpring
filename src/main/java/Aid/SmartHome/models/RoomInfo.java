package Aid.SmartHome.models;

import javax.persistence.*;

@Entity
public class RoomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private boolean protechka;
    private boolean zadimlenie;
    private int vlajnost;
    private int osveshennost;
    private int temp;


    @OneToOne(mappedBy = "roomInfo")
    private Room room;

    public RoomInfo() {
    }

    public RoomInfo(boolean protechka, boolean zadimlenie, int vlajnost, int osveshennost, int temp, Room room) {
        this.protechka = protechka;
        this.zadimlenie = zadimlenie;
        this.vlajnost = vlajnost;
        this.osveshennost = osveshennost;
        this.temp = temp;
        this.room = room;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isProtechka() {
        return protechka;
    }

    public void setProtechka(boolean protechka) {
        this.protechka = protechka;
    }

    public boolean isZadimlenie() {
        return zadimlenie;
    }

    public void setZadimlenie(boolean zadimlenie) {
        this.zadimlenie = zadimlenie;
    }

    public int getVlajnost() {
        return vlajnost;
    }

    public void setVlajnost(int vlajnost) {
        this.vlajnost = vlajnost;
    }

    public int getOsveshennost() {
        return osveshennost;
    }

    public void setOsveshennost(int osveshennost) {
        this.osveshennost = osveshennost;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }





    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
