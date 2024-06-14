import {getServerUrl, handleError, reloadWindow, resolveCSRFToken, resolveId} from "../utils/utils.js";

export function setCategoryDataToEditModal(fullId) {
    const id = resolveId(fullId);
    document.getElementById('categoryName').value = document.getElementById(`category-name-${id}`).innerText;
    document.getElementById('categoryId').innerText = id;
}

export function setCategoryDataToDeleteModal(fullId) {
    const id = resolveId(fullId)
    document.getElementById('category-deletion-span').innerText = document.getElementById(`category-name-${id}`).innerText;
    document.getElementById('categoryId').innerText = id;
}

export async function editCategory() {
    const id = document.getElementById('categoryId').innerText;
    const value = document.getElementById('categoryName').value;
    const url = `${getServerUrl()}/api/categories/${id}?newName=${value}`;
    const editionResponse = await fetch(url, {
        method: 'PATCH', headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(editionResponse)) {
        reloadWindow();
    }
}

export async function deleteCategory() {
    const id = document.getElementById('categoryId').innerText;
    const url = `${getServerUrl()}/api/categories/${id}`;
    const deletionResponse = await fetch(url, {
        method: 'DELETE', headers: {'X-CSRF-TOKEN': resolveCSRFToken().token}
    });
    if (!await handleError(deletionResponse)) {
        reloadWindow();
    }
}
