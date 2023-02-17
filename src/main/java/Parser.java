import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {

    private final PersonWriter personWriter;

    public Parser() {
        this.personWriter = new PersonWriter();
    }

    public void parseString(String str) throws MyException{

        List<String> list = new ArrayList<>(Arrays.asList(str.split(" ")));
        list.remove("");
        isEnoughData(list);
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
        personWriter.writeFile(person);
    }

    private void isEnoughData(List<String> list) throws MyException {
        if (list.size() != 6) {
            if (list.size() > 6) {throw new MyException("Вы ввели больше данных");}
            else {throw new MyException("Вы ввели меньше данных");}
        }
    }

    private String findGender(List<String> list) throws MyException {
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
        else {throw new MyException("Неправильно указан пол");}
    }
    private Long findPhone(List<String> list) throws MyException {
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
        else {throw new MyException("Неправильно указан телефон");}
    }
    private String findDate(List<String> list) throws MyException {
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
        else {throw new MyException("Неправильно указана дата рождения");}
    }
    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private boolean isDate(String str) {

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
