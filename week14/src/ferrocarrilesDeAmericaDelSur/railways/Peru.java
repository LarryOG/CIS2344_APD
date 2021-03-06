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
public class Peru extends Railway {
	/**
	 * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 * @throws SetUpError if there is an error in setting up the delay.
	 */
	public Peru() throws SetUpError {
		super("Peru",new Delay(0.1,0.3));
	}

    /**
     * Run the train on the railway.
     * This method provide correct synchronisation to avoid two trains being in the pass at the same time.
     */
    public void runTrain() throws RailwaySystemError {

    	Clock clock = getRailwaySystem().getClock();
        Railway nextRailway = getRailwaySystem().getNextRailway(this); // get access to other railway's basket.
        while (!clock.timeOut()) {
    		choochoo(); //non critical section
            getBasket().putStone(); // signal that you want to cross the path (put a stone in your basket)
            while (nextRailway.getBasket().hasStone()) { //while Bolivia wants to cross the path
                if (getSharedBasket().hasStone()) {         // check if its not your turn
                    getBasket().takeStone();                //stop signalling need to cross the path
                    while (getSharedBasket().hasStone()){   //and wait until its your turn
                        siesta(); //get tipsy while waiting...
                    }
                    getBasket().putStone(); // once your turn, put the stone in your basket
                }
            }
            crossPass();//cross the path (critical section)
            getSharedBasket().putStone(); //give turn to other railway
            getBasket().takeStone();  //stop signaling need to cross the path.
    	}
    }
}