package org.adneom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.adneom.exception.AbstractInputException;
import org.adneom.exception.NotValidListException;
import org.adneom.exception.NotValidSublistLengthException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

/**
 * @author Bilal YEZZA
 * Parameterized test to check all possible cases (9 cases).
 */
@RunWith(Parameterized.class)
public class PartitionTest {

    private TestParameters inputs;

    public PartitionTest(TestParameters inputs) {
        this.inputs = inputs;
    }

    /**
     * test cases to cover all possible inputs:
     * test1: Without remaining elements (list of 6 elements and taille is 2) : should succeed
     * test2: With remaining elements (list of 6 elements and taille is 4, so we have 2 remaining elements) : should succeed
     * test3: With remaining elements (list of 6 elements and taille is 7, so we have 6 remaining elements) : should succeed
     * test4: taille is null : should throw NotValidSublistLengthException
     * test5: taille is 0 and liste is not empty : should throw NotValidSublistLengthException
     * test6: taille is -1 and liste is not empty : should throw NotValidSublistLengthException
     * test7: liste is null : should throw NotValidListException
     * test8: liste is empty and taille is 0 : should succeed
     * test9: liste is empty and taille is 2 : should succeed
     *
     * @return List of testParameters
     */
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
                new TestParameters(Collections.emptyList(), 0, Collections.singletonList(Collections.emptyList())),
                new TestParameters(Collections.emptyList(), 2, Collections.singletonList(Collections.emptyList()))
        );
    }

    @Test
    public void computingPartitionOfListe() throws AbstractInputException {
        if (this.inputs.expectedException == null) {
            assertEquals(this.inputs.expectedResult, PartitionUtils.partition(this.inputs.liste, this.inputs.taille));
        } else {
            assertThrows(this.inputs.expectedException, () -> PartitionUtils.partition(this.inputs.liste, this.inputs.taille));
        }
    }
}
