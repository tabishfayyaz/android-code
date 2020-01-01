package com.techyourchance.testdrivendevelopment.exercise7;

import com.techyourchance.testdrivendevelopment.exercise7.networking.GetReputationHttpEndpointSync;

public class FetchReputationUseCaseSync {

    private final GetReputationHttpEndpointSync getReputationHttpEndpointSync;

    public FetchReputationUseCaseSync(GetReputationHttpEndpointSync getReputationHttpEndpointSync){
        this.getReputationHttpEndpointSync = getReputationHttpEndpointSync;
    }

    public UseCaseResult getReputationSync() {
        GetReputationHttpEndpointSync.EndpointResult result = getReputationHttpEndpointSync.getReputationSync();
        if (result.getStatus() == GetReputationHttpEndpointSync.EndpointStatus.GENERAL_ERROR){
            return UseCaseResult.FAILURE;
        } else if (result.getStatus() == GetReputationHttpEndpointSync.EndpointStatus.NETWORK_ERROR){
            return UseCaseResult.FAILURE;
        }
        else {
            return UseCaseResult.SUCCESS;
        }
    }

    public enum UseCaseResult {
        FAILURE, SUCCESS
    }
}