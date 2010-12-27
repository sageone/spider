package org.nms.spider.engine;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.utils.FileDownloadProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*testContext-fileDownloadTest.xml")
public class FileDownloadProcessorTest {

	@Autowired
	public FileDownloadProcessorImpl processor;

	@Autowired
	public IElement element;

	@Before
	public void before() {

		Assert.assertNotNull("The processor to test is null!", processor);
	}

	@Test
	public void fileDownloadTest() {

		List<IElement> elements = new ArrayList<IElement>();
		elements.add(element);

		processor.process(elements);

	}
}
