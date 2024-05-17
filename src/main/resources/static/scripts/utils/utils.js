export function getServerUrl() {
    return window.location.origin
}

export function resolveCSRFToken() {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    return {token: token, header: header};
}

export function setEventListener(objectId, eventListener) {
    const object = document.getElementById(objectId);
    object.addEventListener('click', eventListener);
}

export function setEventListeners(objectClassName, eventListener) {
    const objects = document.getElementsByClassName(objectClassName);
    for (const object of objects) {
        object.addEventListener('click', eventListener);
    }
}

export function catchRedirect(response) {
    if (response.redirected === true) {
        window.location.href = response.url;
    }
}

export const reloadWindow = () => window.location.reload();

export async function handleError(response) {
    if (response.status !== 200) {
        const text = await response.json();
        alert(text.message);
        return true;
    }
    return false;
}
