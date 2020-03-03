package railways;

import errors.RailwaySystemError;
import errors.SetUpError;
import tools.Clock;
import tools.Delay;

/**
 * Attempts to manage safe crossing of the pass using attempt 5 from the lecture.
 * In the lecture, this attempt was, using two boolean shared variables:
 * while (true) {
 *   procReqCS[id] = true;
 *   while (procReqCS[(id+1)%2]) {
 *     procReqCS[id] = false;
 *     procReqCS[id] = true;
 *   }
 *   critical section;
 *   procReqCS[id] = false;
 * }
 * Implementing this for railways we will use the railway's basket as an implementation
 * of the procReqCS[id] boolean, with a stone in the basket for true, and no stone modelling
 * false.  The other railway's basket (modelling procReqCS[(id+1)%2]) can be accessed through
 * the railway system in the way shown in figure 5 of the practical notes.
 * The critical section corresponds to the crossPass() method, and the (implicit)
 * non-critical section is the choochoo() method.
 * 
 * @author Hugh Osborne
 * @version January 2020
 *
 */
public class Bolivia extends Railway {
	/**
	 * @throws RailwaySystemError if there is an error in constructing the delay
	 * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 */
	public Bolivia() throws SetUpError{
		super("Bolivia",new Delay(0.1,0.3));
	}

    /**
     * Run the train on the railway.
     * This method provides (incorrect) synchronisation attempting to avoid more than one train in the 
     * pass at any one time.
     */
    public void runTrain() throws RailwaySystemError {
    	Clock clock = getRailwaySystem().getClock();
    	Railway nextRailway = getRailwaySystem().getNextRailway(this); // find out from the railway system which is the other railway
    	while (!clock.timeOut()) {
    		choochoo(); // the non-critical section
    		getBasket().putStone(this); // put a stone in your basket
    		                            // i.e. signal that you want to cross the pass
    		while (nextRailway.getBasket().hasStone(this)) { // if the other railway's basket has a stone in it
    			                                             // i.e. the other railway wants to cross the pass
    			getBasket().takeStone(this); // take the stone out of your basket
    			                             // i.e. give the other railway a chance to cross the pass
    			siesta(); // have a snooze
    			getBasket().putStone(this); // put the stone back in your basket
    			                            // because you still want to cross the pass
    		}
    		crossPass(); // the critical section
    		getBasket().takeStone(this); // take the stone out of your basket
    		                             // because you have now successfully crossed the pass
    		                             // and do not (currently) want to cross it again
    	}
    }
}