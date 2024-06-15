import {getServerUrl, getUser, handleError, reloadWindow, resolveCSRFToken, resolveId} from "../utils/utils.js";

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
    const id = document.getElementById("productId").value
    const productName = document.getElementById('productName').value;
    const imageUrl = document.getElementById('formFile').value;
    const price = document.getElementById('price').value;
    const quantity = document.getElementById('quantity').value;
    const category = document.getElementById('category').value;
    const description = document.getElementById('description').value;
    const technicalDetailsBody = document.getElementById('technical-details').querySelector('tbody');

    const technicalDetails = []
    for(const row of technicalDetailsBody.rows) {
        let detailKey = row.cells[0].querySelector('input').value
        let detailValue = row.cells[1].querySelector('input').value
        technicalDetails.push({technicalDetail: detailKey, technicalDescription: detailValue});
    }

    const productDataResponse = await fetch(`${getServerUrl()}/api/products/${id}`, {method: 'GET'})

    if (await handleError(productDataResponse))
        return;

    const product = await productDataResponse.json();

    product.name = productName;
    product.description = description;
    product.categoryId = Number(category);
    product.price = Number(price);
    product.quantity = Number(quantity);
    product.technicalDetails = technicalDetails

    const url = `${getServerUrl()}/api/products`;

    const editionResponse = await fetch(url, {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json', 'X-CSRF-TOKEN': resolveCSRFToken().token},
        body: JSON.stringify(product)
    });

    if (await handleError(editionResponse))
        return
    window.location.reload()
}