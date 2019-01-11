/*
 * ****************************************************************************
 *     Cloud Foundry
 *     Copyright (c) [2009-2017] Pivotal Software, Inc. All Rights Reserved.
 *
 *     This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *     You may not use this product except in compliance with the License.
 *
 *     This product includes a number of subcomponents with
 *     separate copyright notices and license terms. Your use of these
 *     subcomponents is subject to the terms and conditions of the
 *     subcomponent's license, as noted in the LICENSE file.
 * ****************************************************************************
 */

package org.cloudfoundry.identity.uaa.mock.limited;

import org.cloudfoundry.identity.uaa.login.LoginMockMvcTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

import static org.cloudfoundry.identity.uaa.mock.util.MockMvcUtils.*;
import static org.junit.Assert.assertTrue;

public class LimitedModeLoginMockMvcTests extends LoginMockMvcTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private File existingStatusFile = null;

    @BeforeEach
    @Override
    public void setUpContext() throws Exception {
        super.setUpContext();

        existingStatusFile = getLimitedModeStatusFile(webApplicationContext);
        setLimitedModeStatusFile(webApplicationContext);

        assertTrue(isLimitedMode());
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        resetLimitedModeStatusFile(webApplicationContext, existingStatusFile);
    }

}
