package ThreadSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	private static ExecutorService executorService;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<Integer> list = Arrays.asList(23,45,32,67,89,234,1,67,34,45);
		
		executorService = Executors.newCachedThreadPool();
						
		QuickSorter.list = Arrays.asList(23,45,32,67,89,234,1,67,34,45);
		System.out.println(QuickSorter.list);
		Future<?> submit = executorService.submit(new QuickSorter(0, list.size()-1, executorService));
		submit.get();

		System.out.println(QuickSorter.list);
		executorService.shutdown();

	}
}
