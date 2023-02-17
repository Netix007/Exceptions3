import java.io.FileWriter;
import java.io.IOException;

public class PersonWriter {
    public PersonWriter() {
    }

    public void writeFile(Person person) {
        String fileName = person.getFamily() + ".txt";
        try(FileWriter writer = new FileWriter(fileName, true))
        {
            writer.write(String.format("%s%n",person));
            System.out.println("данные успешно записаны в файл");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            e.printStackTrace();
        }
    }
}
