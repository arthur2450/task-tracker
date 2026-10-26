package com.arthur.tasktracker.main;

import com.arthur.tasktracker.model.Task;
import com.arthur.tasktracker.service.TaskManager;

import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("       JAVA TASK TRACKER");
        System.out.println("================================");
        System.out.println("Bem-vindo ao seu gerenciador de tarefas!");

        TaskManager manager = new TaskManager();

        try {
            manager.loadTasks();
        } catch (IOException erro) {
            System.out.println("Não foi possível carregar: " + erro.getMessage());
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            boolean executando = true;

            while (executando) {
                System.out.println();
                System.out.println("1 - Cadastrar tarefa");
                System.out.println("2 - Listar tarefas");
                System.out.println("3 - Concluir tarefa");
                System.out.println("4 - Excluir tarefa");
                System.out.println("5 - Editar tarefa");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                if (!scanner.hasNextLine()) {
                    break;
                }
                String opcao = scanner.nextLine().trim();

                switch (opcao) {
                    case "1":
                        System.out.print("Digite o título da tarefa: ");
                        String title = scanner.nextLine();
                        System.out.print("Digite a descrição da tarefa: ");
                        String description = scanner.nextLine();

                        try {
                            manager.createTask(title, description);
                            System.out.println("Tarefa cadastrada com sucesso!");
                        } catch (IllegalArgumentException erro) {
                            System.out.println(erro.getMessage());
                        }
                        break;

                    case "2":
                        System.out.println();
                        System.out.println("TAREFAS CADASTRADAS");
                        System.out.println("--------------------------------");

                        if (manager.getTaskCount() == 0) {
                            System.out.println("Nenhuma tarefa cadastrada!");
                        } else {
                            for (Task task : manager.getTasks()) {
                                System.out.println("ID: " + task.getId());
                                System.out.println("Título: " + task.getTitle());
                                System.out.println("Descrição: " + task.getDescription());
                                if (task.isCompleted()) {
                                    System.out.println("Status: Concluída");
                                } else {
                                    System.out.println("Status: Pendente");
                                }
                                System.out.println("--------------------------------");
                            }
                            System.out.println("Total de tarefas: " + manager.getTaskCount());
                        }
                        break;

                    case "3":
                        System.out.print("Digite o ID da tarefa: ");

                        try {
                            int id = Integer.parseInt(scanner.nextLine().trim());
                            Task tarefa = manager.findTaskById(id);

                            if (tarefa == null) {
                                System.out.println("Tarefa não encontrada.");
                            } else if (tarefa.isCompleted()) {
                                System.out.println("Essa tarefa já está concluída.");
                            } else {
                                tarefa.markAsCompleted();
                                System.out.println("Tarefa concluída com sucesso!");
                            }
                        } catch (NumberFormatException erro) {
                            System.out.println("ID inválido. Digite um número inteiro.");
                        }
                        break;

                    case "4":
                        System.out.print("Digite o ID da tarefa que deseja excluir: ");

                        try {
                            int id = Integer.parseInt(scanner.nextLine().trim());
                            boolean excluida = manager.deleteTaskById(id);

                            if (excluida) {
                                System.out.println("Tarefa excluída com sucesso!");
                            } else {
                                System.out.println("Tarefa não encontrada.");
                            }
                        } catch (NumberFormatException erro) {
                            System.out.println("ID inválido. Digite um número inteiro.");
                        }
                        break;

                    case "5":
                        System.out.print("Digite o ID da tarefa que deseja editar: ");

                        try {
                            int id = Integer.parseInt(scanner.nextLine().trim());
                            Task tarefa = manager.findTaskById(id);

                            if (tarefa == null) {
                                System.out.println("Tarefa não encontrada.");
                            } else {
                                System.out.println("Título atual: " + tarefa.getTitle());
                                System.out.print("Digite o novo título: ");
                                String novoTitulo = scanner.nextLine();

                                System.out.println("Descrição atual: " + tarefa.getDescription());
                                System.out.print("Digite a nova descrição: ");
                                String novaDescricao = scanner.nextLine();

                                tarefa.updateTitle(novoTitulo);
                                tarefa.updateDescription(novaDescricao);

                                System.out.println("Tarefa atualizada com sucesso!");
                            }
                        } catch (NumberFormatException erro) {
                            System.out.println("ID inválido. Digite um número inteiro.");
                        } catch (IllegalArgumentException erro) {
                            System.out.println(erro.getMessage());
                        }
                        break;

                    case "0":
                        try {
                            manager.saveTasks();
                            executando = false;
                            System.out.println("Tarefas salvas. Até logo!");
                        } catch (IOException erro) {
                            System.out.println("Não foi possível salvar: " + erro.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Opção inválida. Digite 1, 2, 3, 4, 5 ou 0.");
                        break;
                }
            }
        }
    }
}
