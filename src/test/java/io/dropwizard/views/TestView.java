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
