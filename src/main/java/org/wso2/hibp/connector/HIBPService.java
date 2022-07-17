/*
 * Copyright (c) 2022, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.hibp.connector;

import org.wso2.hibp.connector.util.Utils;

import java.util.Map;

/**
 * HIBP Service implementation.
 */
public class HIBPService {

    /**
     * Get password appearance count.
     *
     * @param password password.
     * @return appearance count.
     * @throws Exception in case of failure.
     */
    public static int getPasswordAppearanceCount(String password) throws Exception {

        String passwordHash = Utils.getSHA1(password);
        String firstFiveLettersOfHash = passwordHash.substring(0, 5);
        String remainingLettersOfHash = passwordHash.substring(5);

        Map<String, Integer> appearanceMap = Utils.getHIBPAppearanceMap(firstFiveLettersOfHash);
        if (appearanceMap.isEmpty() || !appearanceMap.containsKey(remainingLettersOfHash)) {
            return 0;
        }
        return appearanceMap.get(remainingLettersOfHash);
    }
}
