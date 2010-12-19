package org.nms.spider.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nms.spider.engine.impl.BaseSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This test must be used on a controlled web site. The web site is under
 * /var/www/test/spider.
 * 
 * @author daviz
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*testContext-baseSpiderTest.xml")
public class BaseSpiderTest {

	private static final Logger log = LoggerFactory.getLogger(BaseSpider.class);

	@Autowired
	BaseSpider spider;

	@Before
	public void before() {

		Assert.assertNotNull("The spider is null!", spider);
		log.debug("The spider loaded, with {} preprocessors and {} processors",
				spider.getPreprocessor(), spider.getPostprocessor());
	}

	@Test
	public void spiderTest() {

		spider.spider();

	}

}
