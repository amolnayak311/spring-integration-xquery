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
package org.springframework.integration.xquery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.core.io.Resource;
import org.springframework.integration.MessagingException;
import org.springframework.util.Assert;

/**
 * The common utility class that is used to perform all common operations
 * amongst XQuery modules. To contain all common operations that would be used
 * by the router, transformer etc along with all the parsers for these components
 *
 * @author Amol Nayak
 *
 */
public class XQueryUtils {

	/**
	 * Reads the XQuery string from the resource file specified
	 *
	 * @param resource the {@link Resource} instance of the file that contains the XQuery
	 * 			currently only classpath and file resources are supported
	 *
	 * @return the XQUery string from the resource specified
	 */
	public static String readXQueryFromResource(Resource resource) {
		Assert.isTrue(resource.exists(), "Provided XQuery resource does not exist");
		Assert.isTrue(resource.isReadable(), "Provided XQuery resource is not readable");
		try {
			URL url = resource.getURL();
			InputStream inStream = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			String line = reader.readLine();
			StringBuilder builder = new StringBuilder();
			while(line != null) {
				builder.append(line).append("\n");
				line = reader.readLine();
			}
			String xQuery = builder.toString();
			reader.close();
			return xQuery;
		} catch (IOException e) {
			throw new MessagingException("Error while reading the xQuery resource", e);
		}
	}
}
