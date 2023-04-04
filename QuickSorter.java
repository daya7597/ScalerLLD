package ThreadSort;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class QuickSorter implements Runnable{
	
	static List<Integer> list;
	int start;
	int end;
	ExecutorService executorService;
	
	public QuickSorter(int start, int end, ExecutorService executorServices) {
		this.start = start;
		this.end = end;
		this.executorService = executorServices;
	}
	
	public int partition() {
		int pivotIndex = (int)(Math.random()*(end - start + 1)) + start;

		int pivot = list.get(pivotIndex);
		
		int temp = list.get(pivotIndex);
		list.set(pivotIndex, list.get(end));
		list.set(end, pivot);
		
		int p=start-1;
		
		for(int i=start; i<=end-1; i++) {
			if(list.get(i) < pivot) {
				p++;
				temp = list.get(i);
				list.set(i, list.get(p));
				list.set(p, temp);
				
			}
		}
		
		temp = list.get(end);
		list.set(end, list.get(p+1));
		list.set(p+1, temp);
		return p+1;
	}
	
	@Override
	public void run() {
		if(start < end) {
			int p = partition();
			Future<?> submit = executorService.submit(new QuickSorter(start, p-1, executorService));
			Future<?> submit2 = executorService.submit(new QuickSorter(p+1, end, executorService));
		
			
			try {
				submit.get();
				submit2.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
