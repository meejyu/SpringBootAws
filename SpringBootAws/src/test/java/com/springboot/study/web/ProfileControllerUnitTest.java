package com.springboot.study.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {


    @Test
    public void profile_조회() {

        //given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real_db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        System.out.println("expectedProfile = " + expectedProfile);

        System.out.println("profile = " + profile);

        //then
        assertThat(profile).isEqualTo(expectedProfile);

    }

    @Test
    public void 첫번째조회() {

        //given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);

    }

    @Test
    public void 디폴트조회() {

        //given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);

    }

    

}
