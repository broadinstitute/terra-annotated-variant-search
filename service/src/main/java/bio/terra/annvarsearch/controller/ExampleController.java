package bio.terra.annvarsearch.controller;

import bio.terra.common.iam.BearerTokenFactory;
import bio.terra.common.iam.SamUser;
import bio.terra.common.iam.SamUserFactory;
import bio.terra.annvarsearch.api.ExampleApi;
import bio.terra.annvarsearch.config.SamConfiguration;
import bio.terra.annvarsearch.iam.SamService;
import bio.terra.annvarsearch.model.Example;
import bio.terra.annvarsearch.service.ExampleService;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ExampleController implements ExampleApi {

  public static final String EXAMPLE_COUNTER_TAG = "tag";
  public static final String EXAMPLE_COUNTER_NAME = "example.counter";

  private final ExampleService exampleService;
  private final BearerTokenFactory bearerTokenFactory;
  private final SamUserFactory samUserFactory;
  private final SamConfiguration samConfiguration;
  private final HttpServletRequest request;

  private final SamService samService;

  public ExampleController(
      ExampleService exampleService,
      BearerTokenFactory bearerTokenFactory,
      SamUserFactory samUserFactory,
      SamConfiguration samConfiguration,
      HttpServletRequest request,
      SamService samService) {
    this.exampleService = exampleService;
    this.bearerTokenFactory = bearerTokenFactory;
    this.samUserFactory = samUserFactory;
    this.samConfiguration = samConfiguration;
    this.request = request;
    this.samService = samService;
  }

  private SamUser getUser() {
    // this automatically checks if the user is enabled
    return this.samUserFactory.from(request, samConfiguration.basePath());
  }

  /** Example of getting user information from sam. */
  @Override
  public ResponseEntity<String> getMessage() {
    var user = getUser();
    return ResponseEntity.of(
        this.exampleService.getExampleForUser(user.getSubjectId()).map(Example::message));
  }

  @Override
  public ResponseEntity<Void> setMessage(String body) {
    var user = getUser();
    this.exampleService.saveExample(new Example(user.getSubjectId(), body));
    return ResponseEntity.noContent().build();
  }

  /** Example of getting the bearer token and using it to make a Sam (or other service) api call */
  @Override
  public ResponseEntity<Boolean> getAction(String resourceType, String resourceId, String action) {
    var bearerToken = bearerTokenFactory.from(request);
    return ResponseEntity.ok(samService.getAction(resourceType, resourceId, action, bearerToken));
  }

  @Override
  public ResponseEntity<Void> incrementCounter(String tag) {
    Metrics.globalRegistry
        .counter(EXAMPLE_COUNTER_NAME, List.of(Tag.of(EXAMPLE_COUNTER_TAG, tag)))
        .increment();
    return ResponseEntity.noContent().build();
  }
}
