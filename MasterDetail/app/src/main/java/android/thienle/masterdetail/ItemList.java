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
    private List<Item> list = new ArrayList<>();

    public ItemList(Context context) {
        load(context);
    }

    //  Singleton design pattern
    public static ItemList get(Context context) {
        if (singleton == null) {
            singleton = new ItemList(context);
        }

        return singleton;
    }

    private void load(Context context) {
        Item[] items = {
                new Item("4.4", "Kit Kat"),
                new Item("5.0", "Lolipop"),
                new Item("6.0", "Mashmallow")};

        list = Arrays.asList(items);
    }

    public Item get(int location) {
        return list.get(location);
    }

    public List<Item> getList() {
        return list;
    }
}
