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

import org.wso2.carbon.identity.governance.IdentityGovernanceException;
import org.wso2.carbon.identity.governance.common.IdentityConnectorConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.wso2.hibp.connector.util.Constants.*;

/**
 * HIBP connector config implementation.
 */
public class HIBPConnectorConfig implements IdentityConnectorConfig {

    @Override
    public String getName() {

        return CONNECTOR_NAME;
    }

    @Override
    public String getFriendlyName() {

        return "Pwned Passwords";
    }

    @Override
    public String getCategory() {

        return "Password Policies";
    }

    @Override
    public String getSubCategory() {

        return "DEFAULT";
    }

    @Override
    public int getOrder() {

        return 0;
    }

    @Override
    public Map<String, String> getPropertyNameMapping() {

        Map<String, String> nameMapping = new HashMap<>();
        nameMapping.put(CONNECTOR_ENABLE, "Enable HaveIBeenPwned pwned password checker");
        nameMapping.put(CONNECTOR_API_KEY, "HaveIBeenPwned API key");
        return nameMapping;
    }

    @Override
    public Map<String, String> getPropertyDescriptionMapping() {

        Map<String, String> descriptionMapping = new HashMap<>();
        descriptionMapping.put(CONNECTOR_ENABLE, "Validate user passwords with HaveIBeenPwned pwned password checker.");
        descriptionMapping.put(CONNECTOR_API_KEY, "API key access HaveIBeenPwned service.");
        return descriptionMapping;
    }

    @Override
    public String[] getPropertyNames() {

        return new String[]{
                CONNECTOR_ENABLE,
                CONNECTOR_API_KEY
        };
    }

    @Override
    public Properties getDefaultPropertyValues(String s) throws IdentityGovernanceException {

        Map<String, String> defaultProperties = new HashMap<>();
        defaultProperties.put(CONNECTOR_ENABLE, "false");
        defaultProperties.put(CONNECTOR_API_KEY, "");
        Properties properties = new Properties();
        properties.putAll(defaultProperties);
        return properties;
    }

    @Override
    public Map<String, String> getDefaultPropertyValues(String[] strings, String s) throws IdentityGovernanceException {

        return null;
    }
}
