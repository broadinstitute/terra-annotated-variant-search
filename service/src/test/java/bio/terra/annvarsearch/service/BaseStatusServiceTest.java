package bio.terra.annvarsearch.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import bio.terra.annvarsearch.config.StatusCheckConfiguration;
import bio.terra.annvarsearch.model.SystemStatus;
import bio.terra.annvarsearch.model.SystemStatusSystems;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BaseStatusServiceTest {

  @Test
  void getCurrentStatus() {
    var config = new StatusCheckConfiguration(true, 0, 0, 10);
    BaseStatusService service = new BaseStatusService(config);
    var status = new SystemStatusSystems().ok(true);
    service.registerStatusCheck("test", () -> status);
    assertThat(service.getCurrentStatus(), is(new SystemStatus().ok(false)));
    service.checkStatus();
    assertThat(
        service.getCurrentStatus(),
        is(new SystemStatus().ok(true).systems(Map.of("test", status))));
  }
}
