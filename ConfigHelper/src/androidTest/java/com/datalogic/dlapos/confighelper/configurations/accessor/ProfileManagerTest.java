package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.util.ProfileTags;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
public class ProfileManagerTest {

    ConfigDatabase dbManagerToTest;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dbManagerToTest = Room.inMemoryDatabaseBuilder(context, ConfigDatabase.class).build();
    }


    @After
    public void tearDown() {
        dbManagerToTest.profileDao().removeAll();
        dbManagerToTest.close();
    }

    //region File exists
    @Test
    public void fileExistsOnExistingFile() throws APosException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        assertThat(ProfileManager.profileFileExists(context, "apos.json")).isTrue();
    }

    @Test
    public void fileExistsOnInexistingFile() throws APosException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        assertThat(ProfileManager.profileFileExists(context, "iDoNotExist.json")).isFalse();
    }
    //endregion

    //region Parse
    @Test
    public void parseAssetsFile() throws APosException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        ProfileJsonHelper content = ProfileManager.parseAssetsFile(context, "apos.json");
        assertThat(content.getVersion()).isEqualTo("1.0");
        assertThat(content.getAPosEntries().length).isAtLeast(2);
        assertThat(content.getAPosEntries()[0].getProfileID()).isEqualTo("DL-Portal-Scanner");
        assertThat(content.getAPosEntries()[1].getProfileID()).isEqualTo("DLS-GM4100-USB-OEM");
    }

    @Test(expected = APosException.class)
    public void parseInexistentAssetsFile() throws APosException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        ProfileManager.parseAssetsFile(context, "err.json");
    }

    @Test
    public void parseNonJsonAssetFile() throws APosException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        ProfileManager.parseAssetsFile(context, "notAJson.txt");
    }
    //endregion


    //region DBAccess
    //region Upsert
    @Test
    public void upsertWhenEmpty() {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId", "{\"test\":\"test\"}"));
        assertThat(profileManager.getAllProfileIds().get(0)).isEqualTo("TestId");
    }

    @Test
    public void doubleUpsert() {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId", "{\"test\":\"test\"}"));
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId1", "{\"test\":\"test\"}"));
        assertThat(profileManager.getAllProfileIds().size()).isEqualTo(2);
        assertThat(profileManager.getAllProfileIds().get(0)).isEqualTo("TestId");
        assertThat(profileManager.getAllProfileIds().get(1)).isEqualTo("TestId1");
    }

    @Test
    public void updateValueUsingUpsert() throws APosException {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test1\"}"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test1");
    }
    //endregion

    //region InsertIfNotPresent
    @Test
    public void insertIfNotPresent() {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfileIfNotPresent(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        assertThat(profileManager.getAllProfileIds().isEmpty()).isFalse();
    }

    @Test
    public void doubleInsertIfNotPresent() throws APosException {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfileIfNotPresent(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
        dbManagerToTest.profileDao().insertProfileIfNotPresent(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test1\"}"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
    }
    //endregion

    //region InsertAll
    @Test
    public void insertAll() {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        ArrayList<Profile> dataToInsert = new ArrayList<>();
        dataToInsert.add(new Profile("TestId", "{}"));
        dataToInsert.add(new Profile("TestId1", "{}"));
        dbManagerToTest.profileDao().insertAllUpdateMode(dataToInsert);
        assertThat(profileManager.getAllProfileIds().size()).isEqualTo(2);
        assertThat(profileManager.getAllProfileIds().get(0)).isEqualTo("TestId");
        assertThat(profileManager.getAllProfileIds().get(1)).isEqualTo("TestId1");
    }

    @Test
    public void insertAllDropMode() throws APosException {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfileIfNotPresent(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
        ArrayList<Profile> dataToInsert = new ArrayList<>();
        dataToInsert.add(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test1\"}"));
        dataToInsert.add(new Profile("TestId1", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        dbManagerToTest.profileDao().insertAllDropMode(dataToInsert);
        assertThat(profileManager.getAllProfileIds().size()).isEqualTo(2);
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
        assertThat(profileManager.getConfigurationForProfileId("TestId1").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
    }

    @Test
    public void insertAllUpdateMode() throws APosException {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfileIfNotPresent(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
        ArrayList<Profile> dataToInsert = new ArrayList<>();
        dataToInsert.add(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test1\"}"));
        dataToInsert.add(new Profile("TestId1", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        dbManagerToTest.profileDao().insertAllUpdateMode(dataToInsert);
        assertThat(profileManager.getAllProfileIds().size()).isEqualTo(2);
        assertThat(profileManager.getConfigurationForProfileId("TestId").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test1");
        assertThat(profileManager.getConfigurationForProfileId("TestId1").getProperty(ProfileTags.PROFILE_ID)).isEqualTo("test");
    }
    //endregion

    //region Remove
    @Test
    public void remove() {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId", "{\"test\":\"test\"}"));
        assertThat(profileManager.getAllProfileIds().get(0)).isEqualTo("TestId");
        dbManagerToTest.profileDao().removeProfile("TestId");
        assertThat(profileManager.getAllProfileIds().isEmpty()).isTrue();
    }

    @Test
    public void removeWhenEmpty() {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        assertThat(profileManager.getAllProfileIds().isEmpty()).isTrue();
        dbManagerToTest.profileDao().removeProfile("TestId");
        assertThat(profileManager.getAllProfileIds().isEmpty()).isTrue();
    }
    //endregion

    //region RemoveAll
    @Test
    public void removeAll() {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId", "{\"test\":\"test\"}"));
        dbManagerToTest.profileDao().insertProfile(new Profile("TestId1", "{\"test\":\"test\"}"));
        assertThat(profileManager.getAllProfileIds().get(0)).isEqualTo("TestId");
        assertThat(profileManager.getAllProfileIds().get(1)).isEqualTo("TestId1");
        dbManagerToTest.profileDao().removeAll();
        assertThat(profileManager.getAllProfileIds().isEmpty()).isTrue();
    }
    //endregion

    //region getProperties
    @Test
    public void getProfile() throws APosException {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfileIfNotPresent(new Profile("TestId", "{\"" + ProfileTags.PROFILE_ID + "\":\"test\"}"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getAsProfileConfiguration().getProfileID()).isEqualTo("test");
    }

    @Test(expected = APosException.class)
    public void parsingANonJsonProfile() throws APosException {
        ProfileManager profileManager = new ProfileManager(dbManagerToTest);
        dbManagerToTest.profileDao().insertProfileIfNotPresent(new Profile("TestId", "MUST FAIL"));
        assertThat(profileManager.getConfigurationForProfileId("TestId").getAsProfileConfiguration().getProfileID()).isEqualTo("test");
    }
    //endregion
    //endregion
}