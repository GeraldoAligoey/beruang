package com.gma.challenge.beruang.common;

public interface WriteServiceTest {

  public void testCreate_validRequestData();

  public void testCreate_invalidIncompleteRequestData();

  public void testCreate_invalidEmptyRequestData();

  public void testCreate_invalidNullRequestData();

  public void testUpdate_validId_validFullRequestData();

  public void testUpdate_validId_validPartialRequestData();

  public void testUpdate_validId_invalidEmptyRequestData();

  public void testUpdate_validId_invalidNullRequestData();
  
  public void testUpdate_invalidId_validFullRequestData();

  public void testUpdate_invalidId_validPartialRequestData();

  public void testUpdate_invalidId_invalidEmptyRequestData();

  public void testUpdate_invalidId_invalidNullRequestData();

  public void testDelete_validId();

  public void testDelete_invalidId();
}
