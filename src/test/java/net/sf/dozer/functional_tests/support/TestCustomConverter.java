/*
 * Copyright 2005-2010 the original author or authors.
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
package net.sf.dozer.functional_tests.support;

import net.sf.dozer.MappingException;
import net.sf.dozer.converters.CustomConverter;
import net.sf.dozer.vo.CustomDoubleObject;
import net.sf.dozer.vo.CustomDoubleObjectIF;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sullins.ben
 */
public class TestCustomConverter implements CustomConverter {

  private static final Log log = LogFactory.getLog(TestCustomConverter.class);

  public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
    // show me the destClass and sourceClass
    log.debug("Source Class is:" + sourceClass.getName());
    log.debug("Dest Class is:" + destClass.getName());

    if (source == null) {
      return null;
    }

    CustomDoubleObjectIF dest = null;
    if (source instanceof Double) {
      // check to see if the object already exists
      if (destination == null) {
        dest = new CustomDoubleObject();
      } else {
        dest = (CustomDoubleObjectIF) destination;
      }
      dest.setTheDouble(((Double) source).doubleValue());
      return dest;
    } else if (source instanceof CustomDoubleObject) {
      double sourceObj = ((CustomDoubleObjectIF) source).getTheDouble();
      return new Double(sourceObj);
    } else {
      throw new MappingException("Converter TestCustomConverter used incorrectly. Arguments passed in were:" + destination
          + " and " + source);
    }
  }
}