import java.util.Scanner;

    public class ToDoApp2 {
        private static final Scanner scanner = new Scanner(System.in);
        private static final ToDoManager2 toDoManager2 = new ToDoManager2();


        public static void main(String[] args) {
            boolean exit = false;

            while (!exit) {
                System.out.println("\n1. Görev Ekle");
                System.out.println("2. Görevi Güncelle");
                System.out.println("3. Görevi Sil");
                System.out.println("4. Görevleri Listele");
                System.out.println("5. Çıkış");
                System.out.print("Seçiminizi yapın: ");

                int secenek2 = scanner.nextInt();
                scanner.nextLine(); // Boş satırı oku

                switch (secenek2) {
                    case 1:
                        addToDoItem();
                        break;
                    case 2:
                        updateToDoItem();
                        break;
                    case 3:
                        removeToDoItem();
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Seçiminizi yapın: ");
                }
            }
        }

        private static void addToDoItem() {
            System.out.print("Görev Açıklaması: ");
            String description = scanner.nextLine();
            ToDoItem item = new ToDoItem(description);
            toDoManager2.addToDoItem(item);
        }

        private static void updateToDoItem() {
            System.out.print("Güncellenecek ToDo ID : ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Boş satırı oku

            System.out.print("Yeni Açıklama: ");
            String description = scanner.nextLine();
            System.out.print("Tamamlandı mı? (true/false): ");
            boolean isDone = scanner.nextBoolean();

            ToDoItem item = new ToDoItem(id, description, isDone);
            toDoManager2.updateToDoItem(item);
        }

        private static void removeToDoItem() {
            System.out.print("Silinecek ToDo ID : ");
            int id = scanner.nextInt();
            toDoManager2.removeToDoItem(id);
        }

    }
