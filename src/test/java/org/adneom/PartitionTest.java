package org.adneom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.adneom.exception.NotValidInputException;
import org.adneom.exception.NotValidListException;
import org.adneom.exception.NotValidSublistLengthException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PartitionTest {

    private TestParameters inputs;

    public PartitionTest(TestParameters inputs) {
        this.inputs = inputs;
    }

    @Parameters
    public static Collection<TestParameters> inputs() {
        return Arrays.asList(
                new TestParameters(Arrays.asList(1, 2, 3, 4, 5, 6), 2, Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6))),
                new TestParameters(Arrays.asList(1, 2, 3, 4, 5, 6), 4, Arrays.asList(Arrays.asList(1, 2, 3, 4), Arrays.asList(5, 6))),
                new TestParameters(Arrays.asList(1, 2, 3, 4, 5, 6), 7, Collections.singletonList(Arrays.asList(1, 2, 3, 4, 5, 6))),
                new TestParameters(Arrays.asList(1, 2, 3, 4, 5, 6), null, NotValidSublistLengthException.class),
                new TestParameters(Arrays.asList(1, 2, 3, 4, 5, 6), 0, NotValidSublistLengthException.class),
                new TestParameters(Arrays.asList(1, 2, 3, 4, 5, 6), -1, NotValidSublistLengthException.class),
                new TestParameters(null, 2, NotValidListException.class),
                new TestParameters(Collections.emptyList(), 2, NotValidListException.class)
        );
    }

    @Test
    public void computingPartitionOfListe() throws NotValidInputException {
        if (this.inputs.expectedException == null) {
            assertEquals(this.inputs.expectedResult, PartitionUtils.partition(this.inputs.liste, this.inputs.taille));
        } else {
            assertThrows(this.inputs.expectedException, () -> PartitionUtils.partition(this.inputs.liste, this.inputs.taille));
        }
    }


    static class TestParameters {
        private List<Integer> liste;
        private Integer taille;
        private List<List<Integer>> expectedResult;
        private Class<? extends Exception> expectedException;

        public TestParameters(List<Integer> liste, Integer taille, List<List<Integer>> expectedResult) {
            this.liste = liste;
            this.taille = taille;
            this.expectedResult = expectedResult;
        }

        public TestParameters(List<Integer> liste, Integer taille, Class<? extends Exception> expectedException) {
            this.liste = liste;
            this.taille = taille;
            this.expectedException = expectedException;
        }
    }

}
