package com.techyourchance.testdrivendevelopment.exercise7;

import com.techyourchance.testdrivendevelopment.exercise7.networking.GetReputationHttpEndpointSync;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.techyourchance.testdrivendevelopment.exercise7.FetchReputationUseCaseSync.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FetchReputationUseCaseSyncTest {

    @Mock
    GetReputationHttpEndpointSync getReputationHttpEndpointSyncMock;

    FetchReputationUseCaseSync SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new FetchReputationUseCaseSync(getReputationHttpEndpointSyncMock);
        success();
    }

    @Test
    public void getReputationSync_success_successReturned() throws Exception {

        UseCaseResult result = SUT.getReputationSync();
        assertThat(result, is(UseCaseResult.SUCCESS));
    }

    @Test
    public void getReputationSync_generalError_failureReturned() throws Exception {
        generalError();
        UseCaseResult result = SUT.getReputationSync();
        assertThat(result, is(UseCaseResult.FAILURE));
    }

    @Test
    public void getReputationSync_networkError_failureReturned() throws Exception {
        networkError();
        UseCaseResult result = SUT.getReputationSync();
        assertThat(result, is(UseCaseResult.FAILURE));
    }

    private void success() {
        when(getReputationHttpEndpointSyncMock.getReputationSync()).thenReturn(new GetReputationHttpEndpointSync.EndpointResult(GetReputationHttpEndpointSync.EndpointStatus.SUCCESS, 0));
    }

    private void generalError() {
        when(getReputationHttpEndpointSyncMock.getReputationSync()).thenReturn(new GetReputationHttpEndpointSync.EndpointResult(GetReputationHttpEndpointSync.EndpointStatus.GENERAL_ERROR, 0));
    }

    private void networkError() {
        when(getReputationHttpEndpointSyncMock.getReputationSync()).thenReturn(new GetReputationHttpEndpointSync.EndpointResult(GetReputationHttpEndpointSync.EndpointStatus.NETWORK_ERROR, 0));
    }
}