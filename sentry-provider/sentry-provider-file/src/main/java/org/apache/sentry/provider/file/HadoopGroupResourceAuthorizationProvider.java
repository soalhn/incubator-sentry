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

package org.apache.sentry.provider.file;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.Groups;
import org.apache.sentry.policy.common.RoleValidator;
import org.apache.sentry.policy.common.PolicyEngine;
import org.apache.sentry.provider.common.GroupMappingService;
import org.apache.sentry.provider.file.HadoopGroupMappingService;
import org.apache.sentry.provider.file.ResourceAuthorizationProvider;

import com.google.common.annotations.VisibleForTesting;

public class HadoopGroupResourceAuthorizationProvider extends
  ResourceAuthorizationProvider {

  // resource parameter present so that other AuthorizationProviders (e.g.
  // LocalGroupResourceAuthorizationProvider) has the same constructor params.
  public HadoopGroupResourceAuthorizationProvider(String resource, PolicyEngine policy) throws IOException {
    this(policy, new HadoopGroupMappingService(
        Groups.getUserToGroupsMappingService(new Configuration())));
  }

  @VisibleForTesting
  public HadoopGroupResourceAuthorizationProvider(PolicyEngine policy,
      GroupMappingService groupService) {
    super(policy, groupService);
  }

}
