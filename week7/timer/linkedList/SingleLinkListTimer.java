package linkedList;

import arrayGenerator.generator.IntegerArrayGenerator;
import arrayGenerator.scope.IntegerScope;
import linkedList.list.ListAccessError;
import linkedList.list.SingleLinkList;
import timer.Timer;

public class SingleLinkListTimer extends SingleLinkList implements Timer {

    SingleLinkListTimer(SingleLinkList singleLinkList) { }

    @Override
    public Timer getTimer(int size) {
        IntegerArrayGenerator generator = new IntegerArrayGenerator(new IntegerScope(1,999));
        Integer[] array = generator.getArray(size);
        SingleLinkList <Integer> singleLinkListTimer = new SingleLinkList<>();
        try {
            for (int i=0; i<array.length; i++) {
                Integer value = array[i];
                singleLinkListTimer.add(i,value);
            }
        } catch (ListAccessError listAccessError) {
            listAccessError.printStackTrace();
        }
        return new SingleLinkListTimer(singleLinkListTimer);
    }

    @Override
    public void timedMethod() {

    }

    @Override
    public long getMaximumRuntime() {
        return 0;
    }

    @Override
    public int getMinimumTaskSize() {
        return 0;
    }

    @Override
    public int getMaximumTaskSize() {
        return 0;
    }
}
