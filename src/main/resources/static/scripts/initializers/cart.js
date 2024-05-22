import {
    addToCart,
    deleteFromCart
} from "../actions/cartActions.js";

async function handleAddToCart(productId) {
    await addToCart(productId);
    window.location.href = '/cart';
}

async function handleDeleteFromCart(productId) {
    await deleteFromCart(productId);
    window.location.href = '/cart';
}

window.handleAddToCart = handleAddToCart;
window.handleDeleteFromCart = handleDeleteFromCart;