
package com.target.service;

import com.target.client.CabClient;
import com.target.client.SkeletonClientException;
import com.target.impl.domain.entity.DropPoint;
import com.target.impl.port.adaptor.persistance.repository.CabRepository;
import com.target.impl.port.adaptor.persistance.repository.DropPointRepository;
import com.target.impl.port.adaptor.persistance.repository.TeamMemberRepository;
import com.target.intf.CabRequestDTO;
import com.target.intf.RegisterCabRequestDTO;
import com.target.intf.Route;
import com.target.intf.RoutePlanResponseDTO;
import com.target.intf.ErrorDTO;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TestControllerStepDefs class.
 *
 * @author Rohan Raju
 */
public class CabControllerStepDefs extends StepDefs {

    @Autowired
    CabClient cabClient;

    @Autowired
    DropPointRepository dropPointRepository;

    @Autowired
    CabRepository cabRepository;
    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    Environment environment;

    List<List<ErrorDTO>> listsSkeletonErrorDTOs = new ArrayList<>();

    Map<String, String> dropPointData = new HashMap<>();
    List<CabRequestDTO> cabRequestDTOS = new ArrayList<>();
    List<RegisterCabRequestDTO> registerCabRequestDTOS = new ArrayList<>();
    RoutePlanResponseDTO routePlanResponseDTO;


    @Before
    @After
    public void clearAll() {
        cabRequestDTOS = new ArrayList<>();
        registerCabRequestDTOS = new ArrayList<>();
        listsSkeletonErrorDTOs = new ArrayList<>();
        dropPointData= new HashMap<>();
        dropPointRepository.deleteAll();
        cabRepository.deleteAll();
        teamMemberRepository.deleteAll();
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

    @Given("^the following Drop Point data:$")
    public void theFollowingDropPointData(DataTable dataTable) {
        dropPointData = dataTable.asMap(String.class, String.class);
    }

    @When("^call the add Drop Point endpoint$")
    public void callTheAddDropPointEndpoint() {
        try {
            cabClient.createDropPoint(new LinkedHashMap<>(dropPointData));
        } catch (SkeletonClientException e) {
            listsSkeletonErrorDTOs.add(e.getSkeletonErrorDTOS());
        }
    }

    @Then("^the drop Point table should have:$")
    public void theDropPointTableShouldHave(DataTable dataTable) {
        List<DataTableRow> gherkinRows = dataTable.getGherkinRows();
        for (DataTableRow dataTableRow : gherkinRows) {
            List<String> cells = dataTableRow.getCells();
            Optional<DropPoint> dropPoint = dropPointRepository.findByFromAndTo(cells.get(0), cells.get(1));
            Assert.assertTrue(dropPoint.get().getDistance() == Long.valueOf(cells.get(2)));
        }
    }

    @And("^the following Cab data:$")
    public void theFollowingCabData(DataTable dataTable) throws Throwable {
        List<DataTableRow> gherkinRows = dataTable.getGherkinRows().subList(1, dataTable.getGherkinRows().size());
        for (DataTableRow dataTableRow : gherkinRows) {
            List<String> cells = dataTableRow.getCells();
            CabRequestDTO cabRequestDTO = new CabRequestDTO();
            cabRequestDTO.setCabId(Long.valueOf(cells.get(0)));
            cabRequestDTO.setCapacity(Long.valueOf(cells.get(1)));
            cabRequestDTO.setCost(Long.valueOf(cells.get(2)));
            cabRequestDTOS.add(cabRequestDTO);
        }
    }

    @And("^call the add Cab endPoint$")
    public void callTheAddCabEndPoint() throws Throwable {
        try {
            cabClient.createCab(cabRequestDTOS);
        } catch (SkeletonClientException e) {
            listsSkeletonErrorDTOs.add(e.getSkeletonErrorDTOS());
        }
    }

    @And("^the following Team Member data:$")
    public void theFollowingTeamMemberData(DataTable dataTable) {
        List<DataTableRow> gherkinRows = dataTable.getGherkinRows().subList(1, dataTable.getGherkinRows().size());
        for (DataTableRow dataTableRow : gherkinRows) {
            List<String> cells = dataTableRow.getCells();
            RegisterCabRequestDTO registerCabRequestDTO = new RegisterCabRequestDTO();
            registerCabRequestDTO.setTeamMemberID(Long.valueOf(cells.get(0)));
            registerCabRequestDTO.setGender((cells.get(1)));
            registerCabRequestDTO.setDropPoint((cells.get(2)));
            registerCabRequestDTOS.add(registerCabRequestDTO);
        }
    }

    @And("^call the add Team Member endpoint$")
    public void callTheAddTeamMemberEndpoint() throws Throwable {

        for (RegisterCabRequestDTO registerCabRequestDTO : registerCabRequestDTOS) {
            try {
                cabClient.createCabForTeamMember(registerCabRequestDTO);
            } catch (SkeletonClientException e) {
                listsSkeletonErrorDTOs.add(e.getSkeletonErrorDTOS());
            }

        }

    }

    @And("^cab should have (\\d+) rows$")
    public void cabShouldHaveRows(int arg0) throws Throwable {
        Assert.assertTrue(cabRepository.count() == arg0);
    }

    @And("^team member should have (\\d+) rows$")
    public void teamMemberShouldHaveRows(int arg0) throws Throwable {
        Assert.assertTrue(teamMemberRepository.count() == arg0);
    }

    @And("^call the add Fetch endPoint$")
    public void callTheAddFetchEndPoint() throws Throwable {
        routePlanResponseDTO = cabClient.fetchRoutePlanFromHeadQuarter();
    }

    @Then("^when fetch for cab request is made should have total cost of (\\d+) and below details:$")
    public void whenFetchForCabRequestIsMadeShouldHaveTotalCostOfAndBelowDetails(int totalCost,DataTable dataTable) throws Throwable {
        List<DataTableRow> gherkinRows = dataTable.getGherkinRows().subList(1, dataTable.getGherkinRows().size());
        for (DataTableRow dataTableRow : gherkinRows) {
            List<String> cells = dataTableRow.getCells();
            Route route = routePlanResponseDTO.getRoutes().stream().filter(route1 -> route1.getCabId() == Long.valueOf(cells.get(0))).findFirst().get();
            Assert.assertTrue(route.getTeamMemberIds().equals(cells.get(1)));
            Assert.assertTrue(route.getRoute().equals(cells.get(2)));
            Assert.assertTrue(route.getRouteCost() == Long.valueOf(cells.get(3)));
        }
        Assert.assertTrue(routePlanResponseDTO.getTotalCost() == totalCost);
    }

    @Then("^the following cab errors should match:$")
    public void theFollowingCabErrorsShouldMatch(DataTable dataTable) throws Throwable {
        List<ErrorDTO> TestManagementErrorDTOSList = dataTable.asList(ErrorDTO.class);
        List<ErrorDTO> errorDtos = listsSkeletonErrorDTOs.stream().flatMap(TestManagementErrorDTOS -> TestManagementErrorDTOS.stream())
                .collect(Collectors.toList());
        Assert.assertTrue(CollectionUtils.isEqualCollection(TestManagementErrorDTOSList,errorDtos));
    }
}
