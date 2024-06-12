import {getServerUrl, getUser, handleError, reloadWindow, resolveCSRFToken} from "../utils/utils.js";

export async function editUserData() {
    const id = document.getElementById("idStatic").value;
    const email = document.getElementById("email").value;
    const userName = document.getElementById("userName").value;

    const userDataResponse = await getUser(id);
    if (await handleError(userDataResponse))
        return;

    const user = await userDataResponse.json();
    user.name = userName;
    user.email = email;

    const url = `${getServerUrl()}/api/users/${id}`;
    const editionResponse = await fetch(url, {
        method: 'PATCH', body: JSON.stringify(user),
        headers: {'Content-Type': 'application/json', 'X-CSRF-TOKEN': resolveCSRFToken().token}
    });

    if (!await handleError(editionResponse)) {
        reloadWindow()
    }
}

export async function deleteUser() {
    const id = document.getElementById("idStatic").value;
    const url = `${getServerUrl()}/api/users/${id}`;
    const deleteUserResponse = await fetch(url, {
        method: 'DELETE',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(deleteUserResponse)) {
        reloadWindow()
    }
}

export async function requestSellerRole() {
    const userName = document.getElementById("email").value;
    const userId = document.getElementById("idStatic").value;
    const request = {
        id: null,
        requestMessage: `User '${userName}' requested Seller Role`,
        requesterId: userId,
        requesterUserName: userName,
        requestType: "SELLER_ROLE_REQUEST",
        requestStatus: "PENDING"
    }
    const requestResponse = await fetch(`${getServerUrl()}/api/requests`, {
        method: 'POST',
        body: JSON.stringify(request),
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': resolveCSRFToken().token
        }
    })
    if (!await handleError(requestResponse)) {
        reloadWindow()
    }
}
