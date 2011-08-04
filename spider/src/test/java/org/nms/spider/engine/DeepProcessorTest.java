package org.nms.spider.engine;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nms.anxova.process.beans.IElement;
import org.nms.spider.helpers.utils.DeepTreeProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*testContext-deepSearchTest.xml")
public class DeepProcessorTest {

	private final static Logger log = LoggerFactory
			.getLogger(DeepProcessorTest.class);
	@Autowired
	private IElement element;

	@Autowired
	private DeepTreeProcessorHelper processor;

	@Before
	public void before() {
		Assert.assertNotNull("The processor to test is null!", processor);
	}

	@Test
	public void testDeepProcessor() {

		List<IElement> result = processor.process(element);

		Assert.assertNotNull("The result is null!", result);
		Assert.assertNotSame("The result has no elements!", 0, result.size());
		// [DBG]
		for (IElement e : result) {
			log.debug("Element obtained :{}", e.getId());
			System.out.println("E:" + e.getId());
		}
	}
}
