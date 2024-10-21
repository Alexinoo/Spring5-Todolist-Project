package academy.learnprogramming.service;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class TodoItemServiceImpl implements TodoItemServiceInterface{

    @Getter
    private final TodoData data = new TodoData();
    @Override
    public void addItem(TodoItem newTodo) {
        data.addItem(newTodo);
    }

    @Override
    public void removeItem(int id) {
        data.removeItem(id);
    }

    @Override
    public TodoItem getItem(int id) {
        return data.getItem(id);
    }

    @Override
    public void updateItem(TodoItem itemToUpdate) {
        data.updateItem(itemToUpdate);
    }

    /* Added @Getter to this definition : TodoData data = new TodoData();
     * We don't need this definition anymore
     * Since we've used TodoData data variable, it's going to create getData() for us automatically
     *
        @Override
        public TodoData getData() {
            return new TodoData();
        }
    */
}
