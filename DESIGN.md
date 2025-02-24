# terra-annotated-variant-search Design

This repo is responsible for housing the following information:

- a Spring-boot based java api `service/`,
- its associated `client/`,
- `integration/` tests, and
- various `scripts/` to support various repo-operations.

## service/

The Application's service is built and configured from
[bio.terra.annvarsearch.App](./service/src/main/java/bio/terra/annvarsearch/App.java).

See [terra-common-lib](https://github.com/DataBiosphere/terra-common-lib/)
for the following additionally configured integrations:

- [bio.terra.common.iam](https://github.com/DataBiosphere/terra-common-lib/tree/develop/src/main/java/bio/terra/common/iam)
- [bio.terra.common.logging](https://github.com/DataBiosphere/terra-common-lib/tree/develop/src/main/java/bio/terra/common/logging)
- [bio.terra.common.migrate](https://github.com/DataBiosphere/terra-common-lib/tree/develop/src/main/java/bio/terra/common/migrate)
- [bio.terra.common.retry.transaction](https://github.com/DataBiosphere/terra-common-lib/tree/develop/src/main/java/bio/terra/common/retry/transaction)
- [bio.terra.common.tracing](https://github.com/DataBiosphere/terra-common-lib/tree/develop/src/main/java/bio/terra/common/tracing)
- [bio.terra.annvarsearch](./service/src/main/java/bio/terra/annvarsearch)

Local service directory structure supports this structure:

- `config/`: type safe configs
- `controller/`: public facing api controllers
- `dao/`: ORM mapping from the configured database to `bio.terra.annvarsearch.model`
- `iam/`: remote iam setup and config
- `model/`: the service's internal model
- `service/`: internal service objects containing the business logic

Service api interfaces are generated via gradle's processing of
the [`openapi.yml` document](./service/src/main/resources/api/openapi.yml)

For more information about the `service/` design, please refer
to [docs/DESIGN-service.md](./docs/DESIGN-service.md)

## client/

Located in the `client/` directory,
the code for this package is entirely generated by `gradle` and
based on the same `openapi.yml` file used to generate the `service/` api-endpoints above.

## integration/

Integration tests are designed to be run using
the [`terra-test-runner`](https://github.com/databiosphere/terra-test-runner).
For more information about using the `terra-test-runner` infrastructure, please refer to that repo.

## scripts/

Please see [./scripts/README.md](./scripts/README.md) for more information about scripts.
