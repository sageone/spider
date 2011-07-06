package org.nms.spider.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates a Thead for each xml spider file configuration, and launches all threads to spider the elements.
 * 
 * @author daviz
 *
 */
public class ThreadedStandAloneMultipleSpider {

	/**
	 * The Logger
	 */
	private final static Logger log = LoggerFactory
			.getLogger(ThreadedStandAloneMultipleSpider.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		log.info("Starting spider");
		if (args == null || args.length == 0) {
			log.error("Must indicate the xml configuration file. This file must be in the classpath");
			System.exit(-1);
		}
		try {
			Semaphore s = new Semaphore(args.length);
			
			List<Thread> threads = new ArrayList<Thread>();
			for (String ctx : args) {
				RunnableSpider rs = new RunnableSpider(ctx, s);
				Thread t = new Thread(rs,ctx);
				t.start();
				threads.add(t);

			}

			// Wait for all threads to release the semaphore.
			s.acquire(args.length);
			
		} catch (Exception e) {
			log.error("Errors during execution : {}", e.getMessage());
			log.error("Exception:", e);
			System.exit(-1);
		}
		log.info("End of spider");

		System.exit(0);
	}

	/**
	 * A runnable spider.
	 * @author daviz
	 *
	 */
	static class RunnableSpider implements Runnable{

		/**
		 * The semaphore to acquire and release.
		 */
		private Semaphore semaphore;
		
		/**
		 * The xml context file.
		 */
		private String context;
		
		public RunnableSpider(String ctx,Semaphore s){
			this.context = ctx;
			this.semaphore = s;
		}
		@Override
		public void run() {
			try {
				semaphore.acquire();
				ContextSpiderStarter.startSingleSpiderContext(context);
			} catch (InterruptedException e) {
				log.error("Interrupted runnable spider {}",context,e);
				throw new RuntimeException(e);
			} catch (Exception e) {
				log.error("Error during runnable spider {}",context,e);
				throw new RuntimeException(e);
			}finally{
				semaphore.release();
			}
			
		}
		
	}
}
