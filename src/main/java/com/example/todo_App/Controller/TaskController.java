package com.example.todo_App.Controller;

import com.example.todo_App.Model.Task;
import com.example.todo_App.Services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    TaskService service ;

    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody Task task){
        return  new ResponseEntity<>(service.addTask(task), HttpStatus.CREATED);
    }
    @GetMapping("/task")
    public ResponseEntity<?> getTask(){
        return new ResponseEntity<>(service.getTask(),HttpStatus.OK);
    }

    @GetMapping("/task/search")
    public ResponseEntity<?> searchByKeyword(
            @RequestParam(required = false) String keywords,@RequestParam(value = "id",required = false) Integer id)
    {
        if (id==null && keywords==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.search(keywords,id),HttpStatus.OK);
    }
    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Integer id, @RequestBody Task task){
        return new ResponseEntity<>(service.updateTask(id,task),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id){
        return  new ResponseEntity<>(service.deleteTask(id),HttpStatus.OK);
    }
}
