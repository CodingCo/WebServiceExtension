package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author simon
 */
@Entity
public class ClassRoom implements Serializable {

    @Id
    private String roomNumber;

    @OneToMany(mappedBy = "classRoom")
    @Column(name = "TIME_BLOCK")
    private Collection<TimeBlock> timeBlocks;

    private int noOfSeats;

    public ClassRoom() {
    }

    public ClassRoom(String roomNumber, Collection<TimeBlock> timeBlocks, int noOfSeats) {
        this.roomNumber = roomNumber;
        this.timeBlocks = timeBlocks;
        this.noOfSeats = noOfSeats;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public String toString() {
        return "ClassRoom{" + "roomNumber=" + roomNumber + ", timeBlocks=" + timeBlocks + ", noOfSeats=" + noOfSeats + '}';
    }
    
    
    // adders removers
}
