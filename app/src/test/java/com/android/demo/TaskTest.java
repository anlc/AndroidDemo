package com.android.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * @author anlc
 * @date 2019-10-04
 */
public class TaskTest {

    static class Request {
        void execute() {
            try {
                Thread.sleep(2000);
                System.out.println("task : " + this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Task {
        Executor runnable;
        Request request;

        public Task(Executor runnable, Request request) {
            this.runnable = runnable;
            this.request = request;
        }
    }

    static class TaskQueue {
        private List<Task> list = new LinkedList<>();
        private boolean isRequesting = false;

        public void execute(Executor executor, final Request request) {
            if (isRequesting) {
                list.add(new Task(executor, request));
            }
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    request.execute();
                    executeNextTask();
                }
            });
        }

        private void executeNextTask() {
            if (!list.isEmpty()) {
                Task task = list.get(0);
                execute(task.runnable, task.request);
            }
        }
    }

    TaskTest() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            TaskQueue taskQueue = new TaskQueue();
            taskQueue.execute(executor, new Request());
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        new TaskTest();
    }
}
