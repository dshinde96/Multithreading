st<Future<Integer>> futures=executor.invokeAll(tasks,2,TimeUnit.SECONDS);  //Invoke all the tasks withing time frame of 2 seconds
            // for(Future<Integer>f:futures){
            //     