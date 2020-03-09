package org.adneom;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.adneom.exception.AbstractInputException;
import org.adneom.exception.NotValidListException;
import org.adneom.exception.NotValidSublistLengthException;

/**
 * @author Bilal YEZZA
 * Utility class containing tools to compute partition.
 */
public final class PartitionUtils {

    private PartitionUtils() {
    }


    /**
     * Computes the partition of a list based on the max size of a sublist.
     * The list and the maxSize must be not null.
     * When the list is empty and the maxSize is positive, partition returns a list containing an empty list.
     * When the list is not empty and the maxSize is positive, the partition returns a list of subLists of size maxSize
     * with a possible sublist with remaining elements.
     *
     * @param liste  The input list of integers.
     * @param taille The maxSize of partition subLists
     * @return partition                       list of subLists of liste
     * @throws NotValidListException          when liste is null
     * @throws NotValidSublistLengthException when taille is null or negative or equals to zero when list is not empty
     */
    public static List<List<Integer>> partition(List<Integer> liste, Integer taille) throws AbstractInputException {

        if (liste == null) {
            throw new NotValidListException();
        }
        if (taille == null || taille < 0 || (!liste.isEmpty() && taille == 0)) {
            throw new NotValidSublistLengthException();
        }

        if (liste.isEmpty()) {
            return Collections.singletonList(Collections.emptyList());
        }

        return IntStream
                .range(0, getPartitionLength(liste, taille))
                .map(i -> i * taille)
                .mapToObj(i -> liste.subList(i, Math.min(i + taille, liste.size())))
                .collect(Collectors.toList());

    }

    /**
     * Computes the partition length.
     *
     * @param liste  The input list of integers.
     * @param taille The maxSize of partition subLists
     * @return partition Length
     * @author Bilal YEZZA
     */
    private static int getPartitionLength(List<Integer> liste, Integer taille) {
        boolean isListeWithRemainder = liste.size() % taille == 0;
        return liste.size() / taille + (isListeWithRemainder ? 0 : 1);
    }
}
