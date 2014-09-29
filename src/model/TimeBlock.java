package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author simon
 */
@Entity
public class TimeBlock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    private Course course;

    @Temporal(TemporalType.DATE)
    @Column(name = "COURSE_DATE")
    private Date date;

    @Temporal(TemporalType.TIME)
    @Column(name = "START_TIME")
    private Calendar startTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "END_TIME")
    private Calendar endTime;

    @ManyToOne
    private ClassRoom classRoom;

    public TimeBlock() {
    }

    public TimeBlock(Course course, Date date, Calendar startTime, Calendar endTime) {
        this.course = course;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return "TimeBlock{" + "course=" + course + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }

}
