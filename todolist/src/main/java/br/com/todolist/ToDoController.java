package br.com.todolist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.TabbedPaneUI;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class ToDoController {
    private final ToDoRepository todoRepo;

    public ToDoController(ToDoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ToDo> getAll() {
        return this.todoRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ToDo create(@RequestBody ToDo tarefa) {
        return this.todoRepo.save(tarefa);
    }

//    @DeleteMapping("/{tarefaId}")
//    public ResponseEntity<Void> delete(@PathVariable Integer tarefaId) {
//        Optional<ToDo> toDo = this.todoRepo.findById(tarefaId);
//        if (toDo.isPresent()) {
//            this.todoRepo.deleteById(tarefaId);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }

    @PutMapping("/{todoId}/start_task")
    public ResponseEntity<ToDo> startTask(@PathVariable Integer todoId) {
        ToDo ToDoDatabase = this.todoRepo.findById(todoId).get();
        if (ToDoDatabase != null) {
            ToDoDatabase.setStatus(StatusEnum.IN_PROGRESS);
            this.todoRepo.save(ToDoDatabase);
            return ResponseEntity.ok(ToDoDatabase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{todoId}/end_task")
    public ResponseEntity<ToDo> endTask(@PathVariable Integer todoId) {
        ToDo ToDoDatabase = this.todoRepo.findById(todoId).get();
        if (ToDoDatabase != null) {
            ToDoDatabase.setStatus(StatusEnum.FINISHED);
            this.todoRepo.save(ToDoDatabase);
            return ResponseEntity.ok(ToDoDatabase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{todoId}")
    public ResponseEntity<ToDo> update(@PathVariable Integer todoId, @RequestBody ToDo toDo) {
        ToDo ToDoDatabase = this.todoRepo.findById(todoId).get();
        if (ToDoDatabase != null) {
            ToDoDatabase.setTitle(toDo.getTitle());
            ToDoDatabase.setDescription(toDo.getDescription());
            this.todoRepo.save(ToDoDatabase);
            return ResponseEntity.ok(ToDoDatabase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}




