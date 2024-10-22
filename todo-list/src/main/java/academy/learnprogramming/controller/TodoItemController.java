package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import academy.learnprogramming.service.TodoItemServiceInterface;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
public class TodoItemController {

    // == fields ==
    private final TodoItemServiceInterface todoItemServiceInterface;

    // == constructor ==
    @Autowired
    public TodoItemController(TodoItemServiceInterface todoItemServiceInterface) {
        this.todoItemServiceInterface = todoItemServiceInterface;
    }


    // == model attributes ==
    @ModelAttribute
    public TodoData todoData(){
        /* return new TodoData();  */
        // call service methods to get todoData as a model attribute instead of returning as above
        return todoItemServiceInterface.getData();
    }

    // == handler methods ==

    // Map to this url - http://localhost:8080/todo-list/items
    @GetMapping(Mappings.ITEMS)
    public String items(){
        return ViewNames.ITEMS_LIST;
    }

    // display form
    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id,
                              Model model){
        log.info("editing id = {}", id);
        TodoItem todoItem = todoItemServiceInterface.getItem(id);
        if (todoItem == null){
             todoItem = new TodoItem("","", LocalDate.now());
        }
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem){
        log.info("todoItem from form = {}",todoItem);
        if (todoItem.getId() == 0){
            todoItemServiceInterface.addItem(todoItem);
        }else{
            todoItemServiceInterface.updateItem(todoItem);
        }
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id , Model model){
        TodoItem todoItem = todoItemServiceInterface.getItem(id);
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.VIEW_ITEM;
    }

    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id){
        log.info("Deleting item with id = {}",id);
        todoItemServiceInterface.removeItem(id);
        return "redirect:/"+ Mappings.ITEMS;
    }


}
