package com.arthur.tasktracker.main;
import com.arthur.tasktracker.model.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task Tracker iniciado!");
        ArrayList<Task> tasks = new ArrayList<>();



        try {
            Task task1 = new Task("Estudar Java", "Estudar os conceitos básicos de Java");
            tasks.add(task1);
            System.out.println(tasks.size());

            System.out.println(task1.getTitle());
            System.out.println(task1.getDescription());
            System.out.println(task1.isCompleted());
            // task1.markAsCompleted();
            if (task1.isCompleted()) {
                System.out.println("A tarefa foi concluída!");
            } else {
                System.out.println("A tarefa não foi concluída!");
            }
            System.out.println(task1.isCompleted());
        } catch (IllegalArgumentException erro) {
            System.out.println(erro.getMessage());
        }




        int quantidadeDeTarefas = 2;

        if  (quantidadeDeTarefas == 0) {
            System.out.println("Nenhuma tarefa cadastrada!");
        } else {
            System.out.println("Existem tarefas cadastradas!");
        }


        String titulo ="   ";

        if (titulo.isBlank()) {
            System.out.println("título vazio!");
        } else {
            System.out.println("O título preenchido ");
        }

    }
}
