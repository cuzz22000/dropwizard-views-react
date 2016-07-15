### Dropwizard View React

Implements a [React](https://facebook.github.io/react/) view component utilizing KnisterPeter's [JReact](https://github.com/KnisterPeter/jreact) library.

#### Maven Central
 TODO
#### Usage

Simple Template.... way cooler stuff can be found @ [React](https://facebook.github.io/react/) 

```react
var React = require('react')


var Test = React.createClass({
  render: function() {
    return (
      React.DOM.div(null, this.props.view.mess)
    );
  }
});

module.exports = Test;

```

View Class referencing the `test.react` template

```java
package io.dropwizard.views;

public class TestView extends View {

  public TestView() {
    super("test.react");
  }

  public String getMess() {
    return MESSAGE;
  }

  public static final String MESSAGE = "Hello World!";
}
```

Create the resource that maps to the view and you're in business.

```java
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.test.dropwizard.views.TestView;

@Produces(MediaType.TEXT_HTML)
@Path("/")
public class TestResource {

  @GET
  @Path("test")
  public TestView test() {
    return new TestView();
  }

}
```



