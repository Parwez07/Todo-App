package com.example.todo_App.Repo;

import com.example.todo_App.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Integer> {

    List<Task> findByTitleContainingOrDescriptionContaining(String keywords, String keywords1);
}
