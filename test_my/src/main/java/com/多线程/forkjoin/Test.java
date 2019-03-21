package com.多线程.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Test extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public Test(int start, int end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute(){
        int sum = 0;
        boolean canComputer = (end - start) <= THRESHOLD;
        if(canComputer){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        }else{
            int middle = (start + end) / 2;
            Test leftTask = new Test(start, middle);
            Test rightTask = new Test(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        Test task = new Test(1, 5);
        Future<Integer> result = pool.submit(task);
        try{
            System.out.println("result is:" + result.get());
        }catch(InterruptedException e){
        }catch(ExecutionException e){
        }finally{
            pool.shutdown();
        }
    }

}
