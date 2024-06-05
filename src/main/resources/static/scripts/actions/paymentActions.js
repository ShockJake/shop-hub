import {getServerUrl, handleError, reloadWindow, resolveCSRFToken} from "../utils/utils.js";

export async function uploadMoney() {

    const id = document.getElementById("walletIdStatic").value;
    let checkedValue = $('.form-check-input:checked').val();

    const walletResponse = await fetch(`${getServerUrl()}/api/wallet/${id}`, {method: 'GET'})
    if (await handleError(walletResponse))
        return;

    const wallet = await walletResponse.json();
    wallet.balance = Number(checkedValue) + Number(wallet.balance);

    const editionResponse = await fetch(`${getServerUrl()}/api/wallet/update`, {
        method: 'PATCH', body: JSON.stringify(wallet),
        headers: {'Content-Type': 'application/json', 'X-CSRF-TOKEN': resolveCSRFToken().token}
    });

    if (!await handleError(editionResponse)) {
        reloadWindow()
    }
}
