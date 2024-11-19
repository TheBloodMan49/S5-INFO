package fr.insa_rennes.sdd.priority_queue;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class OrderedArrayPQQuickCheckTest {
	PriorityQueueQuickCheckTestCommon test = new PriorityQueueQuickCheckTestCommon(() -> new OrderedArrayPQ<>());
	
	@Property(trials = 500)
    public void arraySameSize(List<Integer> elements) {
		test.sameSize(elements);
    }
	
	@Property(trials = 500)
    public void isEmpty(@When(satisfies = "#_.size() <= 1000") List<Integer> elements, 
    					@InRange(min = "0", max = "1000") Integer nbPolls) {
        test.isEmpty(elements, nbPolls);
    }
	
	@Property(trials = 500) 
    public void arrayIsSorted(List<Integer> elements) {
		test.arrayIsSorted(elements);
	}
	
	@Property(trials = 500) 
    public void peekIsMinAndSizeUnchanged(List<Integer> elements) {
        test.peekIsMinAndSizeUnchanged(elements);
    }
	
	@Property(trials = 500) 
    public void pollIsMinAndDecrementSize(List<Integer> elements) {
        test.pollIsMinAndDecrementSize(elements);
    }
}
