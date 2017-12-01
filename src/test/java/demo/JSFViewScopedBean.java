package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JSFViewScopedBean {
    @LocalServerPort
    int localPort;

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Test
    public void givenBeanThenViewScoped() {
        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("JSFViewScopedBean");
        assertThat(beanDefinition.getScope()).isEqualTo("view");
    }

    @Test
    public void givenBasePathwhenIndexThenJSFContent() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + localPort + "/index.xhtml", String.class)).contains("Populated by Custom spring View scoped bean");
    }

    @Test
    public void givenBasePathwhenIndexThenJSFViewState() {
        ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + localPort + "/index.xhtml", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("javax.faces.ViewState");
    }

    @Test
    public void givenConsolePathwhenIndexThenJSFContent() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + localPort + "/pages/index.xhtml", String.class)).contains("Populated by Custom spring View scoped bean");
    }

}
