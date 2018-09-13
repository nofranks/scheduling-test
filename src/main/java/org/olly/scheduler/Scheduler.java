package org.olly.scheduler;

import org.olly.scheduler.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Scheduler {

    public static List<Task> scheduleTasks(Collection<Task> tasks) throws ScheduleCycleException {
        List<Task> schedule = new ArrayList<>();
        for(Task t : tasks)schedule(new ArrayList<>(), schedule, t);
        return schedule;
    }

    static Collection<Task> schedule(List<String> visited, Collection<Task> scheduled, Task task) throws ScheduleCycleException {
        visited.add(task.getName());

        if (task.getDependant() != null) {
            if (visited.contains(task.getDependant().getName())) throw new ScheduleCycleException();
            else {
                schedule(visited, scheduled, task.getDependant());
            }
        }

        if(!scheduled.contains(task))scheduled.add(task);

        return scheduled;
    }
}
