package io.dropwizard.views;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import io.dropwizard.views.react.ReactViewRenderer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.codahale.metrics.MetricRegistry;
import com.google.common.collect.ImmutableList;

public class ReactViewRenderTest extends JerseyTest {


  @Produces(MediaType.TEXT_HTML)
  @Path("/")
  public static class TestResource {

    @GET
    @Path("test")
    public TestView test() {
      return new TestView();
    }

  }

  @Override
  protected Application configure() {
    ResourceConfig config = new ResourceConfig();
    final ViewRenderer renderer = new ReactViewRenderer();
    config.register(new ViewMessageBodyWriter(new MetricRegistry(), ImmutableList.of(renderer)));
    config.register(new TestResource());
    return config;
  }

  @Test
  public void renderTest() {
    final Response response = target("/test").request().get();
    final String txt = response.readEntity(String.class);
    assertThat(response.getStatus(), equalTo(200));
    assertThat(txt.contains(TestView.MESSAGE), equalTo(true));
  }


}
