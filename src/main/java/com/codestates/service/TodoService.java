package com.codestates.service;

import com.codestates.entity.Todo;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TodoService {
    private final TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository, ApplicationEventPublisher publisher) {
        this.todoRepository = todoRepository;

    }

    public Todo createTodo(Todo todo){
//        Todo savedTodo = todoRepository.save(todo);
//        return savedTodo;
        return todoRepository.save(todo);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Todo updateTodo(Todo todo){
        Todo findTodo = findVerifiedTodo(todo.getId());
        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodo_order())
                .ifPresent(todo_order -> findTodo.setTodo_order(todo_order));
        Optional.ofNullable(todo.isCompleted())
                .ifPresent(completed -> findTodo.setCompleted(completed));

        return todoRepository.save(findTodo);
    }
    public Todo findTodo(int id){
        return findVerifiedTodo(id);
    }
    public List<Todo> findTodoes(){
        return (List<Todo>) todoRepository.findAll();
    }
    public void deleteTodo(int id){
        Todo findTodo = findVerifiedTodo(id);
    //아
        //깃 푸시 왜안돼
        //?
        todoRepository.delete(findTodo);
    }
    public void deleteTodoes(){
        todoRepository.deleteAll();
    }
    @Transactional(readOnly = true)
    public Todo findVerifiedTodo(int id) {
        Optional<Todo> optionalTodo =
                todoRepository.findById((long) id);
        return optionalTodo.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}
