package scripts.testscripts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import bio.terra.annvarsearch.api.PublicApi;
import bio.terra.testrunner.runner.TestScript;
import bio.terra.testrunner.runner.config.TestUserSpecification;
import com.google.api.client.http.HttpStatusCodes;
import scripts.client.AnnvarsearchClient;

public class GetStatus extends TestScript {
  @Override
  public void userJourney(TestUserSpecification testUser) throws Exception {
    var client = new AnnvarsearchClient(server);
    var publicApi = new PublicApi(client);
    publicApi.getStatus();
    assertThat(client.getStatusCode(), is(HttpStatusCodes.STATUS_CODE_OK));
  }
}
