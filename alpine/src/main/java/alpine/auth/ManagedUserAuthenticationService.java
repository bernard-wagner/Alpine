/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package alpine.auth;

import alpine.model.ManagedUser;
import alpine.persistence.QueryManager;
import javax.naming.AuthenticationException;
import java.security.Principal;

/**
 * Class that performs authentication against internally managed users.
 *
 * @since 1.0.0
 */
public class ManagedUserAuthenticationService implements AuthenticationService {

    private String username;
    private String password;

    /**
     * Authentication service validates credentials against internally managed users.
     *
     * @since 1.0.0
     */
    public ManagedUserAuthenticationService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns whether the username/password combo was specified or not. In
     * this case, since the constructor requires it, this method will always
     * return true.
     *
     * @since 1.0.0
     */
    public boolean isSpecified() {
        return true;
    }

    /**
     * Authenticates the username/password combo against the directory service
     * and returns a Principal if authentication is successful. Otherwise,
     * returns an AuthenticationException.
     *
     * @since 1.0.0
     */
    public Principal authenticate() throws AuthenticationException {
        try (QueryManager qm = new QueryManager()) {
            ManagedUser user = qm.getManagedUser(username);
            if (user != null && !user.isSuspended()) {
                if (PasswordService.matches(password.toCharArray(), user)) {
                    return user;
                }
            }
        }
        throw new AuthenticationException();
    }

}