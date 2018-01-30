package com.target.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by rohan on 31/12/17.
 */
@Profile("!integration-test")
@Configuration
public class SkeletonProdconfiguration {
}
