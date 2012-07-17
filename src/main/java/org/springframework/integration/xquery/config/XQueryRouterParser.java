/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.xquery.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.AbstractRouterParser;
import org.springframework.integration.xquery.router.XQueryRouter;
import org.w3c.dom.Element;

/**
 * The parser for the XQuery router
 * @author Amol Nayak
 *
 */
public class XQueryRouterParser extends AbstractRouterParser {

	@Override
	protected BeanDefinition doParseRouter(Element element,
			ParserContext parserContext) {
		BeanDefinitionBuilder routerBuilder = BeanDefinitionBuilder.genericBeanDefinition(XQueryRouter.class);
		AbstractBeanDefinition executor = XQueryParserUtils.getXQueryExecutor(element);
		routerBuilder.addPropertyValue("executor", executor);
		return routerBuilder.getBeanDefinition();
	}
}
