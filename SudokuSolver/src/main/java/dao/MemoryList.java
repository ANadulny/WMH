package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class MemoryList {
    private final int maxSize;
    private final int maxBoardFrequency;
    private final Logger logger = LoggerFactory.getLogger(MemoryList.class);
    private Queue<Board> memory;

    public MemoryList(int size, int maxBoardFrequency) {
        this.maxSize = size;
        this.maxBoardFrequency = maxBoardFrequency;
        this.memory = new LinkedList<>();
    }

    public void addBoard(Board board) {
        if (maxSize <= 0) {
            return;
        }
        // remove elem from head
        if (memory.size() >= maxSize) {
            memory.poll();
//            logger.info("Element removed from memory: " + memory.poll());
        }
        memory.add(board);
    }

    public boolean isBoardFrequenciesOk(Board board) {
        int counter = 0;
        for (Board b: memory) {
            if (b.equals(board)) {
                counter++;
            }
        }
        return counter <= maxBoardFrequency;
    }
}
