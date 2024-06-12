import {getServerUrl, getUser, handleError, reloadWindow, resolveCSRFToken, resolveId} from "../utils/utils.js";

export function setUserDataToEditModal(fullId) {
    const id = resolveId(fullId);
    const userName = document.getElementById(`user-name-${id}`).innerText;
    const userEmail = document.getElementById(`user-email-${id}`).innerText;
    document.getElementById('userName').value = userName;
    document.getElementById('email').value = userEmail;
    document.getElementById('userId').innerText = id;
}

export function setUserIdToDeleteModal(fullId) {
    const id = resolveId(fullId);
    document.getElementById('user-deletion-span').innerText = document.getElementById(`user-email-${id}`).innerText;
    document.getElementById('userId').innerText = id;
}

export async function editUser() {
    const userId = document.getElementById(`userId`).innerText;
    console.log(`userId ${userId} is edited`);

    const userName = document.getElementById("userName").value;
    const userEmail = document.getElementById("email").value;
    const userResponse = await getUser(userId);

    if (await handleError(userResponse)) return;

    const user = await userResponse.json()
    user.name = userName;
    user.email = userEmail;

    const url = `${getServerUrl()}/api/users/${userId}`;
    const editionResponse = await fetch(url, {
        method: 'PATCH', body: JSON.stringify(user),
        headers: {'Content-Type': 'application/json', 'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(editionResponse)) {
        reloadWindow();
    }
}

export async function deleteUser() {
    const userId = document.getElementById(`userId`).innerText;
    console.log(`userId ${userId} is deleted`);

    const url = `${getServerUrl()}/api/users/${userId}`;
    const deleteUserResponse = await fetch(url, {
        method: 'DELETE',
        headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(deleteUserResponse)) {
        reloadWindow()
    }
}
