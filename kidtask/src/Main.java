import java.io.File;

public class Main {
    public static void main(String[] args) {

        // 👉 Çalışma dizinini yazdıralım
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        System.out.println("Commands.txt exists? " + new File("Commands.txt").exists());
        System.out.println("Tasks.txt exists? " + new File("Tasks.txt").exists());
        System.out.println("Wishes.txt exists? " + new File("Wishes.txt").exists());

        // 1. Kullanıcıları oluştur
        Child child = new Child("Zeynep");
        Parent parent = new Parent("Ali");
        Teacher teacher = new Teacher("Ayşe Öğretmen");

        // 2. Dosyaları yönetecek FileManager
        FileManager fileManager = new FileManager();

        // 3. Başlangıçta kayıtlı görev ve dilekleri yükle
        fileManager.loadTasks("Tasks.txt", child);
        fileManager.loadWishes("Wishes.txt", child);

        // 4. Komutları işle
        CommandProcessor processor = new CommandProcessor(child, parent, teacher);
        processor.processCommands("Commands.txt");

        // 5. Çıkışta görevleri ve dilekleri tekrar kaydet
        fileManager.saveTasks(child.getTasks(), "Tasks.txt");
        fileManager.saveWishes(child.getWishes(), "Wishes.txt");

        System.out.println("Program finished and data saved.");
    }
}
