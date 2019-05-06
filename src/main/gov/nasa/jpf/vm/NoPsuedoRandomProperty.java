/*
 * Copyright (C) 2014, United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 *
 * The Java Pathfinder core (jpf-core) platform is licensed under the
 * Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0. 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package gov.nasa.jpf.vm;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.GenericProperty;
import gov.nasa.jpf.search.Search;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * property class to check for use of Math.Random
 */
public class NoPsuedoRandomProperty extends GenericProperty {

  public NoPsuedoRandomProperty (Config config) {
      super();
  }

  @Override
  public String getExplanation () {
    // that's pretty self explaining, isn't it?
    return "no use of Math.Random";
    //return "no uncaught exception";
  }

  @Override
  public String getErrorMessage () {
    return "Used Math.Random";
  }

  @Override
  public void reset() {
  }

  @Override
  public boolean check (Search search, VM vm) {
      MethodInfo mi = vm.getLastTransition().getThreadInfo().getLastInvokedStackFrame().getMethodInfo();
      return !mi.getClassName().equals("java.util.Random") && !(mi.getClassName().equals("java.lang.Math") && mi.getName().equals("random"));

  }

}
