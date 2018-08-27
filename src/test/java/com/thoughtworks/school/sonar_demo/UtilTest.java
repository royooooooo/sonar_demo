package com.thoughtworks.school.sonar_demo;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class UtilTest {
    private Util util = new Util();

    @Test
    public void should_return_user_name() {
        assertThat(util.getName()).isEqualTo("userName");
    }

}