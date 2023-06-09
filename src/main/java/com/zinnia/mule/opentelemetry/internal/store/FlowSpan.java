package com.zinnia.mule.opentelemetry.internal.store;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.context.Context;
import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class FlowSpan implements Serializable {
  private final String flowName;
  private final Span span;
  private boolean ending = false;
  private final Map<String, Span> childSpans = new ConcurrentHashMap<>();
  private boolean ended = false;

  public FlowSpan(String flowName, Span span) {
    this.flowName = flowName;
    this.span = span;
  }

  public Span getSpan() {
    return span;
  }

  public Span addProcessorSpan(String location, SpanBuilder spanBuilder) {
    if (ending || ended)
      throw new UnsupportedOperationException(
          "Flow " + flowName + " span " + (ended ? "has ended." : "is ending."));
    Span span = spanBuilder.setParent(Context.current().with(getSpan())).startSpan();
    childSpans.put(location, span);
    return span;
  }

  public void endProcessorSpan(String location, Consumer<Span> spanUpdater, Instant endTime) {
    if ((!ending || ended) && childSpans.containsKey(location)) {
      Span removed = childSpans.remove(location);
      if (spanUpdater != null)
        spanUpdater.accept(removed);
      removed.end(endTime);
    }
  }

  public void end(Instant endTime) {
    ending = true;
    childSpans.forEach((location, span) -> span.end(endTime));
    span.end(endTime);
    ended = true;
  }

  public Optional<Span> findSpan(String location) {
    return Optional.ofNullable(childSpans.get(location));
  }
}
