import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        System.out.print("Input Data: ");
        String inputData = in.nextLine();
        in.close();*/
        String inputData = "10.03.2000 89245676543 m Ivanov Ivan Ivanovich";

        //String[] list = inputData.split(" ");
        List<String> list = new ArrayList<>(Arrays.asList(inputData.split(" ")));
        list.remove("");

        for (String elem:list
             ) {
            System.out.println(elem);
        }
        System.out.println(list.size());
        if (list.size() == 6) {
            try {
                String gender = findGender(list);
                list.remove(gender);
                Long phone = findPhone(list);
                list.remove(String.valueOf(phone));
                String birthdayDate = findDate(list);
                list.remove(String.valueOf(birthdayDate));
                String family = list.get(0);
                String name = list.get(1);
                String patronymic = list.get(2);
                Person person = new Person(family, name, patronymic, birthdayDate, phone, gender);
                System.out.println(person);
                PersonWriter.writeFile(person);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            if (list.size() > 6) {throw new RuntimeException("Вы ввели больше данных");}
            else {throw new RuntimeException("Вы ввели меньше данных");}
        }

    }
    
    public static String findGender(List<String> list) throws RuntimeException {
      int count = 0;
      boolean flag = false;
      String result = null;
      for (String elem: list) {
          if (elem.equals("f") || elem.equals("m")) {
              if (count == 0) {
                  result = elem;
                  count++;
                  flag = true;
              } else {
                  flag = false;
                  break;
              }
          }
      }
      if (flag) {
          return result;}
      else {throw new RuntimeException("Неправильно указан пол");}
    };

    public static Long findPhone(List<String> list) throws RuntimeException {
        int count = 0;
        boolean flag = false;
        Long result = 0L;
        for (String elem: list) {
            if (isNumeric(elem)) {
                if (count == 0) {
                    result = Long.parseLong(elem);
                    count++;
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            return result;}
        else {throw new RuntimeException("Неправильно указан телефон");}
    };

    public static String findDate(List<String> list) throws RuntimeException {
        int count = 0;
        boolean flag = false;
        String result = null;
        for (String elem: list) {
            if (isDate(elem)) {
                if (count == 0) {
                    result = elem;
                    count++;
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            return result;}
        else {throw new RuntimeException("Неправильно указана дата рождения");}
    };
    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isDate(String str) {

        List<String> listDate = new ArrayList<>(Arrays.asList(str.split(Pattern.quote("."))));
        if (listDate.size() == 3) {

            if (isNumeric(listDate.get(0)) && Integer.parseInt(listDate.get(0)) >= 0 && Integer.parseInt(listDate.get(0)) <= 31 && listDate.get(0).length() == 2) {
                if (isNumeric(listDate.get(1)) && Integer.parseInt(listDate.get(1)) >= 0 && Integer.parseInt(listDate.get(1)) <= 12 && listDate.get(1).length() == 2) {
                    if (isNumeric(listDate.get(2)) && Integer.parseInt(listDate.get(2)) >= 1920 && Integer.parseInt(listDate.get(2)) <= 2023) {
                        return true;
                    } else {return false;}
                } else {return false;}
            } else {return false;}
        } else {return false;}

    }
}
