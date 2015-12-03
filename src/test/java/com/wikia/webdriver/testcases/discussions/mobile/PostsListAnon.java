package com.wikia.webdriver.testcases.discussions.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.mobile.PostsListPage;

import org.testng.annotations.Test;

/**
    * @ownership Social Wikia
    */
@Test(groups = "Discussions")
public class PostsListAnon extends NewTestTemplate {

  @Test
  @Execute(asUser = User.ANONYMOUS)
  public void postsListLoads() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertFalse(postsList.isPostListEmpty());
  }
}