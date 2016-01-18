package com.twe.samples.eventnote;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

public class Event {
  private UUID id;
  private String title;
  private String details;
  private Date date;
  private boolean solved = false;

  public Event() {
    this.id = UUID.randomUUID();
    this.date = new Date();
  }

  public Event(UUID id, Date date) {
    this.id = id;
    this.date = date;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Event setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDetails() {
    return details;
  }

  public Event setDetails(String details) {
    this.details = details;
    return this;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isSolved() {
    return solved;
  }

  public Event setSolved(boolean solved) {
    this.solved = solved;
    return this;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s: %s", new DateFormat().format("MMM dd, yyyy", date), title, details);
  }
}
