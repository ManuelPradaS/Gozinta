import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


class GozintaTest {

    @Test
    void getGozintaChains() {


        final Gozinta test = new Gozinta();

        final Map<Long, List<List<Long>>> answers = new TreeMap<>();

        answers.put(12L, new LinkedList<>());
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(2L); add(4L); add(12L); }});
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(2L); add(6L); add(12L); }});
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(2L); add(12L); }});
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(3L); add(6L); add(12L); }});
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(3L); add(12L); }});
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(4L); add(12L); }});
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(6L); add(12L); }});
        answers.get(12L).add(new LinkedList<Long>(){{ add(1L); add(12L); }});

        
        for (final long n: answers.keySet()) {
            ArrayList<ArrayList<Long>> solutions = test.getGozintaChains(n);
            assertEquals(answers.get(n).toString(),solutions.toString());
        }


    }
}