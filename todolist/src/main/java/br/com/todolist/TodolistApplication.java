package br.com.todolist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@Bean
	CommandLineRunner criarTarefasFake(ToDoRepository toDoRepository) {
		return args -> {
			toDoRepository.deleteAll();
			ToDo todo1 = new ToDo("Configurar JDK", "Preciso aprender a configurar o JDK corretamente",
					StatusEnum.NOT_STARTED);
			ToDo todo2 = new ToDo ("Estudar Java", "Estudar Java para aprender programação",
					StatusEnum.IN_PROGRESS);
			ToDo todo3 = new ToDo ("Estudar HTML", "Estudar HTML para desenvolver sites", StatusEnum.FINISHED);
			toDoRepository.save(todo1);
			toDoRepository.save(todo2);
			toDoRepository.save(todo3);
		};
	}
}
