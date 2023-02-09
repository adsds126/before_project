package com.codestates.controller;

import com.codestates.dto.SingleResponseDto;
import com.codestates.dto.TodoDto;
import com.codestates.entity.Todo;
import com.codestates.mapper.TodoMapper;
import com.codestates.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Validated
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper){
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }
    @PostMapping
    public ResponseEntity postToDo(@Valid @RequestBody TodoDto.Post requestBody){
        Todo todo = todoMapper.todoPostToTodo(requestBody);

        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity patchToDo(@PathVariable("id")@Positive int id,
                                    @Valid @RequestBody TodoDto.Patch requestBody){
//        requestBody.setId(id);
//        Todo todo = new Todo();
//        todo.setId(requestBody.getId());
//        todo.setTitle(requestBody.getTitle());
//        todo.setTodo_order(requestBody.getTodo_order());
//        todo.setCompleted(requestBody.isCompleted());
//
//        Todo response = todoService.updateTodo(todo);
        requestBody.setId(id);

        Todo todo =
                todoService.updateTodo(todoMapper.todoPatchToTodo(requestBody));
        return new ResponseEntity<>(
                new SingleResponseDto<>(todoMapper.todoToTodoResponse(todo)),
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getToDo(@PathVariable("id") int id){
        Todo response = todoService.findTodo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getTodoes(){
        List<Todo> response = todoService.findTodoes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") int id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity deleteTodoes(){
        todoService.deleteTodoes();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
