package io.dropwizard.views.react;

import io.dropwizard.views.View;
import io.dropwizard.views.ViewRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import de.matrixweb.jreact.JReact;

public abstract class ReactViewRenderer implements ViewRenderer {

  public ReactViewRenderer() {}

  @Override
  public void configure(final Map<String, String> arg0) {

  }

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
    OutputStreamWriter osw = new OutputStreamWriter(outputStream);
    final String result = react.renderToString("." + view.getTemplateName(), properties());
    osw.append(result);
    osw.close();

  }

  protected abstract ImmutableMap<String, Object> properties();
}
