import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {
    private static final String FILE_NAME = "todos.json";
    private List<ToDoItem> toDoList;
    private ObjectMapper objectMapper;

    public ToDoManager() {
        objectMapper = new ObjectMapper();
        toDoList = new ArrayList<>();
        loadToDos();
    }

    private void loadToDos() {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                toDoList = objectMapper.readValue(file, new TypeReference<List<ToDoItem>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToDos() {
        try {
            objectMapper.writeValue(new File(FILE_NAME), toDoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToDoItem(ToDoItem item) {
        long startTime = System.nanoTime();
        toDoList.add(item); //
        saveToDos();
        long endTime = System.nanoTime();
        System.out.println("addToDoItem (Best Case) Execution Time: " + (endTime - startTime) + " ns");

        // En kötü durumu simüle etmek için listenin başına eklenip yeniden kaydediliyor
        startTime = System.nanoTime();
        toDoList.add(0, item); // Görev listenin başına ekleniyor
        saveToDos();
        endTime = System.nanoTime();
        System.out.println("addToDoItem (Worst Case) Execution Time: " + (endTime - startTime) + " ns");
    }

    public void updateToDoItem(ToDoItem item) {
        long startTime = System.nanoTime();
        for (ToDoItem toDo : toDoList) {
            if (toDo.getId() == item.getId()) {
                toDo.setDescription(item.getDescription());
                toDo.setDone(item.isDone());
                break;
            }
        }
        saveToDos();
        long endTime = System.nanoTime();
        System.out.println("updateToDoItem (Best Case) Execution Time: " + (endTime - startTime) + " ns");

        // En kötü durumu simüle etmek için listenin sonundaki görev güncellenip yeniden kaydediliyor
        startTime = System.nanoTime();
        for (int i = toDoList.size() - 1; i >= 0; i--) {
            ToDoItem toDo = toDoList.get(i);
            if (toDo.getId() == item.getId()) {
                toDo.setDescription(item.getDescription());
                toDo.setDone(item.isDone());
                break;
            }
        }
        saveToDos();
        endTime = System.nanoTime();
        System.out.println("updateToDoItem (Worst Case) Execution Time: " + (endTime - startTime) + " ns");
    }

    public void removeToDoItem(int id) {
        long startTime = System.nanoTime();
        toDoList.removeIf(toDo -> toDo.getId() == id);
        saveToDos();
        long endTime = System.nanoTime();
        long execution_time=endTime - startTime;
        System.out.println("removeToDoItem (Best Case) Execution Time: " + (execution_time) + " ns");

        // En kötü durumu simüle etmek için listenin sonundaki görev kaldırılıp yeniden kaydediliyor
        startTime = System.nanoTime();
        if (!toDoList.isEmpty()) {
            toDoList.remove(toDoList.size() - 1);
            saveToDos();
        }
        endTime = System.nanoTime();
        System.out.println("removeToDoItem (Worst Case) Execution Time: " + (endTime - startTime) + " ns");
    }

    public List<ToDoItem> getToDoList() {
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        System.out.println("getToDoList Execution Time: " + (endTime - startTime) + " ns");
        return toDoList;
    }
}
