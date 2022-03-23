package com.datalogic.dlapos.confighelper.configurations.support;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.util.ProfileTags;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


public class ProfileConfigurationTest {

    //region Updates
    @Test
    public void updateProperty() throws APosException {
        //region Profile initialization
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.setProfileID("Test");
        toTest.getCreation().setFactoryClass("TestFactory");
        toTest.getCreation().setServiceClass("TestService");
        toTest.getVendor().setName("TestVendor");
        toTest.getVendor().setUrl("TestVendorUrl");
        toTest.getAPos().setVersion("TestVersion");
        toTest.getAPos().setCategory("TestCategory");
        toTest.getProduct().setName("TestProductName");
        toTest.getProduct().setDescription("TestProductDescription");
        toTest.getProduct().setUrl("TestProductUrl");
        Property[] props = new Property[2];
        props[0] = new Property("prop1", "String", "prop1val");
        props[1] = new Property("prop2", "String", "prop2val");
        toTest.setProperties(props);
        //endregion

        //region Initial profile validation
        assertThat(toTest.getProfileID()).isEqualTo("Test");
        assertThat(toTest.getCreation().getFactoryClass()).isEqualTo("TestFactory");
        assertThat(toTest.getCreation().getServiceClass()).isEqualTo("TestService");
        assertThat(toTest.getVendor().getName()).isEqualTo("TestVendor");
        assertThat(toTest.getVendor().getUrl()).isEqualTo("TestVendorUrl");
        assertThat(toTest.getAPos().getVersion()).isEqualTo("TestVersion");
        assertThat(toTest.getAPos().getCategory()).isEqualTo("TestCategory");
        assertThat(toTest.getProduct().getName()).isEqualTo("TestProductName");
        assertThat(toTest.getProduct().getDescription()).isEqualTo("TestProductDescription");
        assertThat(toTest.getProduct().getUrl()).isEqualTo("TestProductUrl");
        assertThat(toTest.getProperties()[0].getName()).isEqualTo("prop1");
        assertThat(toTest.getProperties()[0].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("prop1val");
        assertThat(toTest.getProperties()[1].getName()).isEqualTo("prop2");
        assertThat(toTest.getProperties()[1].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[1].getValue()).isEqualTo("prop2val");
        //endregion

        //region Update and validations
        toTest.updateProperty(ProfileTags.PROFILE_ID, "Updated");
        assertThat(toTest.getProfileID()).isEqualTo("Updated");

        toTest.updateProperty(ProfileTags.SERVICE_INSTANCE_FACTORY, "UpdatedFactory");
        assertThat(toTest.getCreation().getFactoryClass()).isEqualTo("UpdatedFactory");

        toTest.updateProperty(ProfileTags.SERVICE_CLASS, "UpdatedServiceClass");
        assertThat(toTest.getCreation().getServiceClass()).isEqualTo("UpdatedServiceClass");

        toTest.updateProperty(ProfileTags.VENDOR_NAME, "UpdatedVendorName");
        assertThat(toTest.getVendor().getName()).isEqualTo("UpdatedVendorName");

        toTest.updateProperty(ProfileTags.VENDOR_URL, "UpdatedVendorUrl");
        assertThat(toTest.getVendor().getUrl()).isEqualTo("UpdatedVendorUrl");

        toTest.updateProperty(ProfileTags.APOS_VERSION, "UpdatedVersion");
        assertThat(toTest.getAPos().getVersion()).isEqualTo("UpdatedVersion");

        toTest.updateProperty(ProfileTags.APOS_CATEGORY, "UpdatedCategory");
        assertThat(toTest.getAPos().getCategory()).isEqualTo("UpdatedCategory");

        toTest.updateProperty(ProfileTags.PRODUCT_NAME, "UpdatedProductName");
        assertThat(toTest.getProduct().getName()).isEqualTo("UpdatedProductName");

        toTest.updateProperty(ProfileTags.PRODUCT_DESCRIPTION, "UpdatedProductDescription");
        assertThat(toTest.getProduct().getDescription()).isEqualTo("UpdatedProductDescription");

        toTest.updateProperty(ProfileTags.PRODUCT_URL, "UpdatedProductUrl");
        assertThat(toTest.getProduct().getUrl()).isEqualTo("UpdatedProductUrl");

        toTest.updateProperty("prop1", "updatedProp1");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("updatedProp1");

        toTest.updateProperty("prop2", "updatedProp2");
        assertThat(toTest.getProperties()[1].getValue()).isEqualTo("updatedProp2");
        //endregion
    }

    @Test(expected = APosException.class)
    public void updateOnVoidProfileConfig() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.updateProperty("NOT_EXISTS", "foo");
    }

