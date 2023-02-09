package com.codestates.mapper;

import com.codestates.dto.TodoDto;
import com.codestates.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    //Member memberPostDtoToMember(MemberDto.Post requestBody);
    Todo todoPostToTodo(TodoDto.Post requestBody);

    Todo todoPatchToTodo(TodoDto.Patch requestBody);

    TodoDto.response todoToTodoResponse(Todo todo);
    List<TodoDto.response> todoesToTodoResponse(List<Todo> todoes);
}
