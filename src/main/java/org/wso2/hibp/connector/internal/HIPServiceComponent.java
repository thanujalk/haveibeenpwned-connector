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

package org.wso2.hibp.connector.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.osgi.service.http.HttpService;
import org.wso2.hibp.connector.HIBPServlet;

import javax.servlet.Servlet;

import static org.wso2.hibp.connector.util.Constants.HIBP_SERVLET_PATH;

/**
 * HIBP service component
 */
@Component(name = "org.wso2.hibp.connector",
        immediate = true)
public class HIPServiceComponent {

    private static Log log = LogFactory.getLog(HIPServiceComponent.class);
    private HttpService httpService;

    @Activate
    protected void activate(ComponentContext context) {

        Servlet commonAuthServlet = new ContextPathServletAdaptor(new HIBPServlet(),
                HIBP_SERVLET_PATH);

        try {
            httpService.registerServlet(HIBP_SERVLET_PATH, commonAuthServlet, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register /hibp servlet.", e);
        }

        log.info("/hibp servlet registered successfully.");
    }

    @Reference(
            name = "osgi.httpservice",
            service = HttpService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetHttpService"
    )
    protected void setHttpService(HttpService httpService) {

        if (log.isDebugEnabled()) {
            log.debug("HTTP Service is set in the hibp bundle");
        }

        this.httpService = httpService;
    }

    protected void unsetHttpService(HttpService httpService) {

        if (log.isDebugEnabled()) {
            log.debug("HTTP Service is unset in the hibp bundle");
        }

        this.httpService = null;
    }
}