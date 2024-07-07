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

export async function buyAndPay() {
    const href_parts = window.location.href.split('/');
    const product_id = href_parts[href_parts.length - 2];


    const response = await fetch(`${getServerUrl()}/api/cart/add/${product_id}/`, {
        body: `{ "quantity": 1 }`,
        method: 'PATCH',
        headers: {
            'X-CSRF-TOKEN': resolveCSRFToken().token,
            'Content-Type': 'application/json'
        }
    })
    await handleError(response)

    window.location.href = `${getServerUrl()}/payment/`;
}