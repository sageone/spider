package org.nms.spider.engine;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nms.anxova.filter.IFilter;
import org.nms.anxova.process.IProcessor;
import org.nms.anxova.process.beans.IElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:*testContext-stringFilterTest.xml")
public class StringFilterTest {

	/**
	 * The filter to test.
	 */
	@Autowired
	public IProcessor filter;
	
	/**
	 * Elements to filter.
	 */
	@SuppressWarnings("rawtypes")
	@Autowired
	public List<IElement> elements;
	
	@Autowired
	public Integer expectedResultNum;
	
	@Before
	public void before(){
		
		Assert.assertNotNull("The filter to test is NULL",filter);
	}
	
	/**
	 * Testing the stringFilter implementation.
	 */
	@Test
	public void stringFilterTest(){
		
		List<IElement> result = filter.process(elements);
		
		Assert.assertNotNull("The result of filter is null!",result);
		Assert.assertEquals("More results than expected, filter execution fail.",
				expectedResultNum.intValue(),result.size());
		
		
	}
}
