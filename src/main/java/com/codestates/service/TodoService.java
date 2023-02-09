package com.codestates.service;

import com.codestates.entity.Todo;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.repository.TodoRepository;
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
    private final ApplicationEventPublisher publisher;

    public TodoService(TodoRepository todoRepository, ApplicationEventPublisher publisher) {
        this.todoRepository = todoRepository;
        this.publisher = publisher;
    }

    public Todo createTodo(Todo todo){
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Todo updateTodo(Todo todo){
        Todo updateTodo = todo;
        return updateTodo;
    }
    public Todo findTodo(int id){
        return findVerifiedTodo(id);
    }
    public List<Todo> findTodoes(){
        return todoRepository.findAll();
    }
    public void deleteTodo(int id){
        Todo findTodo = findVerifiedTodo(id);

        todoRepository.delete(findTodo);
    }
    public void deleteTodos(){
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
