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

export async function uploadMoneyWithAmount() {
    const id = document.getElementById("walletIdStatic").innerText
    const amount = document.getElementById("uploadAmount").value

    const walletResponse = await fetch(`${getServerUrl()}/api/wallet/${id}`)
    if(await handleError(walletResponse))
        return;

    const wallet = await walletResponse.json();
    wallet.balance = Number(amount) + Number(wallet.balance);
    console.log(wallet.balance);
    const editionResponse = await fetch(`${getServerUrl()}/api/wallet/update`, {
        method: 'PATCH', body: JSON.stringify(wallet),
        headers: {'Content-Type': 'application/json', 'X-CSRF-TOKEN': resolveCSRFToken().token}
    })
    if (!await handleError(editionResponse)) {
        reloadWindow()
    }
}

export async function withdrawMoneyWithAmount() {
    const id = document.getElementById("walletIdStatic").value
    const amount = document.getElementById("withdrawAmount").value

    const walletResponse = await fetch(`${getServerUrl()}/api/wallet/${id}`)
    if(await handleError(walletResponse))
        return;

    const wallet = await walletResponse.json();
    const result = Number(wallet.balance) - Number(amount);

    if (result < 0) {
        return;
    }
    wallet.balance = result
    const editionResponse = await fetch(`${getServerUrl()}/api/wallet/update`, {
        method: 'PATCH', body: JSON.stringify(wallet),
        headers: {'Content-Type': 'application/json', 'X-CSRF-TOKEN': resolveCSRFToken().token}
    })
    if (!await handleError(editionResponse)) {
        reloadWindow()
    }
}
