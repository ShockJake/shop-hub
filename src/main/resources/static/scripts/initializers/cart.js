import {
    addToCart, buyAndPay,
    deleteFromCart
} from "../actions/cartActions.js";
import {setEventListener} from "../utils/utils.js";

async function handleAddToCart(productId) {
    await addToCart(productId);
    alert('Product added to cart');
}

async function handleDeleteFromCart(productId) {
    await deleteFromCart(productId);
}

setEventListener('buyProductButton', buyAndPay)

window.handleAddToCart = handleAddToCart;
window.handleDeleteFromCart = handleDeleteFromCart;