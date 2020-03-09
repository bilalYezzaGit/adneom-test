package org.adneom;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.adneom.exception.NotValidInputException;
import org.adneom.exception.NotValidListException;
import org.adneom.exception.NotValidSublistLengthException;

public final class PartitionUtils {

    private PartitionUtils() {
    }

    public static List<List<Integer>> partition(List<Integer> liste, Integer taille) throws NotValidInputException {

        if (liste == null || liste.isEmpty()) {
            throw new NotValidListException();
        }
        if (taille == null || taille <= 0) {
            throw new NotValidSublistLengthException();
        }

        return IntStream
                .range(0, getPartitionLength(liste, taille))
                .map(i -> i * taille)
                .mapToObj(i -> liste.subList(i, Math.min(i + taille, liste.size())))
                .collect(Collectors.toList());

    }

    private static int getPartitionLength(List<Integer> liste, Integer taille) {
        boolean isListeWithRemainder = liste.size() % taille == 0;
        return liste.size() / taille + (isListeWithRemainder ? 0 : 1);
    }
}
