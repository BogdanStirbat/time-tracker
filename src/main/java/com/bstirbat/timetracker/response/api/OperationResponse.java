package com.bstirbat.timetracker.response.api;

/**
 * Class used for returning the result of an backend operation to frontend.
 * Example: an entity was added/removed.
 */
public class OperationResponse {
    private boolean success;
    private Long id;

    public OperationResponse() {

    }

    public OperationResponse(boolean success) {
        this.success = success;
    }

    public OperationResponse(boolean success, Long id) {
        this.success = success;
        this.id = id;
    }

    /**
     * Describes if las operation was successful.
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * If last operation was an insert, returns the id of newly created entity.
     * @return
     */
    public Long getId() {
        return id;
    }
}
