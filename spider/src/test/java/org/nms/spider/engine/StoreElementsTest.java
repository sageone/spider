package org.nms.spider.engine;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.IFilter;
import org.nms.spider.helpers.IProcessor;
import org.nms.spider.helpers.impl.NonRepeatFilterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:*testContext-storeElementsTest.xml")
public class StoreElementsTest {

	@Autowired
	IElement element;
	
	@Autowired
	NonRepeatFilterImpl filter;
	
	@Before
	public void before(){
		Assert.assertNotNull(element);
		Assert.assertNotNull(filter);
	}
	
	/**
	 * Testing the stringFilter implementation.
	 */
	@Test
	public void storeElementsTest(){
		boolean result;
		
		result = filter.passes(element);
		
		Assert.assertTrue("The element must not exist in the Database.",result);
		
		result = filter.passes(element);
		
		Assert.assertFalse("The element must be stored by the filter!",result);
	}
}
