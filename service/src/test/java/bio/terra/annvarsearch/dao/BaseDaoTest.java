package bio.terra.annvarsearch.dao;

import bio.terra.annvarsearch.BaseSpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback
public abstract class BaseDaoTest extends BaseSpringBootTest {}
