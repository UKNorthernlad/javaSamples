package com.mycompany.app;

import java.util.*;

public class StreamsApp {
    public static void main(String[] args) {

        // A Set<T> is an interface to a collection that contains no duplicate elements.
        Set<UUID> accountIds = new HashSet<>();
        accountIds.add(UUID.randomUUID());
        accountIds.add(UUID.randomUUID());
        accountIds.add(UUID.randomUUID());

        UUID guid = UUID.randomUUID();

        accountIds.add(guid);
        accountIds.add(guid); // This item will not be added to the set because it is a duplicate.

        // var result2 = IntStream.range(0, (4101 + 99) / 100)
        //                             .mapToObj(i -> Collections.singletonMap("keyBlah", "valueBlah"))
        //                             .collect(Collectors.toList());

        System.out.println("Hello World!");
        System.out.println(String.valueOf((4 + 99) / 100));

        //List<Set<UUID>> partitions = partitionSet(accountIds);

    }

    // private static List<Set<UUID>> partitionSet(Set<UUID> set) {
    //     List<UUID> list = new ArrayList<>(set);

    //     // Use .range to create a stream of numbers from 0. These represent a page of 100 list entries.
    //     return IntStream.range(0, (list.size() + 99) / 100)
    //             .mapToObj(i -> new HashSet<>(list.subList(i * 100, Math.min((i + 1) * 100, list.size()))))
    //             .collect(Collectors.toList());


    // }

}
