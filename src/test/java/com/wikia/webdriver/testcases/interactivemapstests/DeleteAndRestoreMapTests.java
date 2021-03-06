package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

import org.testng.annotations.Test;

public class DeleteAndRestoreMapTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

 @Test(
      enabled = false, //MAIN-4538
      groups = {"DeleteAndRestoreMapTests_001", "DeleteAndRestoreMapTests", "InteractiveMaps"})
 @Execute(asUser = User.USER)
  public void DeleteAndRestoreMapTests_001_DeleteAndRestoreMapAsAMapOwner() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapPageObject selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[0]);
    DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
    InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
    selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[0]);
    selectedMap.verifyMapOpenedForDeleteMapTests();
    selectedMap.restoreMap();
    selectedMap.verifyMapOpenedForDeleteMapTests();
  }

  @Test(groups = {"DeleteAndRestoreMapTests_002", "DeleteAndRestoreMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER_2)
  public void DeleteAndRestoreMapTests_002_DeleteMapByNotOwner() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapPageObject selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[1]);
    DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
    deleteMapModal.clickDeleteMap();
    Assertion.assertEquals(deleteMapModal.getDeleteMapError(), InteractiveMapsContent.MAP_DELETE_ERROR);
  }

  @Test(groups = {"DeleteAndRestoreMapTests_003", "DeleteAndRestoreMapTests", "InteractiveMaps"},
        enabled=false)
  public void DeleteAndRestoreMapTests_003_StaffUserCanDeleteMap() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    InteractiveMapPageObject selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[2]);
    selectedMap.verifyMapOpenedForDeleteMapTests();
    DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
    InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
    selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[2]);
    selectedMap.verifyMapOpenedForDeleteMapTests();
    selectedMap.restoreMap();
    selectedMap.verifyMapOpenedForDeleteMapTests();
  }
}
