package com.todolist.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.todolist.Model.Todo;
import com.todolist.Service.TodoService;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/deleteTodo/{id}")
    public String deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }

    @GetMapping("/toggleTodo/{id}")
    public String toggleTodoStatus(@PathVariable Long id) {
        Todo todo = todoService.getAllTodos().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (todo != null) {
            todoService.updateTodoStatus(id, !todo.isCompleted());
        }
        return "redirect:/";
    }
}

