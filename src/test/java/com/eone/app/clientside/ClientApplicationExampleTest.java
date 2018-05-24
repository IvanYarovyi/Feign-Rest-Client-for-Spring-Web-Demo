package com.eone.app.clientside;

import com.eone.app.api.UserApi;
import com.eone.app.api.User;
import com.eone.restclient.RestClientFactory;
import com.eone.app.serverside.ServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ServerApplication.class})
public class ClientApplicationExampleTest {

    @Configuration
    public class TestConfiguration{

        @Autowired
        private TestRestTemplate testRestTemplate;

        @Bean
        public UserApi restClient(@Autowired TestRestTemplate testRestTemplate){
            String rootUri = testRestTemplate.getRootUri();
            return RestClientFactory.clientOf(UserApi.class, rootUri);
        }
    }

    @Autowired
    private UserApi restClient;

    @Test
    public void restClientTest(){

        System.out.println("List: " + restClient.getCodes());
        System.out.println("Int :" + restClient.getVersion());

        User user = new User();
        user.setName("Ivan");
        user.setAge(50);
        System.out.println("Pojo :" + restClient.newUser(user));

        User actualResponse = restClient.getUser(100);
        System.out.println("Get by path var" + actualResponse);

        assertThat("Response is correct", actualResponse.getName(), is("User Name"));
        assertThat("Response is correct", actualResponse.getId(), is(100));
    }

}