package academy.learnprogramming.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class TodoData {
    // == fields ==
    private static int idValue = 1;
    private final List<TodoItem> items = new ArrayList<>();

    // constructor
    public TodoData() {
        // add some dummy data , using the addItem method so that it sets the id field
        addItem(new TodoItem("first","first details", LocalDate.now()));
        addItem(new TodoItem("second","second details", LocalDate.now()));
        addItem(new TodoItem("third","third details", LocalDate.now()));
        addItem(new TodoItem("fourth","third details", LocalDate.now()));
    }

    // public methods
    public List<TodoItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public void addItem(@NonNull TodoItem newItem){
        /*
         * Replaced by @NonNull lombok annotation - annotating the parameters for this method
            if (newItem == null)
                throw new NullPointerException("new item can not be null");
         */
        newItem.setId(idValue);
        items.add(newItem);
        idValue++;
    }

    public void removeItem(int id){
        ListIterator<TodoItem> itemIterator = items.listIterator();
        while (itemIterator.hasNext()){
            TodoItem item = itemIterator.next();
            if (item.getId() == id) {
                itemIterator.remove();
                break;
            }
        }
    }

    public TodoItem getItem(int id){
        for (TodoItem item : items){
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    public void updateItem(@NonNull TodoItem itemToUpdate){
        ListIterator<TodoItem> itemIterator =   items.listIterator();
        while (itemIterator.hasNext()){
            TodoItem item = itemIterator.next();
            if (item.equals(itemToUpdate)){
                itemIterator.set(itemToUpdate);
                break;
            }
        }
    }
}
