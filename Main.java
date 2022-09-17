import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Umka", 2);
        Cat cat2 = new Cat("Luska", 5);
        Cat cat3 = new Cat("Barsic", -8);
        Cat cat4 = new Cat("Timka", 5);
        Cat cat5 = new Cat("Kuzia", -2);
        Cat[] cats = new Cat[]{cat1, cat2, cat3, cat4, cat5};
        Comparator<Cat> tomLambdaComp = Comparator.comparingInt(a -> a.getName().length());
        Arrays.sort(cats, tomLambdaComp);
        System.out.println(Arrays.toString(cats));
//  Task 2. Compound comparator to return the minimum number which module is the closest to zero.
        List<Integer> catAges = new ArrayList<>(Arrays.stream(cats).mapToInt(Cat::getAge).boxed().toList());
        System.out.println(catAges);
        Comparator<Integer> compWhoCloserToZero = Comparator.comparingInt(Math::abs);
        Comparator<Integer> minimum = Integer::compare;
        Comparator<Integer> res = compWhoCloserToZero.thenComparing(minimum.reversed());
        System.out.println(Collections.min(catAges, res));
//  Task 3. Compairing the numbers according to the sum of first and last digits in it.
        List <Integer> numbers = new ArrayList<>(Arrays.asList(4, 6, 8, 9, 100, 2, 199, 809, 997));
        Comparator <Integer> com = Comparator.comparingInt(a -> Integer.parseInt(String.valueOf(a.toString().charAt(0))) + Integer.parseInt(String.valueOf(a.toString().charAt(String.valueOf(a).length() - 1))));
        numbers.sort(com);
        System.out.println(numbers);
//  Task 4.
        Comparator <Integer> comp = Main::compare;
        System.out.println(Collections.max(numbers, comp));
//  Task5.
        File dir = new File("E:\\");
        File file = new File( "google.txt");
        List<File> onlyFiles = new ArrayList<>();
        File[] allFiles = dir.listFiles();
        if (allFiles != null) {
            for (File f : allFiles){
                if (f.isFile() && f.getName().endsWith("txt")){
                    onlyFiles.add(f);
                }
            }
        }
        System.out.println(onlyFiles);

        Comparator <File> symbols = (a, b) -> {
            try {
                return symbolsCompare(a, b);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        onlyFiles.sort(symbols);
        System.out.println(onlyFiles);
    }
    public static int symbolsCompare (File a, File b) throws IOException {
        String fromA;
        String fromB;
        fromA = Main.readFromFileToString("E:\\", a.getName()).replaceAll("[^,.\\-?!]", "");
        System.out.println(fromA.length());
        fromB = Main.readFromFileToString("E:\\", b.getName()).replaceAll("[^,.\\-?!]", "");
        System.out.println(fromB.length());
        return Integer.compare(fromA.length(), fromB.length());
    }

    public static String readFromFileToString (String sep, String fileName) throws IOException {
            return Files.lines(Paths.get(sep, fileName), Charset.forName("windows-1251")).filter(str -> str.length() > 1)
                    .collect(Collectors.joining());
        }
    public static int compare(Integer a, Integer b) {
        if (BigInteger.valueOf(a).isProbablePrime(a) & !BigInteger.valueOf(b).isProbablePrime(b)){
            return 1;
        }
        if (!BigInteger.valueOf(a).isProbablePrime(a) & BigInteger.valueOf(b).isProbablePrime(b)){
            return -1;
        }
        if (BigInteger.valueOf(a).isProbablePrime(a) && BigInteger.valueOf(b).isProbablePrime(b)){
            return a.compareTo(b);
        }
        else {
            if (a > b){
                return -1;
            }
            if (a < b){
                return 1;
            }
            else return 0;
        }
    }
}
