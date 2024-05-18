import {
    approveRequest,
    deleteRequest,
    rejectRequest
} from "../actions/requestsManagement.js";

import {
    reloadWindow,
    resolveId,
    setEventListeners
} from "../utils/utils.js";

setEventListeners("delete-button", e => deleteRequest(e.target.id));
setEventListeners("approve-button", e => approveRequest(e.target.id).then(() => reloadWindow()));
setEventListeners("reject-button", e => rejectRequest(e.target.id).then(() => reloadWindow()));
