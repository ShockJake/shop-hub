import {getServerUrl, handleError, reloadWindow, resolveCSRFToken} from "../utils/utils.js";

export async function addToCart(id) {
    let quantity = document.getElementById('quantity');
    quantity = quantity ? quantity.value : 1;

    const response = await fetch(`${getServerUrl()}/cart/${id}?quantity=${quantity}`, {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': resolveCSRFToken().token
        }
    })
    await handleError(response)
}

export async function deleteFromCart(id) {
    let quantity = document.getElementById('quantity');
    quantity = quantity ? quantity.value : 1;

    const response = await fetch(`${getServerUrl()}/cart/${id}?quantity=${quantity}`, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': resolveCSRFToken().token
        }
    })
    if (!await handleError(response)) {
        reloadWindow()
    }
}