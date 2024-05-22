import {getServerUrl, handleError, reloadWindow, resolveCSRFToken} from "../utils/utils.js";

export async function addToCart(id) {
    const response = await fetch(`${getServerUrl()}/cart/${id}`, {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': resolveCSRFToken().token
        }
    })
    await handleError(response)
}

export async function deleteFromCart(id) {
    const response = await fetch(`${getServerUrl()}/cart/${id}`, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': resolveCSRFToken().token
        }
    })
    if (!await handleError(response)) {
        reloadWindow()
    }
}