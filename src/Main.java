import java.io.IOException;
import java.lang.constant.Constable;
import java.util.Scanner;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = sc.nextLine();
        System.out.println(calc(input));
        sc.close();
    }
    public static String calc(String input) throws IOException {
        int a = 0, b = 0, c = 0;
        char z = '.';
        StringBuilder calcResult = new StringBuilder();
        String pat = "^\\d+", pat2 = "\\d+$", znak = "[+\\-*/]", rimPat = "^[I,V,X]{1,4}", rimPat2 = "[I,V,X]{1,4}$",
                rimMath = "^[I,V,X]{1,4}\\s?[+\\-*\\/]\\s?[I,V,X]{1,4}$",
                arMath = "^[0-9]{1,2}\\s?[+\\-*\\/]\\s?[0-9]{1,2}$";
        boolean rimString = checkMath(rimMath,input);
        boolean arString = checkMath(arMath,input);
        if (rimString == true){
            String rimNumOne = pattern(rimPat,input);
            a = rimSymbol(rimNumOne);

            String rimNumTwo = pattern(rimPat2,input);
            b = rimSymbol(rimNumTwo);

            String string = pattern(znak, input);
            z = string.charAt(0);

            c = operation(z,a,b);

            String str = rimEndNum(c);
            System.out.println("Результат:" + str);

        }else if(arString == true){
            String string = pattern(pat, input);
            a = Integer.parseInt(string);

            string = pattern(pat2, input);
            b = Integer.parseInt(string);

            string = pattern(znak, input);
            z = string.charAt(0);
            c = operation(z,a,b);
            System.out.println("Результат:" + c);
        }else {
            throw new IOException("Неверно введено выражение");
        }

        return calcResult.toString();
    }
    public static int rimSymbol(String rimString) throws IOException {
        int a = 0;
        switch (rimString){
            case ("I"):
                a = 1;
                break;
            case ("II"):
                a = 2;
                break;
            case ("III"):
                a = 3;
                break;
            case ("IV"):
                a = 4;
                break;
            case ("V"):
                a = 5;
                break;
            case ("VI"):
                a = 6;
                break;
            case ("VII"):
                a = 7;
                break;
            case ("VIII"):
                a = 8;
                break;
            case ("IX"):
                a = 9;
                break;
            case ("X"):
                a = 10;
                break;
            default: throw new IOException ("Неправильно записана цифра: "+ rimString);
        }
        return a;
}
    public static int operation(char znak,int a,int b) throws IOException {
        int c = 0;
        if (a > 0 && b > 0 && a <= 10 && b <= 10) {
            switch (znak) {
                case ('+'):
                    c = a + b;
                    break;
                case ('-'):
                    c = a - b;
                    break;
                case ('*'):
                    c = a * b;
                    break;
                case ('/'):
                    c = a / b;
                    break;
            }
        }else {throw new IOException("Значение должно быть больше нуля и меньше десяти");}
        return c;
    }
    public static String pattern(String pat,String primer) {
        StringBuffer str = new StringBuffer();

        Pattern patNum = Pattern.compile(pat);
        Matcher matcher = patNum.matcher(primer);

        while (matcher.find()) {
           str.append(primer.substring(matcher.start(), matcher.end()));
        }
        return str.toString();
    }
    public static Boolean checkMath(String pat,String primer){
        boolean str = Pattern.matches(pat,primer);
        return str;
    }
    public static String rimEndNum(int arNum) throws IOException {
        if (arNum<=0){throw new IOException("В римской системе нет отрицательных чисел");}
        StringBuffer c = new StringBuffer();
        int[] array = {100,90,80,70,60,50,10,9,5,4,1};
        String[] arrayRim = {"C","XC","LXXX","LXX","LX","L","X","IX","V","IV","I"};
        for (int i=0; i < array.length;){
            if(arNum>=array[i]){
                arNum = arNum - array[i];
                c.append(arrayRim[i]);
            }else {++i;}
        }
        return c.toString();
    }
}