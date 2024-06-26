import {setEventListener, setEventListeners} from "../utils/utils.js";
import {setProductDataToDeleteModal, deleteProduct, editProduct} from "../actions/productManagement.js";

setEventListeners("delete-product-button", e => setProductDataToDeleteModal(e.target.id));
setEventListener("confirmDeletionOfProductButton", () => deleteProduct())
setEventListener("confirmEditProductButton", () => editProduct())