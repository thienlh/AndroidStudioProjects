package com.twe.android.master_detail;

import android.graphics.Bitmap;

import java.util.UUID;

public class Version {
  private UUID id;
  private String code;
  private String level;
  private Bitmap icon;

  public Version(String code, String level, Bitmap icon) {
    this.id = UUID.randomUUID();
    this.code = code;
    this.level = level;
    this.icon = icon;
  }

  public UUID getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public Bitmap getIcon() {
    return icon;
  }

  public void setIcon(Bitmap icon) {
    this.icon = icon;
  }
}
