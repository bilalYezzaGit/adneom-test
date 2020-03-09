package org.adneom;

import java.util.List;

/**
 * @author Bilal YEZZA
 * Class to encapsulate inputs and expected result or exception of a parameterized test.
 */
class TestParameters {
    List<Integer> liste;
    Integer taille;
    List<List<Integer>> expectedResult;
    Class<? extends Exception> expectedException;

    TestParameters(List<Integer> liste, Integer taille, List<List<Integer>> expectedResult) {
        this.liste = liste;
        this.taille = taille;
        this.expectedResult = expectedResult;
    }

    TestParameters(List<Integer> liste, Integer taille, Class<? extends Exception> expectedException) {
        this.liste = liste;
        this.taille = taille;
        this.expectedException = expectedException;
    }
}