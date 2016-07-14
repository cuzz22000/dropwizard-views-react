package io.dropwizard.views.react;

import io.dropwizard.views.View;
import io.dropwizard.views.ViewRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.matrixweb.jreact.JReact;

public class ReactViewRenderer implements ViewRenderer {


  public ReactViewRenderer() {}

  @Override
  public void configure(final Map<String, String> properties) {}

  @Override
  public String getSuffix() {
    return ".react";
  }

  @Override
  public boolean isRenderable(final View view) {
    return view.getTemplateName().endsWith(getSuffix());
  }

  @Override
  public void render(final View view, final Locale locale, final OutputStream outputStream)
      throws IOException {
    final JReact react = new JReact(true);
    react.addRequirePath("react-0.14");
    react.addRequirePath("react");
    final OutputStreamWriter osw = new OutputStreamWriter(outputStream);
    final Map<String, Object> properties = new HashMap<>();
    properties.put("view", view);
    final String result = react.renderToString("." + view.getTemplateName(), properties);
    osw.append(result);
    osw.close();

  }

}
