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
    public ToDo create(@RequestBody ToDo tarefa){
        return this.todoRepo.save(tarefa);
    }

    @DeleteMapping("/{tarefaId}")
    public ResponseEntity<Void> delete(@PathVariable Integer tarefaId) {
        Optional<ToDo> toDo = this.todoRepo.findById(tarefaId);
        if (toDo.isPresent()) {
            this.todoRepo.deleteById(tarefaId);
            return ResponseEntity.noContent().build();
        } else {
            return  ResponseEntity.notFound().build();
        }
    }
}


