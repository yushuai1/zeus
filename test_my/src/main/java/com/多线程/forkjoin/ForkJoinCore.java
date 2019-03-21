//package com.多线程.forkjoin;
//
//import java.util.concurrent.RecursiveTask;
//
//public class ForkJoinCore extends RecursiveTask<Boolean> {
//
//    //一个小时的毫秒数
//    private static final long THRESHOLD = 3600*1000;
//    //要处理的时间的开始毫秒数
//    private long startTime;
//    //要处理的时间的结束毫秒数
//    private long endTime;
//
//    public ForkJoinCore(long startTime, long endTime) {
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }
//
//    @Override
//    protected Boolean compute(){
//        boolean flag = true;
//        boolean isNewThread = (endTime - startTime) <= THRESHOLD;
//        if(isNewThread){
//            for(int i = start; i <= end; i++){
//                sum += i;
//            }
//        }else{
//            int middle = (start + end) / 2;
//            Test leftTask = new Test(start, middle);
//            Test rightTask = new Test(middle + 1, end);
//            leftTask.fork();
//            rightTask.fork();
//            int leftResult = leftTask.join();
//            int rightResult = rightTask.join();
//            sum = leftResult + rightResult;
//        }
//        return sum;
//    }
//}
