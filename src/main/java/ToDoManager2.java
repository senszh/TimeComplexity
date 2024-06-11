import com.fasterxml.jackson.databind.ObjectMapper; // JSON işlemleri için ObjectMapper nesnesi
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ToDoManager2 {
    private static final String FILE_NAME2 = "todos2.json";
    private LinkedList<ToDoItem> toDoList;
    private ObjectMapper objectMapper;
    public long bestTime2 = Long.MAX_VALUE; // En iyi süre başlangıçta maksimum uzun süre olarak ayarlanıyor
    public long worstTime2 = Long.MIN_VALUE; // En iyi süre başlangıçta minimum uzun süre olarak ayarlanıyor

    public ToDoManager2() {
        objectMapper = new ObjectMapper();
        toDoList = new LinkedList<>();
        loadToDos();
    }

    private void loadToDos() {
        try {
            File file = new File(FILE_NAME2);
            if (file.exists()) {
                long startTime = System.nanoTime(); // Başlangıç zamanı alınıyor
                toDoList = objectMapper.readValue(file, new TypeReference<LinkedList<ToDoItem>>() {});
                long endTime = System.nanoTime(); // Bitiş zamanı alınıyor
                reportTime(endTime - startTime, "loadToDos"); // İşlem süresi raporlanıyor
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToDos() {
        try {
            long startTime = System.nanoTime();
            objectMapper.writeValue(new File(FILE_NAME2), toDoList);
            long endTime = System.nanoTime();
            reportTime(endTime - startTime, "saveToDos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToDoItem(ToDoItem item) {
        long startTime = System.nanoTime();
        toDoList.add(item);
        saveToDos();
        long endTime = System.nanoTime();
        reportTime(endTime - startTime, "addToDoItem");
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
        reportTime(endTime - startTime, "updateToDoItem");
    }

    public void removeToDoItem(int id) {
        long startTime = System.nanoTime();
        toDoList.removeIf(toDo -> toDo.getId() == id);
        saveToDos();
        long endTime = System.nanoTime();
        reportTime(endTime - startTime, "removeToDoItem");
    }

    public List<ToDoItem> getToDoList() {
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        reportTime(endTime - startTime, "getToDoList");
        return toDoList;
    }

    private void reportTime(long duration, String operation) {
        if (duration < bestTime2) {
            bestTime2 = duration;
        }
        if (duration > worstTime2) {
            worstTime2 = duration;
        }

        System.out.println(operation + " Execution Time: " + duration + " ns");
        System.out.println("En iyi süre: " + bestTime2 + " ns");
        System.out.println("En kötü süre: " + worstTime2 + " ns");
    }
}
