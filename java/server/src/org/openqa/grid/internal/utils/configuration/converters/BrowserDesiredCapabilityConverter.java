// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.grid.internal.utils.configuration.converters;

import com.beust.jcommander.IStringConverter;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserDesiredCapabilityConverter implements IStringConverter<DesiredCapabilities> {
  @Override
  public DesiredCapabilities convert(String value) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    for (String cap : value.split(",")) {
      String[] pieces = cap.split("=");
      try {
        final Long x = Long.parseLong(pieces[1]);
        capabilities.setCapability(pieces[0], x);
      } catch (NumberFormatException e) {
        // ignore the exception. process as boolean or string.
        if (pieces[1].equals("true") || pieces[1].equals("false")) {
          capabilities.setCapability(pieces[0], Boolean.parseBoolean(pieces[1]));
        } else {
          capabilities.setCapability(pieces[0], pieces[1]);
        }
      }
    }
    return capabilities;
  }
}
