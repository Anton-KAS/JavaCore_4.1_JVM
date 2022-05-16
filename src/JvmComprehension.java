
// При вызове класса в Class Loader подгружается класс JvmComprehension в
// Application ClassLoader -> Platform ClassLoader -> ApplicationClassLoader -> Platform ClassLoader -> Application ClassLoader
// Происходит подгрузка класса в том Loader, где он был найден
// Далее происходит связывание (подготовка класса к выполнению) по этапам: Verify -> Prepare -> Resolve
// Далее происходит инициализация класса и загрузка его в Metaspace
public class JvmComprehension {

    public static void main(String[] args) { // в Stack memory создается фрейм main
        int i = 1;                      // 1 в Stack memory фрейм main добавляется int = 1
        Object o = new Object();        // 2 в Heap создается Object, в Stack Memory фрейм main дабавляется o = ссылка на объект Object в Heap
        Integer ii = 2;                 // 3 в Heap создается Integer, в Stack Memory фрейм main дабавляется ii = ссылка на объект Integer в Heap
        printAll(o, i, ii);             // 4 в Stack memory создается фрейм printAll в Stack Memory фрейм printAll добавляется:
                                            // 0 c ссылкой на ранее созданный объект Object (п.2) в Heap
                                            // i = 1
                                            // ii c сылкой на ранее созданный объект Integer (п.3) в Heap
        System.out.println("finished"); // 7 в Stack Memory создается фрейм println со связкой со String в Heap и после выполнения удаляет фрейм println из Stack Memory
                                        // После завершения main очищается фрейм main в Stack Memory и программа завершит свою работу
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5 в Heap создается новый Integer, в Stack Memory фрейм main дабавляется ii = ссылка на новый объект Integer в Heap
        System.out.println(o.toString() + i + ii);  // 6 в Stack Memory создается фрейм println со связкой со String в Heap и после выполнения удаляет фрейм println из Stack Memory
                                                    // После завершения printAll очищается фрейм printAll в Stack Memory
    }
}
