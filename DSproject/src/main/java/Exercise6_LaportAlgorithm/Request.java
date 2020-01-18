package Exercise6_LaportAlgorithm;

/**
 * Project Name: DSproject
 * Created With: IntelliJ IDEA.
 * Author: Baftjar TABAKU & Erli REÇI
 * Date Created: 1/16/2020
 * Time Created: 12:47 PM
 **/
public class Request implements Comparable<Request> {

    public int time;
    public long processId;

    public Request(int time, long processId) {
        this.time = time;
        this.processId = processId;
    }

    public int getTime() {
        return this.time;
    }

    public long getProcessId() {
        return this.processId;
    }

    @Override
    public int compareTo(Request other) {
        if (this.getTime() == other.getTime())
            return 0;
        else if (this.getTime() > other.getTime())
            return 1;
        else
            return -1;
    }
}
