import {
    getServerUrl,
    handleError, reloadWindow, resolveCSRFToken, resolveId
} from "../utils/utils.js";

export async function approveRequest(fullId) {
    const id = resolveId(fullId);
    const url = `${getServerUrl()}/api/requests/update/${id}?requestAction=approve`;
    const approveRequestResponse = await fetch(url, {
        method: 'PUT',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(approveRequestResponse)) {
        reloadWindow();
    }
}

export async function rejectRequest(fullId) {
    const id = resolveId(fullId);
    const url = `${getServerUrl()}/api/requests/update/${id}?requestAction=reject`;
    const rejectRequestResponse = await fetch(url, {
        method: 'PUT',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(rejectRequestResponse)) {
        reloadWindow();
    }
}

export async function deleteRequest(fullId) {
    const id = resolveId(fullId);
    const url = `${getServerUrl()}/api/requests/${id}`;
    const deleteRequestResponse = await fetch(url, {
        method: 'DELETE',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(deleteRequestResponse)) {
        reloadWindow();
    }
}
