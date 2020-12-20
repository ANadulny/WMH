package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class TabuList {
    private final int maxSize;
    private final Logger logger = LoggerFactory.getLogger(TabuList.class);
    private Queue<NeighbourState> tabu;

    public TabuList(int size) {
        this.maxSize = size;
        this.tabu = new LinkedList<>();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Queue<NeighbourState> getTabu() {
        return tabu;
    }

    public void addElement(NeighbourState elem) {
        if (maxSize <= 0) {
            return;
        }
        // remove elem from head
        if (tabu.size() >= maxSize) {
            logger.info("Element removed from tabu: " + tabu.poll());
        }
        tabu.add(elem);
    }

    public int getCurrentTabuSize() {
        return tabu.size();
    }

    @Override
    public String toString() {
        return "TabuList{\n" +
                "size=" + maxSize +
                "\ntabu=" + tabu +
                "\n}";
    }
}
