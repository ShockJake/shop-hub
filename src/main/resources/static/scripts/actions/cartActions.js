import {getServerUrl, handleError, reloadWindow, resolveCSRFToken} from "../utils/utils.js";

export async function addToCart(id) {
    let quantity = document.getElementById('quantity');
    quantity = quantity ? quantity.value : 1;

    const response = await fetch(`${getServerUrl()}/api/cart/add/${id}/`, {
        body: `{ "quantity": ${quantity} }`,
        method: 'PATCH',
        headers: {
            'X-CSRF-TOKEN': resolveCSRFToken().token,
            'Content-Type': 'application/json'
        }
    })
    await handleError(response)
}

export async function deleteFromCart(id) {
    let quantity = document.getElementById('quantity');
    quantity = quantity ? quantity.value : 1;

    const response = await fetch(`${getServerUrl()}/api/cart/remove/${id}`, {
        body: `{ "quantity": ${quantity} }`,
        method: 'PATCH',
        headers: {
            'X-CSRF-TOKEN': resolveCSRFToken().token,
            'Content-Type': 'application/json'
        }
    })
    if (!await handleError(response)) {
        reloadWindow()
    }
}