    @Test(expected = APosException.class)
    public void updateInexistentProperty() throws APosException {
        //region Profile initialization
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.setProfileID("Test");
        toTest.getCreation().setFactoryClass("TestFactory");
        toTest.getCreation().setServiceClass("TestService");
        toTest.getVendor().setName("TestVendor");
        toTest.getVendor().setUrl("TestVendorUrl");
        toTest.getAPos().setVersion("TestVersion");
        toTest.getAPos().setCategory("TestCategory");
        toTest.getProduct().setName("TestProductName");
        toTest.getProduct().setDescription("TestProductDescription");
        toTest.getProduct().setUrl("TestProductUrl");
        Property[] props = new Property[2];
        props[0] = new Property("prop1", "String", "prop1val");
        props[1] = new Property("prop2", "String", "prop2val");
        toTest.setProperties(props);
        //endregion

        //region Initial profile validation
        assertThat(toTest.getProfileID()).isEqualTo("Test");
        assertThat(toTest.getCreation().getFactoryClass()).isEqualTo("TestFactory");
        assertThat(toTest.getCreation().getServiceClass()).isEqualTo("TestService");
        assertThat(toTest.getVendor().getName()).isEqualTo("TestVendor");
        assertThat(toTest.getVendor().getUrl()).isEqualTo("TestVendorUrl");
        assertThat(toTest.getAPos().getVersion()).isEqualTo("TestVersion");
        assertThat(toTest.getAPos().getCategory()).isEqualTo("TestCategory");
        assertThat(toTest.getProduct().getName()).isEqualTo("TestProductName");
        assertThat(toTest.getProduct().getDescription()).isEqualTo("TestProductDescription");
        assertThat(toTest.getProduct().getUrl()).isEqualTo("TestProductUrl");
        assertThat(toTest.getProperties()[0].getName()).isEqualTo("prop1");
        assertThat(toTest.getProperties()[0].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("prop1val");
        assertThat(toTest.getProperties()[1].getName()).isEqualTo("prop2");
        assertThat(toTest.getProperties()[1].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[1].getValue()).isEqualTo("prop2val");
        //endregion

        toTest.updateProperty("NOT_EXISTS", "foo");
    }

    @Test(expected = APosException.class)
    public void updateNullPropertyName() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.updateProperty(null, "da");
    }

