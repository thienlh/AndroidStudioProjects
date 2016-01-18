package fpt.petcare.android.fragment;

import java.util.UUID;

/**
 * Created by thienlh on 1/11/2016.
 */
public class Dish {
  private UUID id;
  private String viName;
  private String enName;
  private String recipe;
  private String image;

  public UUID getId() {
    return id;
  }

  public Dish(String viName, String enName, String recipe, String image) {
    this.id = UUID.randomUUID();
    this.viName = viName;
    this.enName = enName;
    this.recipe = recipe;
    this.image = image;
  }

  public String getViName() {
    return viName;
  }

  public void setViName(String viName) {
    this.viName = viName;
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getRecipe() {
    return recipe;
  }

  public void setRecipe(String recipe) {
    this.recipe = recipe;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
