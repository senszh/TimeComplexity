import java.util.List;
import java.util.Scanner;

public class ToDoApp {
    private static long bestTime = Long.MAX_VALUE;
    private static long worstTime = Long.MIN_VALUE;

    public static void main(String[] args) {
        ToDoManager manager = new ToDoManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Görev Ekle");
            System.out.println("2. Görevi Güncelle");
            System.out.println("3. Görevi Sil");
            System.out.println("4. Görevleri Listele");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            int secenek = scanner.nextInt();
            scanner.nextLine(); // Buffer'ı temizle

            long startTime = System.nanoTime();

            switch (secenek) {
                case 1:
                    System.out.print("Görev Açıklaması: ");
                    String description = scanner.nextLine();
                    ToDoItem newItem = new ToDoItem();
                    newItem.setId(manager.getToDoList().size() + 1);
                    newItem.setDescription(description);
                    newItem.setDone(false);
                    manager.addToDoItem(newItem);
                    System.out.println("Görev eklendi: " + newItem.getDescription());
                    break;
                case 2:
                    System.out.print("Güncellenecek To-Do ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Buffer'ı temizle
                    System.out.print("Yeni Açıklama: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Tamamlandı mı? (true/false): ");
                    boolean isDone = scanner.nextBoolean();
                    scanner.nextLine(); // Buffer'ı temizle
                    ToDoItem updateItem = new ToDoItem();
                    updateItem.setId(updateId);
                    updateItem.setDescription(newDescription);
                    updateItem.setDone(isDone);
                    manager.updateToDoItem(updateItem);
                    System.out.println("Görev güncellendi: " + updateItem.getDescription());
                    break;
                case 3:
                    System.out.print("Silinecek To-Do ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Buffer'ı temizle
                    manager.removeToDoItem(deleteId);
                    System.out.println("Görev silindi: ID " + deleteId);
                    break;
                case 4:
                    List<ToDoItem> toDoList = manager.getToDoList();
                    System.out.println("Görev Listesi:");
                    for (ToDoItem item : toDoList) {
                        System.out.println(item.getId() + ": " + item.getDescription() + " (Tamamlanma Durumu: " + item.isDone() + ")");
                    }
                    break;
                case 5:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçim!");
            }

            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            //Eğer işlem süresi en iyi süreden küçükse, en iyi süre güncellenir.
            if (duration < bestTime) {
                bestTime = duration;
            }
            //Eğer işlem süresi en kötü süreden büyükse, en kötü süre güncellenir.
            if (duration > worstTime) {
                worstTime = duration;
            }

            System.out.println("İşlem süresi: " + duration + " ns");
            System.out.println("En iyi süre: " + bestTime + " ns");
            System.out.println("En kötü süre: " + worstTime + " ns");
        }
    }
}