    @Test(expected = APosException.class)
    public void updateNullValue() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.updateProperty("da", null);
    }
    //endregion

    //region AddProperty

    @Test
    public void addPropertyOnVoidProfile() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty("Test", "String", "Value");
        assertThat(toTest.getProperties().length).isEqualTo(1);
        assertThat(toTest.getProperties()[0].getName()).isEqualTo("Test");
        assertThat(toTest.getProperties()[0].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("Value");
    }

    @Test
    public void addProperty() throws APosException {
        //region Profile initialization
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty(ProfileTags.PROFILE_ID, null, "Test");
        toTest.addProperty(ProfileTags.SERVICE_INSTANCE_FACTORY, null, "TestFactory");
        toTest.addProperty(ProfileTags.SERVICE_CLASS, null, "TestService");
        toTest.addProperty(ProfileTags.VENDOR_NAME, null, "TestVendor");
        toTest.addProperty(ProfileTags.VENDOR_URL, null, "TestVendorUrl");
        toTest.addProperty(ProfileTags.APOS_VERSION, null, "TestVersion");
        toTest.addProperty(ProfileTags.APOS_CATEGORY, null, "TestCategory");
        toTest.addProperty(ProfileTags.PRODUCT_NAME, null, "TestProductName");
        toTest.addProperty(ProfileTags.PRODUCT_DESCRIPTION, null, "TestProductDescription");
        toTest.addProperty(ProfileTags.PRODUCT_URL, null, "TestProductUrl");
        toTest.addProperty("prop1", "String", "prop1val");
        toTest.addProperty("prop2", "String", "prop2val");
        //endregion

        //region Initial profile validation
        assertThat(toTest.getProfileID()).isEqualTo("Test");
        assertThat(toTest.getCreation().getFactoryClass()).isEqualTo("TestFactory");
        assertThat(toTest.getCreation().getServiceClass()).isEqualTo("TestService");
        assertThat(toTest.getVendor().getName()).isEqualTo("TestVendor");
        assertThat(toTest.getVendor().getUrl()).isEqualTo("TestVendorUrl");
        assertThat(toTest.getAPos().getVersion()).isEqualTo("TestVersion");
        assertThat(toTest.getAPos().getCategory()).isEqualTo("TestCategory");
        assertThat(toTest.getProduct().getName()).isEqualTo("TestProductName");
        assertThat(toTest.getProduct().getDescription()).isEqualTo("TestProductDescription");
        assertThat(toTest.getProduct().getUrl()).isEqualTo("TestProductUrl");
        assertThat(toTest.getProperties()[0].getName()).isEqualTo("prop1");
        assertThat(toTest.getProperties()[0].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("prop1val");
        assertThat(toTest.getProperties()[1].getName()).isEqualTo("prop2");
        assertThat(toTest.getProperties()[1].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[1].getValue()).isEqualTo("prop2val");
        //endregion

    }

    @Test(expected = APosException.class)
    public void addPropertyWithNullName() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty(null, "foo", "foo");
    }

    @Test(expected = APosException.class)
    public void addPropertyNullType() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty("foo", null, "foo");
    }

    @Test(expected = APosException.class)
    public void addPropertyNullValue() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty("foo", "foo", null);
    }

    //endregion

    //region Removes
    @Test
    public void removeProperty() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty("Test", "String", "Value");
        assertThat(toTest.getProperties().length).isEqualTo(1);
        assertThat(toTest.getProperties()[0].getName()).isEqualTo("Test");
        assertThat(toTest.getProperties()[0].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("Value");

        toTest.removeProperty("Test");
        assertThat(toTest.getProperties().length).isEqualTo(0);
    }

    @Test(expected = APosException.class)
    public void removePropertyNullName() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty("Test", "String", "Value");
        assertThat(toTest.getProperties().length).isEqualTo(1);
        assertThat(toTest.getProperties()[0].getName()).isEqualTo("Test");
        assertThat(toTest.getProperties()[0].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("Value");

        toTest.removeProperty(null);
    }

    @Test
    public void removePropertyVoidProfile() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.removeProperty("Test");
    }

    @Test
    public void removeInexistentProperty() throws APosException {
        ProfileConfiguration toTest = new ProfileConfiguration();
        toTest.addProperty("Test", "String", "Value");
        assertThat(toTest.getProperties().length).isEqualTo(1);
        assertThat(toTest.getProperties()[0].getName()).isEqualTo("Test");
        assertThat(toTest.getProperties()[0].getType()).isEqualTo("String");
        assertThat(toTest.getProperties()[0].getValue()).isEqualTo("Value");

        toTest.removeProperty("Test1");
        assertThat(toTest.getProperties().length).isEqualTo(1);
    }
    //endregion
}