package android.thienle.masterdetail;


import android.app.Activity;

import java.util.List;

/**
 * Created by thienlh on 1/18/2016.
 */
public class ItemList {
    static List<Item> items;

    public ItemList(List<Item> items) {
        ItemList.items = items;
    }

    public ItemList() {
    }

    public static ItemList get(Activity activity) {
        return new ItemList();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        ItemList.items = items;
    }

    public Item getItem(int index) {
        return items.get(index);
    }
}
