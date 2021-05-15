package cos.controls;


/**
 * this class contains all the basic of course tasks, and all future implementations will be made here.
 *
 */

public class Task extends BasicTask {
    private static final long serialVersionUID = 1L;



    public Task(boolean state, WorkHourCounter wHS, String name, String des, String notes, Integer prio) {
        super(state, wHS, name, des, notes, prio);

    }


}
