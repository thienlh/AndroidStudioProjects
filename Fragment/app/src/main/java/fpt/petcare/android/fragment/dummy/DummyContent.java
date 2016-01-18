package fpt.petcare.android.fragment.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fpt.petcare.android.fragment.Dish;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

  /**
   * An array of dishs.
   */
  public static final List<Dish> ITEMS = new ArrayList<Dish>();

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static final Map<String, Dish> ITEM_MAP = new HashMap<String, Dish>();


  static {
    // Add some sample items.
    Dish item1 = new Dish("Banh Beo", "Beo Beo", "file1", "file1");
    Dish item2 = new Dish("Banh xeo", "Xeo Xeo", "file2", "file2");
    Dish item3 = new Dish("Banh mi", "Mi Mi", "file3", "file3");
    addItem(item1);
    addItem(item2);
    addItem(item3);
  }

  private static void addItem(Dish item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.getId().toString(), item);
  }

//
//  private static Dish createDummyItem(int position) {
//    return new Dish(String.valueOf(position), "Item " + position, makeDetails(position));
//  }

//  private static String makeDetails(int position) {
//    StringBuilder builder = new StringBuilder();
//    builder.append("Details about Item: ").append(position);
//    for (int i = 0; i < position; i++) {
//      builder.append("\nMore details information here.");
//    }
//    return builder.toString();
//  }
}
