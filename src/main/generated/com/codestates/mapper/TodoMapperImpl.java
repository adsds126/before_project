package com.codestates.mapper;

import com.codestates.dto.TodoDto.Patch;
import com.codestates.dto.TodoDto.Post;
import com.codestates.entity.Todo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-08T15:23:06+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo todoPostDtotoTodo(Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        return todo;
    }

    @Override
    public Todo todoPatchtoTodo(Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        return todo;
    }
}
