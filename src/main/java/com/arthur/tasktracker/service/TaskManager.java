package com.arthur.tasktracker.service;

import com.arthur.tasktracker.model.Task;

import java.util.ArrayList;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public Task createTask(String title, String description) {
        Task createTask = new Task(nextId, title, description);
        tasks.add(createTask);
        nextId = nextId + 1;
        return createTask;
    }

    public void saveTasks() throws IOException {
        Properties dados = new Properties();

        dados.setProperty("total", String.valueOf(tasks.size()));
        dados.setProperty("nextId", String.valueOf(nextId));

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String prefixo = "task." + i + ".";

            dados.setProperty(prefixo + "id", String.valueOf(task.getId()));
            dados.setProperty(prefixo + "title", task.getTitle());
            dados.setProperty(prefixo + "description", task.getDescription());
            dados.setProperty(prefixo + "completed", String.valueOf(task.isCompleted()));
        }

        Path arquivo = Path.of("tarefas.properties");

        try (Writer writer = Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8)) {
            dados.store(writer, "Tarefas do Task Tracker");
        }

    }

    public void loadTasks () throws IOException {
        Path arquivo = Path.of("tarefas.properties");

        if (Files.notExists(arquivo)) {
            return;
        }

        Properties dados = new Properties();

        try (Reader reader = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            dados.load(reader);
        } catch (IllegalArgumentException erro) {
            throw new IOException("Arquivo de tarefas inválido.", erro);
        }

        ArrayList<Task> tarefasCarregadas = new ArrayList<>();

        try {
            int total = Integer.parseInt(dados.getProperty("total"));
            int proximoId = Integer.parseInt(dados.getProperty("nextId"));

            if (total < 0 || proximoId < 1) {
                throw new IllegalArgumentException("Contagem ou próximo ID inválido.");
            }

            int maiorId = 0;

            for (int i = 0; i < total; i++) {
                String prefixo = "task." + i + ".";

                int id = Integer.parseInt(dados.getProperty(prefixo + "id"));
                String title = dados.getProperty(prefixo + "title");
                String description = dados.getProperty(prefixo + "description");
                String completed = dados.getProperty(prefixo + "completed");

                if (id < 1 || description == null || (!"true".equals(completed) && !"false".equals(completed))) {
                    throw new IllegalArgumentException("Dados de tarefa inválidos.");
                }

                for (Task existente : tarefasCarregadas) {
                    if (existente.getId() == id) {
                        throw new IllegalArgumentException("ID repetido no arquivo.");
                    }
                }

                Task task = new Task(id, title, description);

                if (Boolean.parseBoolean(completed)) {
                    task.markAsCompleted();
                }

                tarefasCarregadas.add(task);
                maiorId = Math.max(maiorId, id);
            }

            if (proximoId <= maiorId) {
                throw new IllegalArgumentException("Próximo ID inválido.");
            }

            tasks = tarefasCarregadas;
            nextId = proximoId;
        } catch (IllegalArgumentException erro) {
            throw new IOException("Arquivo de tarefas inválido.", erro);
        }
    }

    public Task findTaskById(int idToFind) {
        for (Task task : tasks) {
            if (task.getId() == idToFind) {
                return task;
            }
        }
        return null;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    private int nextId = 1;

    public boolean deleteTaskById(int idToDelete) {
        Task taskToDelete = findTaskById(idToDelete);
        if (taskToDelete == null) {
            return false;
        }

        tasks.remove(taskToDelete);
        return true;
    }
}
