import {getServerUrl, handleError, reloadWindow, resolveCSRFToken, resolveId} from "../utils/utils.js";

export function setProductDataToDeleteModal(fullId) {
    const id = resolveId(fullId)
    document.getElementById('product-deletion-span').innerText = document.getElementById(`product-name-${id}`).innerText;
    document.getElementById('productId').innerText = id;
}

export async function deleteProduct() {
    const id = document.getElementById('productId').innerText;
    const url = `${getServerUrl()}/api/products/${id}`;

    const deletionResponse = await fetch(url, {
        method: 'DELETE', headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });

    if (!await handleError(deletionResponse)) {
        reloadWindow();
    }
}

export async function editProduct() {
    const id = document.getElementById("productId").innerText
    const productName = document.getElementById('productName').value;
    const imageUrl = document.getElementById('imagePreview').src;
    const price = document.getElementById('price').value;
    const category = document.getElementById('category').value;
    const description = document.getElementById('floatingTextarea').value;

    console.log(id, productName, description, imageUrl, price, category, description);
}