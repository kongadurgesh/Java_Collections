package colections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionsDemo {
    public static void main(String[] args) {
        listOperations();
        setOperations();
        mapOperations();
        queueOperations();
        sortList();
        customObjectsList();
        groupListsByProperty();
        filterAndMapList();
        mergeMaps();
        parallelStreamsSum();
        flatMap();
        countMapOfStrings();
    }

    // Collecting into Custom Data Structure
    // Given a list of strings representing sentences, collect the words from all
    // sentences into a custom data structure that keeps track of the frequency of
    // each word.
    private static void countMapOfStrings() {
        List<String> sentences = Arrays.asList(
                "Hello world",
                "World of Java",
                "Java is awesome");
        // sentences.forEach(sentence -> Arrays.asList(sentence.split(" ")));
        // System.out.println(sentences);
        List<String> flattedStrings = new ArrayList<>();
        sentences.forEach(sentence -> {
            flattedStrings.addAll(Arrays.asList(sentence.split(" ")));
        });
        Map<String, Long> countMap = flattedStrings.stream()
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        System.out.println(countMap);

        Map<String, Long> countMap2 = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(countMap2);

    }

    // FlatMap Operation
    // Given a list of lists of integers, flatten the lists into a single list of
    // integers using the flatMap operation.
    private static void flatMap() {
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8));

        List<Integer> flatIntegers = listOfLists.stream().flatMap(list -> list.stream()).toList();
        System.out.println(flatIntegers);
    }

    // Parallel Stream Processing
    // Given a large list of integers, calculate the sum of all even numbers using
    // parallel stream processing.
    private static void parallelStreamsSum() {
        List<Integer> numbers = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());
        System.out.println(numbers.parallelStream().collect(Collectors.summingInt(Integer::intValue)));
        System.out.println(numbers.stream().mapToInt(Integer::intValue).sum());
    }

    // Given two maps representing the scores of students in two different subjects,
    // merge the maps such that:
    // If a student appears in both maps, their scores in both subjects should be
    // added together.
    // If a student appears in only one map, their scores in the other subject
    // should be considered as 0.
    private static void mergeMaps() {
        Map<String, Integer> mathScores = new HashMap<>();
        mathScores.put("Alice", 85);
        mathScores.put("Bob", 90);
        mathScores.put("Charlie", 75);

        Map<String, Integer> physicsScores = new HashMap<>();
        physicsScores.put("Alice", 80);
        physicsScores.put("David", 88);
        physicsScores.put("Eva", 95);

        Map<String, Integer> totalScores = new HashMap<>();
        totalScores.putAll(mathScores);
        physicsScores.forEach((t, u) -> {
            if (totalScores.containsKey(t)) {
                totalScores.put(t, totalScores.get(t) + u);
            } else {
                totalScores.put(t, u);
            }
        });
        Map<String, Integer> sumOfScores = Stream.of(mathScores, physicsScores)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
        System.out.println(sumOfScores);
    }

    // Filtering and Mapping
    // Given a list of Person objects (similar to the previous questions), filter
    // the list to include only persons with an age greater than or equal to 30.
    // Map the filtered list to a list of strings containing only the names of the
    // filtered persons.
    private static void filterAndMapList() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("Dan", 30));
        people.add(new Person("Erik", 25));
        people.add(new Person("Flit", 35));

        System.out.println("Filtered Persons");
        System.out.println(people.stream().filter(person -> person.getAge() > 30)
                .map(Person::getName).toList());
    }

    // Group Elements by Property
    // Given a list of Person objects (similar to the previous question), group the
    // persons by their age.
    private static void groupListsByProperty() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("Dan", 30));
        people.add(new Person("Erik", 25));
        people.add(new Person("Flit", 35));

        System.out.println("Persons grouped by age");
        System.out.println(people.stream().collect(
                Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList()))));
    }

    private static void customObjectsList() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        System.out.println("Sorted people based on age ascending");

        System.out.println(people.stream().sorted((x, y) -> x.age - y.age).toList()
                .stream().map(Person::getName).toList());
    }

    // Intermediate Level
    // Sort a List
    // Given a list of strings, sort the list in alphabetical order and in reverse
    // alphabetical order.
    private static void sortList() {
        List<String> list = Arrays.asList("banana", "apple", "cherry");
        System.out.println("alphabetical order");
        System.out.println(list.stream().sorted().toList());
        System.out.println("reverse alphabetical order");
        System.out.println(list.stream().sorted().toList().reversed());
    }

    private static void queueOperations() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        System.out.println(queue);
        queue.remove();
        System.out.println("First Element:" + queue.peek());
        for (Integer item : queue) {
            System.out.println(item);
        }
    }

    private static void mapOperations() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 30);
        map.put("Bob", 25);
        map.put("Charlie", 35);
        System.out.println("Alice:" + map.get("Alice"));
        map.remove("Bob");
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }

    private static void setOperations() {
        Set<String> set = new HashSet<>(Arrays.asList("apple", "banana", "cherry"));
        set.add("date");
        if (set.contains("banana"))
            System.out.println("set contains banana");
        set.remove("apple");
        for (String item : set) {
            System.out.println(item);
        }
    }

    private static void listOperations() {
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        list.add(60);
        System.out.println(list.get(2));
        list.remove(1);
        for (Integer item : list) {
            System.out.println(item);
        }
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
