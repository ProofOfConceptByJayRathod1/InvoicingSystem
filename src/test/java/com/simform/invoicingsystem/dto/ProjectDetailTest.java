package com.simform.invoicingsystem.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class ProjectDetailTest {
    /**
     * Method under test: {@link ProjectDetail#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ProjectDetail()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ProjectDetail#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        // Act and Assert
        assertTrue(projectDetail.canEqual(new ProjectDetail()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectDetail#ProjectDetail()}
     *   <li>{@link ProjectDetail#setAccStartDate(LocalDate)}
     *   <li>{@link ProjectDetail#setAccType(String)}
     *   <li>{@link ProjectDetail#setActiveBillingFlag(boolean)}
     *   <li>{@link ProjectDetail#setChannel(String)}
     *   <li>{@link ProjectDetail#setClientDetails(ClientDetails)}
     *   <li>{@link ProjectDetail#setContractLink(String)}
     *   <li>{@link ProjectDetail#setCsm(String)}
     *   <li>{@link ProjectDetail#setCycle(String)}
     *   <li>{@link ProjectDetail#setInvoiceTerm(int)}
     *   <li>{@link ProjectDetail#setModel(String)}
     *   <li>{@link ProjectDetail#setName(String)}
     *   <li>{@link ProjectDetail#setPayModel(String)}
     *   <li>{@link ProjectDetail#setProjectEndDate(LocalDate)}
     *   <li>{@link ProjectDetail#setProjectStartDate(LocalDate)}
     *   <li>{@link ProjectDetail#setSalesPersons(Collection)}
     *   <li>{@link ProjectDetail#setSource(String)}
     *   <li>{@link ProjectDetail#toString()}
     *   <li>{@link ProjectDetail#getAccStartDate()}
     *   <li>{@link ProjectDetail#getAccType()}
     *   <li>{@link ProjectDetail#getChannel()}
     *   <li>{@link ProjectDetail#getClientDetails()}
     *   <li>{@link ProjectDetail#getContractLink()}
     *   <li>{@link ProjectDetail#getCsm()}
     *   <li>{@link ProjectDetail#getCycle()}
     *   <li>{@link ProjectDetail#getInvoiceTerm()}
     *   <li>{@link ProjectDetail#getModel()}
     *   <li>{@link ProjectDetail#getName()}
     *   <li>{@link ProjectDetail#getPayModel()}
     *   <li>{@link ProjectDetail#getProjectEndDate()}
     *   <li>{@link ProjectDetail#getProjectStartDate()}
     *   <li>{@link ProjectDetail#getSalesPersons()}
     *   <li>{@link ProjectDetail#getSource()}
     *   <li>{@link ProjectDetail#isActiveBillingFlag()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        ProjectDetail actualProjectDetail = new ProjectDetail();
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualProjectDetail.setAccStartDate(ofEpochDayResult);
        actualProjectDetail.setAccType("Acc Type");
        actualProjectDetail.setActiveBillingFlag(true);
        actualProjectDetail.setChannel("Channel");
        ClientDetails clientDetails = new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD",
                "GB", "4105551212");

        actualProjectDetail.setClientDetails(clientDetails);
        actualProjectDetail.setContractLink("Contract Link");
        actualProjectDetail.setCsm("Csm");
        actualProjectDetail.setCycle("Cycle");
        actualProjectDetail.setInvoiceTerm(1);
        actualProjectDetail.setModel("Model");
        actualProjectDetail.setName("Name");
        actualProjectDetail.setPayModel("Pay Model");
        LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
        actualProjectDetail.setProjectEndDate(ofEpochDayResult1);
        LocalDate ofEpochDayResult2 = LocalDate.ofEpochDay(1L);
        actualProjectDetail.setProjectStartDate(ofEpochDayResult2);
        ArrayList<String> stringList = new ArrayList<>();
        actualProjectDetail.setSalesPersons(stringList);
        actualProjectDetail.setSource("Source");
        String actualToStringResult = actualProjectDetail.toString();

        // Assert that nothing has changed
        assertSame(ofEpochDayResult, actualProjectDetail.getAccStartDate());
        assertEquals("Acc Type", actualProjectDetail.getAccType());
        assertEquals("Channel", actualProjectDetail.getChannel());
        assertSame(clientDetails, actualProjectDetail.getClientDetails());
        assertEquals("Contract Link", actualProjectDetail.getContractLink());
        assertEquals("Csm", actualProjectDetail.getCsm());
        assertEquals("Cycle", actualProjectDetail.getCycle());
        assertEquals(1, actualProjectDetail.getInvoiceTerm());
        assertEquals("Model", actualProjectDetail.getModel());
        assertEquals("Name", actualProjectDetail.getName());
        assertEquals("Pay Model", actualProjectDetail.getPayModel());
        assertSame(ofEpochDayResult1, actualProjectDetail.getProjectEndDate());
        assertSame(ofEpochDayResult2, actualProjectDetail.getProjectStartDate());
        assertSame(stringList, actualProjectDetail.getSalesPersons());
        assertEquals("Source", actualProjectDetail.getSource());
        assertTrue(actualProjectDetail.isActiveBillingFlag());
        assertEquals(
                "ProjectDetail(name=Name, model=Model, clientDetails=ClientDetails(name=Name, companyName=Company Name,"
                        + " email=jane.doe@example.org, city=Oxford, state=MD, country=GB, phoneNumber=4105551212), cycle=Cycle,"
                        + " invoiceTerm=1, payModel=Pay Model, accType=Acc Type, accStartDate=1970-01-02, projectStartDate=1970-01-02,"
                        + " projectEndDate=1970-01-02, csm=Csm, salesPersons=[], contractLink=Contract Link, source=Source,"
                        + " channel=Channel, activeBillingFlag=true)",
                actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectDetail#ProjectDetail(String, String, ClientDetails, String, int, String, String, LocalDate, LocalDate, LocalDate, String, Collection, String, String, String, boolean)}
     *   <li>{@link ProjectDetail#setAccStartDate(LocalDate)}
     *   <li>{@link ProjectDetail#setAccType(String)}
     *   <li>{@link ProjectDetail#setActiveBillingFlag(boolean)}
     *   <li>{@link ProjectDetail#setChannel(String)}
     *   <li>{@link ProjectDetail#setClientDetails(ClientDetails)}
     *   <li>{@link ProjectDetail#setContractLink(String)}
     *   <li>{@link ProjectDetail#setCsm(String)}
     *   <li>{@link ProjectDetail#setCycle(String)}
     *   <li>{@link ProjectDetail#setInvoiceTerm(int)}
     *   <li>{@link ProjectDetail#setModel(String)}
     *   <li>{@link ProjectDetail#setName(String)}
     *   <li>{@link ProjectDetail#setPayModel(String)}
     *   <li>{@link ProjectDetail#setProjectEndDate(LocalDate)}
     *   <li>{@link ProjectDetail#setProjectStartDate(LocalDate)}
     *   <li>{@link ProjectDetail#setSalesPersons(Collection)}
     *   <li>{@link ProjectDetail#setSource(String)}
     *   <li>{@link ProjectDetail#toString()}
     *   <li>{@link ProjectDetail#getAccStartDate()}
     *   <li>{@link ProjectDetail#getAccType()}
     *   <li>{@link ProjectDetail#getChannel()}
     *   <li>{@link ProjectDetail#getClientDetails()}
     *   <li>{@link ProjectDetail#getContractLink()}
     *   <li>{@link ProjectDetail#getCsm()}
     *   <li>{@link ProjectDetail#getCycle()}
     *   <li>{@link ProjectDetail#getInvoiceTerm()}
     *   <li>{@link ProjectDetail#getModel()}
     *   <li>{@link ProjectDetail#getName()}
     *   <li>{@link ProjectDetail#getPayModel()}
     *   <li>{@link ProjectDetail#getProjectEndDate()}
     *   <li>{@link ProjectDetail#getProjectStartDate()}
     *   <li>{@link ProjectDetail#getSalesPersons()}
     *   <li>{@link ProjectDetail#getSource()}
     *   <li>{@link ProjectDetail#isActiveBillingFlag()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange
        ClientDetails clientDetails = new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD",
                "GB", "4105551212");

        LocalDate accStartDate = LocalDate.ofEpochDay(1L);
        LocalDate projectStartDate = LocalDate.ofEpochDay(1L);
        LocalDate projectEndDate = LocalDate.ofEpochDay(1L);
        ArrayList<String> stringList = new ArrayList<>();

        // Act
        ProjectDetail actualProjectDetail = new ProjectDetail("Name", "Model", clientDetails, "Cycle", 1, "Pay Model",
                "Acc Type", accStartDate, projectStartDate, projectEndDate, "Csm", stringList, "Contract Link", "Source",
                "Channel", true);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualProjectDetail.setAccStartDate(ofEpochDayResult);
        actualProjectDetail.setAccType("Acc Type");
        actualProjectDetail.setActiveBillingFlag(true);
        actualProjectDetail.setChannel("Channel");
        ClientDetails clientDetails1 = new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD",
                "GB", "4105551212");

        actualProjectDetail.setClientDetails(clientDetails1);
        actualProjectDetail.setContractLink("Contract Link");
        actualProjectDetail.setCsm("Csm");
        actualProjectDetail.setCycle("Cycle");
        actualProjectDetail.setInvoiceTerm(1);
        actualProjectDetail.setModel("Model");
        actualProjectDetail.setName("Name");
        actualProjectDetail.setPayModel("Pay Model");
        LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
        actualProjectDetail.setProjectEndDate(ofEpochDayResult1);
        LocalDate ofEpochDayResult2 = LocalDate.ofEpochDay(1L);
        actualProjectDetail.setProjectStartDate(ofEpochDayResult2);
        ArrayList<String> stringList1 = new ArrayList<>();
        actualProjectDetail.setSalesPersons(stringList1);
        actualProjectDetail.setSource("Source");
        String actualToStringResult = actualProjectDetail.toString();

        // Assert that nothing has changed
        assertSame(ofEpochDayResult, actualProjectDetail.getAccStartDate());
        assertEquals("Acc Type", actualProjectDetail.getAccType());
        assertEquals("Channel", actualProjectDetail.getChannel());
        ClientDetails clientDetails2 = actualProjectDetail.getClientDetails();
        assertSame(clientDetails1, clientDetails2);
        assertEquals(clientDetails, clientDetails2);
        assertEquals("Contract Link", actualProjectDetail.getContractLink());
        assertEquals("Csm", actualProjectDetail.getCsm());
        assertEquals("Cycle", actualProjectDetail.getCycle());
        assertEquals(1, actualProjectDetail.getInvoiceTerm());
        assertEquals("Model", actualProjectDetail.getModel());
        assertEquals("Name", actualProjectDetail.getName());
        assertEquals("Pay Model", actualProjectDetail.getPayModel());
        assertSame(ofEpochDayResult1, actualProjectDetail.getProjectEndDate());
        assertSame(ofEpochDayResult2, actualProjectDetail.getProjectStartDate());
        Collection<String> salesPersons = actualProjectDetail.getSalesPersons();
        assertSame(stringList1, salesPersons);
        assertEquals(stringList, salesPersons);
        assertEquals("Source", actualProjectDetail.getSource());
        assertTrue(actualProjectDetail.isActiveBillingFlag());
        assertEquals(
                "ProjectDetail(name=Name, model=Model, clientDetails=ClientDetails(name=Name, companyName=Company Name,"
                        + " email=jane.doe@example.org, city=Oxford, state=MD, country=GB, phoneNumber=4105551212), cycle=Cycle,"
                        + " invoiceTerm=1, payModel=Pay Model, accType=Acc Type, accStartDate=1970-01-02, projectStartDate=1970-01-02,"
                        + " projectEndDate=1970-01-02, csm=Csm, salesPersons=[], contractLink=Contract Link, source=Source,"
                        + " channel=Channel, activeBillingFlag=true)",
                actualToStringResult);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new ProjectDetail(), null);
        assertNotEquals(new ProjectDetail(), "Different type to ProjectDetail");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectDetail#equals(Object)}
     *   <li>{@link ProjectDetail#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        // Act and Assert
        assertEquals(projectDetail, projectDetail);
        int expectedHashCodeResult = projectDetail.hashCode();
        assertEquals(expectedHashCodeResult, projectDetail.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectDetail#equals(Object)}
     *   <li>{@link ProjectDetail#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        ProjectDetail projectDetail1 = new ProjectDetail();

        // Act and Assert
        assertEquals(projectDetail, projectDetail1);
        int expectedHashCodeResult = projectDetail.hashCode();
        assertEquals(expectedHashCodeResult, projectDetail1.hashCode());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ClientDetails clientDetails = new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD",
                "GB", "4105551212");

        LocalDate accStartDate = LocalDate.ofEpochDay(1L);
        LocalDate projectStartDate = LocalDate.ofEpochDay(1L);
        LocalDate projectEndDate = LocalDate.ofEpochDay(1L);
        ProjectDetail projectDetail = new ProjectDetail("Name", "Model", clientDetails, "Cycle", 1, "Pay Model",
                "Acc Type", accStartDate, projectStartDate, projectEndDate, "Csm", new ArrayList<>(), "Contract Link",
                "Source", "Channel", true);

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setName("Name");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setModel("Model");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setClientDetails(
                new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD", "GB", "4105551212"));

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setCycle("Cycle");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setPayModel("Pay Model");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setAccType("Acc Type");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setAccStartDate(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setProjectStartDate(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setProjectEndDate(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setCsm("Csm");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setSalesPersons(new ArrayList<>());

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setContractLink("Contract Link");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals17() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setSource("Source");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals18() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setChannel("Channel");

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals19() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setActiveBillingFlag(true);

        // Act and Assert
        assertNotEquals(projectDetail, new ProjectDetail());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProjectDetail#equals(Object)}
     *   <li>{@link ProjectDetail#hashCode()}
     * </ul>
     */
    @Test
    void testEquals20() {
        // Arrange
        ClientDetails clientDetails = new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD",
                "GB", "4105551212");

        LocalDate accStartDate = LocalDate.ofEpochDay(1L);
        LocalDate projectStartDate = LocalDate.ofEpochDay(1L);
        LocalDate projectEndDate = LocalDate.ofEpochDay(1L);
        ProjectDetail projectDetail = new ProjectDetail("Name", "Model", clientDetails, "Cycle", 1, "Pay Model",
                "Acc Type", accStartDate, projectStartDate, projectEndDate, "Csm", new ArrayList<>(), "Contract Link",
                "Source", "Channel", true);
        ClientDetails clientDetails1 = new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD",
                "GB", "4105551212");

        LocalDate accStartDate1 = LocalDate.ofEpochDay(1L);
        LocalDate projectStartDate1 = LocalDate.ofEpochDay(1L);
        LocalDate projectEndDate1 = LocalDate.ofEpochDay(1L);
        ProjectDetail projectDetail1 = new ProjectDetail("Name", "Model", clientDetails1, "Cycle", 1, "Pay Model",
                "Acc Type", accStartDate1, projectStartDate1, projectEndDate1, "Csm", new ArrayList<>(), "Contract Link",
                "Source", "Channel", true);

        // Act and Assert
        assertEquals(projectDetail, projectDetail1);
        int expectedHashCodeResult = projectDetail.hashCode();
        assertEquals(expectedHashCodeResult, projectDetail1.hashCode());
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals21() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setName("Name");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals22() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setModel("Model");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals23() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setClientDetails(
                new ClientDetails("Name", "Company Name", "jane.doe@example.org", "Oxford", "MD", "GB", "4105551212"));

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals24() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setCycle("Cycle");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals25() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setPayModel("Pay Model");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals26() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setAccType("Acc Type");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals27() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setAccStartDate(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals28() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setProjectStartDate(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals29() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setProjectEndDate(LocalDate.ofEpochDay(1L));

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals30() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setCsm("Csm");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals31() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setSalesPersons(new ArrayList<>());

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals32() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setContractLink("Contract Link");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals33() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setSource("Source");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }

    /**
     * Method under test: {@link ProjectDetail#equals(Object)}
     */
    @Test
    void testEquals34() {
        // Arrange
        ProjectDetail projectDetail = new ProjectDetail();

        ProjectDetail projectDetail1 = new ProjectDetail();
        projectDetail1.setChannel("Channel");

        // Act and Assert
        assertNotEquals(projectDetail, projectDetail1);
    }
}

