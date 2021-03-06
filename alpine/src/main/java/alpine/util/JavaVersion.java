/*
 * This file is part of Alpine.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) Steve Springett. All Rights Reserved.
 */
package alpine.util;

import org.apache.commons.lang3.math.NumberUtils;

public class JavaVersion {

    private static final String RUNTIME_VERSION = System.getProperty("java.runtime.version");
    private static int major, minor, update, build;
    static {
        if (RUNTIME_VERSION.startsWith("1.")) {
            String[] javaVersionElements = RUNTIME_VERSION.split("\\.|_|-b");
            major = NumberUtils.toInt(javaVersionElements[1]);
            minor = NumberUtils.toInt(javaVersionElements[2]);
            update = NumberUtils.toInt(javaVersionElements[3]);
            build = NumberUtils.toInt(javaVersionElements[4]);
        } else {
            //todo: make compatible with Java 9
        }
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getUpdate() {
        return update;
    }

    public int getBuild() {
        return build;
    }
}