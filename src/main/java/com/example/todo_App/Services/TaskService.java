package com.example.todo_App.Services;

import com.example.todo_App.Model.Task;
import com.example.todo_App.Repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo repo;

    public ResponseEntity<?> addTask(Task task) {
        return new ResponseEntity<>(repo.save(task), HttpStatus.CREATED);
    }

    public List<Task> getTask() {
        return repo.findAll();
    }

    public ResponseEntity<?> updateTask(Integer id, Task newTask) {
        Task task = repo.findById(id).orElse(new Task());
        task.setDescription(newTask.getDescription());
        task.setTitle(newTask.getTitle());
        task.setIsCompleted(newTask.getIsCompleted());
        return new ResponseEntity<>(repo.save(task),HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTask(Integer id) {
         repo.deleteById(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Task> search(String keywords,Integer id) {

        if(id!=null) {
            System.out.println("Returning from here");
            return repo.findById(id).stream().toList();
        }
        return  repo.findByTitleContainingOrDescriptionContaining(keywords,keywords);
    }
}
