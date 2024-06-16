import {
    addToCart,
    deleteFromCart
} from "../actions/cartActions.js";

async function handleAddToCart(productId) {
    await addToCart(productId);
    alert('Product added to cart');
}

async function handleDeleteFromCart(productId) {
    await deleteFromCart(productId);
    alert('Product deleted from cart');
}

window.handleAddToCart = handleAddToCart;
window.handleDeleteFromCart = handleDeleteFromCart;