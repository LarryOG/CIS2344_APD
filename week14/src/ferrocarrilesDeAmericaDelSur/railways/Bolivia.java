package ferrocarrilesDeAmericaDelSur.railways;

import ferrocarrilesDeAmericaDelSur.errors.RailwaySystemError;
import ferrocarrilesDeAmericaDelSur.errors.SetUpError;
import ferrocarrilesDeAmericaDelSur.tools.Clock;
import ferrocarrilesDeAmericaDelSur.tools.Delay;

/**
 * An implementation of railway, with guaranteed safe crossing of the pass using Dekker's algorithm.
 *
 *  while (true) {
 *     procReqCS[id] = true;
 *     while (procReqCS[(id+1)%2]) {
 *       if (turn == (id+1) % 2){
 *           procReqCS[id] = false;
 *           while (turn != id);
 *           procReqCS[id] = true;
 *       }
 *     }
 *     critical section;
 *     turn = (id+1) % 2;
 *     procReqCS[id] = false;
 *   }
 *
 *   Implementing this for railways we will use the railway's basket as an implementation
 *   of the procReqCS[id] boolean, with a stone in the basket for true, and no stone modelling
 *   false.  The other railway's basket (modelling procReqCS[(id+1)%2]) can be accessed through
 *   the railway system in the way shown in figure 5 of the practical notes.
 *   The shared basket will be used to model priority/turn I.e. If there is a stone in the shared-
 *   basket, it's Bolivia's turn, and no stone modelling Peru's turn.
 *   The critical section corresponds to the crossPass() method, and the (implicit)
 *   non-critical section is the choochoo() method.
 *
 */
public class  Bolivia extends Railway {
	/**
     * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 * @throws SetUpError if there is an error in setting up the delay.
	 */
	public Bolivia() throws SetUpError {
		super("Bolivia",new Delay(0.1,0.3));
	}

    /**
     * Run the train on the railway.
     * This method provide correct synchronisation to avoid two trains being in the pass at the same time.
     */
    public void runTrain() throws RailwaySystemError {
    	Clock clock = getRailwaySystem().getClock();
        Railway nextRailway = getRailwaySystem().getNextRailway(this); //get access to Peru's basket
    	while (!clock.timeOut()) {
    		choochoo(); //non-critical section
			getBasket().putStone(); //put stone in your basket (signal that you want to cross the path)
			while (nextRailway.getBasket().hasStone()) { //check other railways basket (if they want to cross the path too)
			    if (!getSharedBasket().hasStone()) { //if its not your turn (no stone in shared basket)
			        getBasket().takeStone(); //take the stone from your basket (stop signaling)
			        while (!getSharedBasket().hasStone()){ //wait until its your turn
			            siesta(); //might have a drink
                    }
			        getBasket().putStone(); //return stone to your basket
                }
            }
			crossPass(); //critical section
			if (getSharedBasket().hasStone()){getSharedBasket().takeStone();} //give turn to other railway
			getBasket().takeStone(); //stop signaling need to cross the path.
    	}
    }
}