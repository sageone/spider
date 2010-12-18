package org.nms.spider.engine;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nms.spider.beans.IElement;
import org.nms.spider.beans.impl.StringElement;
import org.nms.spider.engine.impl.BaseSpider;
import org.nms.spider.helpers.impl.AppenderElementModifierImpl;
import org.nms.spider.helpers.impl.RegexParserProcessorImpl;
import org.nms.spider.helpers.impl.UrlProcessorImpl;
import org.nms.spider.helpers.utils.ChainedProcessorHelperImpl;
import org.nms.spider.helpers.utils.ElementModifierProcessorImpl;
import org.nms.spider.helpers.utils.LogProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This test must be used on a controlled web site.
 * The web site is under /var/www/test/spider.
 * @author daviz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:*testContext-baseSpiderTest.xml")
public class BaseSpiderTest {

	private static final Logger log = LoggerFactory.getLogger(BaseSpider.class);
	
	@Autowired
	BaseSpider spider;
	
	@Before
	public void before(){

		Assert.assertNotNull("The spider is null!",spider);
		log.debug("The spider loaded, with {} preprocessors and {} processors"
				,spider.getPreprocessor(),spider.getPostprocessor());
	}

	
	@Test
	public void spiderTest(){
		
		spider.spider();
		
		
	}

}
