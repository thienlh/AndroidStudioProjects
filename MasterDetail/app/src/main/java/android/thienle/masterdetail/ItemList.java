package android.thienle.masterdetail;


import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by thienlh on 1/18/2016.
 */
public class ItemList {
    private static ItemList singleton;
    private static List<Item> list = new ArrayList<>();

    public ItemList() {
        load();
    }

    //  Singleton design pattern
    private static ItemList get(Context context) {
        if (singleton == null) {
            singleton = new ItemList();
        }

        return singleton;
    }

    private void load() {
        Item[] items = {
                new Item("4.4", "Kit Kat"),
                new Item("5.0", "Lolipop"),
                new Item("6.0", "Mashmallow")};

        list = Arrays.asList(items);
    }

    public static Item get(int location) {
        return list.get(location);
    }

    public static List<Item> getList() {
        return list;
    }
}
