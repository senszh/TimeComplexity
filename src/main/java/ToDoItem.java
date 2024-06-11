
    public class ToDoItem {
        public int id;
        public String description;
        public boolean isDone;

        public ToDoItem() {
        }

        public ToDoItem(int id, String description, boolean isDone) {
            this.id = id;
            this.description = description;
            this.isDone = isDone;
        }
        public ToDoItem(String description) {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isDone() {
            return isDone;
        }

        public void setDone(boolean isDone) {
            this.isDone = isDone;
        }
    }


