package com.codestates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;



public class TodoDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String title;
        private int todo_order;
        private boolean completed;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private int id;
        private String title;
        private int todo_order;
        private boolean completed;

        public void setId(int id){
            this.id = id;
        }
    }
}
