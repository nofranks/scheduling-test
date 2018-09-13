package org.olly.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.olly.scheduler.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchedulerTest {

    private static Logger logger = LogManager.getLogger();

    @Test
    public void testLoadSchedule() {

        String tasks = "[a=>b.b=>c,a]";
        Matcher m = Pattern.compile("\\[([^]]+)\\]").matcher(tasks);
        logger.debug("Load Scheduler Test {}", m.matches());
    }

    @Test
    public void testEmptySchedule()throws ScheduleCycleException {
        List<Task> schedule = Scheduler.scheduleTasks(Collections.emptyList());
        Assert.assertTrue(schedule.isEmpty());
    }

    @Test
    public void testScheduleNoDependencies()throws ScheduleCycleException {
        Task a = new Task("a");
        Task b = new Task("b");
        List<Task> tasks = new ArrayList<>();
        tasks.add(a);
        tasks.add(b);
        List<Task> scheduled = Scheduler.scheduleTasks(tasks);
        String result = parseToResultString(scheduled);
        Assert.assertEquals("ab", result);
    }

    @Test
    public void testScheduleDependencies() throws ScheduleCycleException {

        Task a = new Task("a");
        Task b = new Task("b");
        Task c = new Task("c");

        a.setDependant(b);
        b.setDependant(c);

        List<Task> tasks = new ArrayList<>();
        tasks.add(a);
        tasks.add(b);
        tasks.add(c);

        List<Task> scheduled = Scheduler.scheduleTasks(tasks);
        String result = parseToResultString(scheduled);
        Assert.assertEquals("cba", result);
    }

    @Test(expected = ScheduleCycleException.class)
    public void testCycle() throws ScheduleCycleException {

        Task a = new Task("a");
        Task b = new Task("b");
        Task c = new Task("c");

        a.setDependant(c);
        c.setDependant(a);

        List<Task> tasks = new ArrayList<>();
        tasks.add(c);
        tasks.add(b);
        tasks.add(a);

        List<Task> scheduled = Scheduler.scheduleTasks(tasks);

    }


    String parseToResultString(List<Task> tasks){
        return tasks.stream().map(x -> x.getName()).reduce("", String::concat);
    }

}


