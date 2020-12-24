package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class TabuList {
    private final int maxSize;
    private final Logger logger = LoggerFactory.getLogger(TabuList.class);
    private Queue<Board> tabu;

    public TabuList(int size) {
        this.maxSize = size;
        this.tabu = new LinkedList<>();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Queue<Board> getTabu() {
        return tabu;
    }

    public void addElement(Board elem) {
        if (maxSize <= 0) {
            return;
        }
        // remove elem from head
        if (tabu.size() >= maxSize) {
            tabu.poll();
//            logger.info("Element removed from tabu: " + tabu.poll());
        }
        tabu.add(elem);
    }

    public int getCurrentTabuSize() {
        return tabu.size();
    }

    public boolean hasBoardState(Board board) {
        for (Board b: tabu) {
            if (b.equals(board)) {
                return true;
            }
        }
        return false;
    }

    public void updatePositionInTabu(Board board) {
        if (maxSize <= 0) {
            return;
        }
        tabu.remove(board);
//        logger.info("Position updated in tabu for: " + tabu.remove(board));
        tabu.add(board);
    }

    @Override
    public String toString() {
        return "TabuList{\n" +
                "size=" + maxSize +
                "\ntabu=" + tabu +
                "\n}";
    }
}
