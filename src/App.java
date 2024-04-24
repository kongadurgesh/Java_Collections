import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {

        // Collections using ArrayList
        Collection<Integer> collection = new ArrayList<>();
        collection.add(5);
        collection.add(6);

        for (Integer integer : collection) {
            System.out.println(integer);
        }

        Collection<Integer> collection2 = new ArrayList<>();
        collection2.add(234);

        Collection<Integer> collection3 = new ArrayList<Integer>();
        collection3.addAll(collection);

        System.out.println("collection size: " + collection.size()); // size of the collection
        System.out.println(collection.getClass()); // class name
        System.out.println(collection.add(200)); // adds integer
        System.out.println(collection.addAll(collection)); // adds collections
        System.out.println(collection.toString()); // prints entire collection
        System.out.println(collection.contains(7)); // checks for value presence
        System.out.println(collection.equals(collection2)); // checks for equality of collections
        System.out.println(collection.hashCode()); // prints hashcode
        System.out.println(collection.isEmpty()); // checks for emptiness
        System.out.println(collection.remove(5)); // removes the first occurance
        System.out.println(collection.toString());
        System.out.println(collection.removeAll(collection2)); // removes all the matching elements - duplicates also
        System.out.println(collection.toString());
        System.out.println(collection.removeIf(n -> n % 2 != 0)); // removes elements baed on predicate
        System.out.println(collection.toString());
        System.out.println(collection.retainAll(collection3)); // retains elements present in 2nd collection
        System.out.println(collection);

        System.out.println(collection.size()); // returns size
        System.out.println(collection.toArray()); // creates array object from collection
        System.out.println(collection.iterator()); // creates a iterator object
        System.out.println(collection.stream()); // creates a stream
        collection.clear(); // clear the collection
        System.out.println(collection.addAll(collection3));
        System.out.println(collection);
    }
}
