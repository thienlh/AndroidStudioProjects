package fpt.petcare.android.event;

import java.util.Date;
import java.util.UUID;

/**
 * Created by thienlh on 1/12/2016.
 */
public class Event {
  private UUID id;
  private String title;
  private Date date;
  private boolean resolve;

  public Event(String title, Date date, boolean resolve) {
    this.id = UUID.randomUUID();
    this.title = title;
    this.date = date;
    this.resolve = resolve;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "Event{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", date=" + date +
        ", resolve=" + resolve +
        '}';
  }

  public Event() {
  }

  public Date getDate() {
    return date;
  }

  public boolean isResolve() {
    return resolve;
  }
}
